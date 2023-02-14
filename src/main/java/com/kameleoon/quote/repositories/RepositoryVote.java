package com.kameleoon.quote.repositories;

import com.kameleoon.quote.DTO.VoteDto;
import com.kameleoon.quote.models.User;
import com.kameleoon.quote.models.Vote;
import com.kameleoon.quote.models.Voting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepositoryVote extends JpaRepository<Vote, Long> {

    Optional<Vote> findFirstByVotingAndVoter(Voting voting, User voter);

//    @Modifying
//    @Query(value = "SELECT SUM(pros), SUM(cons), creation_date\n" +
//            "FROM vote WHERE  voting_id = ?\n" +
//            "GROUP BY creation_date", nativeQuery = true)
//    List<VoteDto> getListVoteDto(Long qouteId);
}
