package com.kameleoon.quote.servicies.interfacies;

import com.kameleoon.quote.DTO.VoteDto;
import com.kameleoon.quote.models.User;
import com.kameleoon.quote.models.Voting;
import org.springframework.security.core.Authentication;

import java.util.*;

public interface VotingService {
    Voting createVoting();

    void voteFor(Voting voting, User user);

    void voteAgainst(Voting voting, User user);

//    List<VoteDto> getListVoteDto(Long qouteId);
}
