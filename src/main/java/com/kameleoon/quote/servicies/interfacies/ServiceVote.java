package com.kameleoon.quote.servicies.interfacies;

import com.kameleoon.quote.models.User;
import com.kameleoon.quote.models.Voting;


public interface ServiceVote {

    void createVoteFor(Voting voting, User user);
    void createVoteAgainst(Voting voting, User user);

}
