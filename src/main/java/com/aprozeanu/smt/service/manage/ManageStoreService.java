package com.aprozeanu.smt.service.manage;

import com.aprozeanu.smt.repository.StoreSectionEntryRepository;
import com.aprozeanu.smt.service.manage.dto.ManageStoreRequest;
import com.aprozeanu.smt.service.manage.dto.UpdatePriceRequest;
import jakarta.persistence.EntityManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ManageStoreService {

    private static final Log logger = LogFactory.getLog(ManageStoreService.class);

    private final StoreSectionEntryRepository entryRepository;
    private final EntityManager em;

    @Value("${task.timeout.millis}")
    private Long timeout;

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
    synchronized public <T> Result<T> dispatchRequest(ManageStoreRequest<T> request) {
        var sequence = sequence();
        logger.info(String.format("Request submitted SEQ #%06d %s", sequence, request));
        try {
            var value = execute(request);
            return new Success<>(sequence, value);
        } catch (Exception e) {
            logger.error(String.format("request failure #%06d", sequence), e);
            return new Failure<>(sequence, e.getMessage());
        }
    }

    @Transactional
    UpdatePriceRequest.UpdatePriceResponse updatePrice(UpdatePriceRequest request) {
        var sectionId = request.storeSectionId();
        var productId = request.productId();
        var storeSectionEntry = entryRepository.findStoreSectionEntryByStoreSectionIdAndProductId(sectionId, productId);
        if (storeSectionEntry != null) {
            storeSectionEntry.setPrice(request.price());
            em.merge(storeSectionEntry);
        }
        return new UpdatePriceRequest.UpdatePriceResponse(storeSectionEntry.getId(), request.price());
    }

    @SuppressWarnings("unchecked")
    @Transactional
    <T> T execute(ManageStoreRequest<T> request) {
        if (request instanceof UpdatePriceRequest updatePriceRequest) {
            return (T) updatePrice(updatePriceRequest);
        } else {
            throw new RuntimeException();
        }
    }

    public sealed interface Result<T> permits Success, Failure {
    }

    public record Success<T>(long sequence, T value) implements Result<T> {
    }

    public record Failure<T>(long sequence, String errorMessage) implements Result<T> {
    }

}
