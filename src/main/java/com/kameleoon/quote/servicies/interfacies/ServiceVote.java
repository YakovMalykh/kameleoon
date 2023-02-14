package com.kameleoon.quote.servicies.interfacies;

import com.kameleoon.quote.DTO.VoteDto;
import com.kameleoon.quote.models.User;
import com.kameleoon.quote.models.Voting;

import java.util.List;

public interface ServiceVote {

    void createVoteFor(Voting voting, User user);
    void createVoteAgainst(Voting voting, User user);

//    List<VoteDto> getListVoteDto(Long qouteId);
}
