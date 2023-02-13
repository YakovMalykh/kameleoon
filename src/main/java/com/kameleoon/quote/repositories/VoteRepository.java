package com.kameleoon.quote.repositories;

import com.kameleoon.quote.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
}
