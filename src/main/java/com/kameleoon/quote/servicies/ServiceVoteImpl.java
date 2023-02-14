package com.kameleoon.quote.servicies;

import com.kameleoon.quote.DTO.VoteDto;
import com.kameleoon.quote.exceptions.AlreadyVotesException;
import com.kameleoon.quote.models.User;
import com.kameleoon.quote.models.Vote;
import com.kameleoon.quote.models.Voting;
import com.kameleoon.quote.repositories.RepositoryVote;
import com.kameleoon.quote.servicies.interfacies.ServiceVote;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ServiceVoteImpl implements ServiceVote {
    private final RepositoryVote repositoryVote;

    @Override
    public void createVoteFor(Voting voting, User voter) {
        Optional<Vote> optionalVote = isThisUserAlreadyVotedInThisVoting(voting, voter);
        if (optionalVote.isEmpty()) {
            Vote vote = new Vote();
            vote.setPros(true);
            vote.setCreationDate(LocalDate.now());
            vote.setVoting(voting);
            vote.setVoter(voter);
            repositoryVote.save(vote);
        } else {
            throw new AlreadyVotesException(voter.getUsername() + " have already voted");
        }
    }

    @Override
    public void createVoteAgainst(Voting voting, User voter) {
        Optional<Vote> optionalVote = isThisUserAlreadyVotedInThisVoting(voting, voter);
        if (optionalVote.isEmpty()) {
            Vote vote = new Vote();
            vote.setCons(true);
            vote.setCreationDate(LocalDate.now());
            vote.setVoting(voting);
            vote.setVoter(voter);
            repositoryVote.save(vote);
        } else {
            throw new AlreadyVotesException(voter.getUsername() + " have already voted");
        }
    }

//    @Override
//    public List<VoteDto> getListVoteDto(Long qouteId) {
//        return repositoryVote.getListVoteDto(qouteId);
//    }

    private Optional<Vote> isThisUserAlreadyVotedInThisVoting(Voting voting, User voter) {
        return repositoryVote.findFirstByVotingAndVoter(voting, voter);
    }
}
