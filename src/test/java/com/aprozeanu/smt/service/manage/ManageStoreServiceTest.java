package com.aprozeanu.smt.service.manage;

import com.aprozeanu.smt.model.store.StoreSectionEntry;
import com.aprozeanu.smt.repository.StoreSectionEntryRepository;
import com.aprozeanu.smt.service.manage.dto.ManageStoreRequest;
import com.aprozeanu.smt.service.manage.dto.UpdatePriceRequest;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.when;

class ManageStoreServiceTest {

    @Mock
    private StoreSectionEntryRepository entryRepository;

    @Mock
    private EntityManager em;

    @InjectMocks
    private ManageStoreService manageStoreService;
    private AutoCloseable mocks;

    @BeforeEach
    void setUp() {
        this.mocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        mocks.close();
    }

    @Test
    void dispatchRequestWithValidUpdatePriceRequest() {
        UpdatePriceRequest request = new UpdatePriceRequest(1L, 1L, 100);

        StoreSectionEntry storeSectionEntry = new StoreSectionEntry();
        storeSectionEntry.setPrice(50);
        when(entryRepository.findStoreSectionEntryByStoreSectionIdAndProductId(1L, 1L)).thenReturn(storeSectionEntry);

        var response = manageStoreService.dispatchRequest(request);

        assertInstanceOf(ManageStoreService.Success.class, response.result());
        assertEquals(100,
            ((ManageStoreService.Success<UpdatePriceRequest.UpdatePriceResponse>) response.result()).value().price());
    }

    @Test
    void dispatchRequestWithInvalidUpdatePriceRequest() {
        UpdatePriceRequest request = new UpdatePriceRequest(1L, 1L, 100);

        when(entryRepository.findStoreSectionEntryByStoreSectionIdAndProductId(1L, 1L)).thenReturn(null);

        var response = manageStoreService.dispatchRequest(request);

        assertInstanceOf(ManageStoreService.Failure.class, response.result());
        assertEquals("not found", ((ManageStoreService.Failure<?>) response.result()).errorMessage());
    }

    @Test
    void dispatchRequestWithUnsupportedRequest() {
        ManageStoreRequest<String> unsupportedRequest = new ManageStoreRequest<>() {
        };

        var response = manageStoreService.dispatchRequest(unsupportedRequest);

        assertInstanceOf(ManageStoreService.Failure.class, response.result());
        assertEquals("unsupported", ((ManageStoreService.Failure<?>) response.result()).errorMessage());
    }

    @Test
    void sequenceIncrementsCorrectly() {
        long firstSequence = manageStoreService.sequence();
        long secondSequence = manageStoreService.sequence();

        assertEquals(firstSequence + 1, secondSequence);
    }
}
