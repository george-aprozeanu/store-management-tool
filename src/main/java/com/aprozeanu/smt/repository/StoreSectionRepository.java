package com.aprozeanu.smt.repository;

import com.aprozeanu.smt.model.store.StoreSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreSectionRepository extends JpaRepository<StoreSection, Long> {
    List<StoreSection> getAllByStoreName(String store);

    StoreSection getFirstById(Long sectionId);

}
