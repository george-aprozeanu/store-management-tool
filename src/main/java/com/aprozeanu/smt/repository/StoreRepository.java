package com.aprozeanu.smt.repository;

import com.aprozeanu.smt.model.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    List<Store> getAllByMarketName(String market);

    Store getFirstByName(String store);
}
