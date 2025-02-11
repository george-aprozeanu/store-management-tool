package com.aprozeanu.smt.repository;

import com.aprozeanu.smt.model.store.StoreSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoreSectionRepository extends JpaRepository<StoreSection, Long> {
    List<StoreSection> getAllByStoreName(String store);

    Optional<StoreSection> getFirstById(Long sectionId);
}
