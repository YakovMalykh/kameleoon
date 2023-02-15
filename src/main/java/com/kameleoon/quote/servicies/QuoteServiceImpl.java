package com.kameleoon.quote.servicies;

import com.kameleoon.quote.DTO.CreateQuoteDto;
import com.kameleoon.quote.DTO.DetailsQuoteDto;
import com.kameleoon.quote.exceptions.QuoteNotFoundException;
import com.kameleoon.quote.mappers.QuoteMapper;
import com.kameleoon.quote.models.Quote;
import com.kameleoon.quote.models.User;
import com.kameleoon.quote.models.Voting;
import com.kameleoon.quote.repositories.QuoteRepository;
import com.kameleoon.quote.repositories.UserRepository;
import com.kameleoon.quote.servicies.interfacies.QuoteService;
import com.kameleoon.quote.servicies.interfacies.VotingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class QuoteServiceImpl implements QuoteService {

    private final QuoteRepository quoteRepository;
    private final QuoteMapper quoteMapper;
    private final UserRepository userRepository;
    private final VotingService votingService;

    @Override
    public ResponseEntity<DetailsQuoteDto> createQuote(CreateQuoteDto createQuoteDto, Authentication authentication) {
        Quote quote = quoteMapper.createQuoteDtoToQuote(createQuoteDto);
        quote.setCreationDate(LocalDate.now());
        quote.setUpdateDate(LocalDate.now());
        quote.setCreator(getUser(authentication));
        quote.setVoting(votingService.createVoting());

        Quote savedQuote = quoteRepository.save(quote);
        DetailsQuoteDto detailsQuoteDto = quoteMapper.quoteToDetailsQuoteDto(savedQuote);

        return ResponseEntity.ok(detailsQuoteDto);
    }

    private User getUser(Authentication authentication) {
        return userRepository.findByUsername(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("User '" + authentication.getName() + "' not found"));
    }

    @Override
    public ResponseEntity<DetailsQuoteDto> getQuoteById(Long id) {
        Quote quote = quoteRepository.findById(id).orElseThrow(() -> new QuoteNotFoundException("Quote with id: " + id + " not found"));
        DetailsQuoteDto detailsQuoteDto = quoteMapper.quoteToDetailsQuoteDto(quote);
        return ResponseEntity.ok(detailsQuoteDto);
    }

    @Override
    public ResponseEntity<DetailsQuoteDto> getRandomQuote() {
        List<Quote> quoteList = quoteRepository.findAll();
        if (quoteList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        long randomId = Double.valueOf(Math.random() * quoteList.size() + 1).longValue();
        return getQuoteById(randomId);
    }

    @Override
    public ResponseEntity<DetailsQuoteDto> updateQuote(Long id, CreateQuoteDto createQuoteDto) {
        Quote quote = quoteRepository.findById(id).orElseThrow(() -> new QuoteNotFoundException("Quote with id: " + id + " not found"));
        quote.setUpdateDate(LocalDate.now());

        quoteMapper.updateQuoteFromCreateQuoteDto(createQuoteDto, quote);

        Quote updatedAndSavedQuote = quoteRepository.save(quote);

        DetailsQuoteDto detailsQuoteDto = quoteMapper.quoteToDetailsQuoteDto(updatedAndSavedQuote);

        return ResponseEntity.ok(detailsQuoteDto);
    }

    @Override
    public ResponseEntity<Void> deleteQuote(Long id) {
        Quote quote = quoteRepository.findById(id).orElseThrow(() -> new QuoteNotFoundException("Quote with id: " + id + " not found"));
        quoteRepository.delete(quote);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> voteFor(Long quotesId, Authentication authentication) {
        Quote quote = quoteRepository.findById(quotesId).orElseThrow(() -> new QuoteNotFoundException("Quote with quotesId: " + quotesId + " not found"));
        User user = userRepository.findByUsername(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("User " + authentication.getName() + " not found"));
        Voting voting = quote.getVoting();
        votingService.voteFor(voting, user);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> voteAgainst(Long quotesId, Authentication authentication) {
        Quote quote = quoteRepository.findById(quotesId).orElseThrow(() -> new QuoteNotFoundException("Quote with quotesId: " + quotesId + " not found"));
        User user = userRepository.findByUsername(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("User " + authentication.getName() + " not found"));
        Voting voting = quote.getVoting();
        votingService.voteAgainst(voting, user);
        return ResponseEntity.ok().build();
    }
    @Override
    public ResponseEntity<List<DetailsQuoteDto>> getTopTenQuotes() {
        List<Quote> tenTopQuotes = quoteRepository.getTenTopQuotes();
        List<DetailsQuoteDto> listForResponse = new ArrayList<>();
        for (Quote quote : tenTopQuotes) {
            DetailsQuoteDto detailsQuoteDto = quoteMapper.quoteToDetailsQuoteDto(quote);
            listForResponse.add(detailsQuoteDto);
        }
        return ResponseEntity.ok(listForResponse);
    }

    @Override
    public ResponseEntity<List<DetailsQuoteDto>> getWorseTenQuotes() {
        List<Quote> tenWorseQuotes = quoteRepository.getTenWorseQuotes();
        List<DetailsQuoteDto> listForResponse = new ArrayList<>();
        for (Quote quote : tenWorseQuotes) {
            DetailsQuoteDto detailsQuoteDto = quoteMapper.quoteToDetailsQuoteDto(quote);
            listForResponse.add(detailsQuoteDto);
        }
        return ResponseEntity.ok(listForResponse);
    }

}
