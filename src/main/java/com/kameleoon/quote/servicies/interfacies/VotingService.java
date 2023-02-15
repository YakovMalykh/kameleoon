package com.kameleoon.quote.servicies.interfacies;

import com.kameleoon.quote.models.User;
import com.kameleoon.quote.models.Voting;

public interface VotingService {
    Voting createVoting();

    void voteFor(Voting voting, User user);

    void voteAgainst(Voting voting, User user);

}
