package com.kameleoon.quote.repositories;

import com.kameleoon.quote.models.Voting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotingRepository extends JpaRepository<Voting, Long> {

}
