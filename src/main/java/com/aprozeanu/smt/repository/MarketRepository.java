package com.aprozeanu.smt.repository;

import com.aprozeanu.smt.model.store.Market;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MarketRepository extends JpaRepository<Market, String> {
    Optional<Market> getFirstByName(String market);
}
