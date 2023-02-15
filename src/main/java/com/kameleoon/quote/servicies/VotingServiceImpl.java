package com.kameleoon.quote.servicies;

import com.kameleoon.quote.models.User;
import com.kameleoon.quote.models.Voting;
import com.kameleoon.quote.repositories.VotingRepository;
import com.kameleoon.quote.servicies.interfacies.ServiceVote;
import com.kameleoon.quote.servicies.interfacies.VotingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class VotingServiceImpl implements VotingService {

    private final VotingRepository votingRepository;

    private final ServiceVote serviceVote;

    @Override
    public Voting createVoting() {
        return votingRepository.save(new Voting());
    }

    @Override
    public void voteFor(Voting voting, User voter) {
        Long amountPros = voting.getAmountPros();
        if (amountPros == null) {
            amountPros = 0L;
        }
        voting.setAmountPros(amountPros + 1);

        serviceVote.createVoteFor(voting, voter);

        votingRepository.save(voting);
    }

    @Override
    public void voteAgainst(Voting voting, User voter) {
        Long amountCons = voting.getAmountCons();
        if (amountCons == null) {
            amountCons = 0L;
        }
        voting.setAmountCons(amountCons + 1);

        serviceVote.createVoteAgainst(voting, voter);

        votingRepository.save(voting);
    }

}
