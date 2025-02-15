package com.aprozeanu.smt.repository;

import com.aprozeanu.smt.model.store.StoreSectionEntry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreSectionEntryRepository extends JpaRepository<StoreSectionEntry, Long> {
    Page<StoreSectionEntry> findStoreSectionEntryById(Long id, Pageable pageable);

    StoreSectionEntry findStoreSectionEntryByStoreSectionIdAndProductId(Long storeSectionId, Long productId);
}
