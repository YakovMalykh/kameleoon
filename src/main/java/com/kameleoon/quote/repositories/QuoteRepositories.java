package com.kameleoon.quote.repositories;

import com.kameleoon.quote.models.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepositories extends JpaRepository<Quote, Long> {
}
