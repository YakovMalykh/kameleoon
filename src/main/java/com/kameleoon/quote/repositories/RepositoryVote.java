package com.kameleoon.quote.repositories;

import com.kameleoon.quote.models.User;
import com.kameleoon.quote.models.Vote;
import com.kameleoon.quote.models.Voting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositoryVote extends JpaRepository<Vote, Long> {

    Optional<Vote> findFirstByVotingAndVoter(Voting voting, User voter);

}
