package com.aprozeanu.smt.service.manage;

import com.aprozeanu.smt.repository.StoreSectionEntryRepository;
import com.aprozeanu.smt.service.manage.dto.ManageStoreRequest;
import com.aprozeanu.smt.service.manage.dto.UpdatePriceRequest;
import jakarta.persistence.EntityManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ManageStoreService {

    private static final Log logger = LogFactory.getLog(ManageStoreService.class);

    private final StoreSectionEntryRepository entryRepository;
    private final EntityManager em;

    private long sequence = 0L;

    public ManageStoreService(StoreSectionEntryRepository entryRepository,
                              @Qualifier("entityManagerFactory") EntityManager em) {
        this.entryRepository = entryRepository;
        this.em = em;
    }

    synchronized long sequence() {
        return ++sequence;
    }

    @Transactional
    synchronized public <T> Response<T> dispatchRequest(ManageStoreRequest<T> request) {
        var sequence = sequence();
        logger.info(String.format("Request submitted SEQ #%06d %s", sequence, request));
        try {
            return new Response<>(execute(request), sequence);
        } catch (Exception e) {
            logger.error(String.format("request failure #%06d", sequence), e);
            return new Response<>(new Failure<>(e.getMessage()), sequence);
        }
    }

    @Transactional
    Result<UpdatePriceRequest.UpdatePriceResponse> updatePrice(UpdatePriceRequest request) {
        var sectionId = request.storeSectionId();
        var productId = request.productId();
        var storeSectionEntry = entryRepository.findStoreSectionEntryByStoreSectionIdAndProductId(sectionId, productId);
        if (storeSectionEntry != null) {
            storeSectionEntry.setPrice(request.price());
            em.merge(storeSectionEntry);
            return new Success<>(new UpdatePriceRequest.UpdatePriceResponse(storeSectionEntry.getId(),
                request.price()));
        } else {
            return new Failure<>("not found");
        }
    }

    @SuppressWarnings("unchecked")
    @Transactional
    <T> Result<T> execute(ManageStoreRequest<T> request) {
        if (request instanceof UpdatePriceRequest updatePriceRequest) {
            return (Result<T>) updatePrice(updatePriceRequest);
        } else {
            return new Failure<>("unsupported");
        }
    }

    public sealed interface Result<T> permits Success, Failure {
    }

    public record Success<T>(T value) implements Result<T> {
    }

    public record Failure<T>(String errorMessage) implements Result<T> {
    }

    public record Response<T>(Result<T> result, long sequence) {
    }

}
