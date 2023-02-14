package com.kameleoon.quote.repositories;

import com.kameleoon.quote.models.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {
    @Query(value = "SELECT quote.* FROM quote JOIN voting v ON quote.voting_id = v.id WHERE pros + cons > 0 ORDER BY ((pros + 1.9208) / (pros + cons) - 1.96 * " +
            "SQRT((pros * cons) / (pros + cons) + 0.9604) / (pros + cons)) / (1 + 3.8416 / (pros + cons)) DESC LIMIT 10", nativeQuery = true)
    List<Quote> getTenTopQuotes();

    @Query(value = "SELECT quote.* FROM quote JOIN voting v ON quote.voting_id = v.id WHERE pros + cons > 0 ORDER BY ((pros + 1.9208) / (pros + cons) - 1.96 * " +
            "SQRT((pros * cons) / (pros + cons) + 0.9604) / (pros + cons)) / (1 + 3.8416 / (pros + cons))  LIMIT 10", nativeQuery = true)
    List<Quote> getTenWorseQuotes();

}
