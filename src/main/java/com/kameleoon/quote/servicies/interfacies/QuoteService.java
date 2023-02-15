package com.kameleoon.quote.servicies.interfacies;

import com.kameleoon.quote.DTO.CreateQuoteDto;
import com.kameleoon.quote.DTO.DetailsQuoteDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface QuoteService {
    ResponseEntity<DetailsQuoteDto> createQuote(CreateQuoteDto createQuoteDto, Authentication authentication);

    ResponseEntity<DetailsQuoteDto> getQuoteById(Long id);

    ResponseEntity<DetailsQuoteDto> getRandomQuote();

    ResponseEntity<DetailsQuoteDto> updateQuote(Long id, CreateQuoteDto createQuoteDto);

    ResponseEntity<Void> deleteQuote(Long id);

    ResponseEntity<Void> voteFor(Long id, Authentication authentication);
    ResponseEntity<Void> voteAgainst(Long id, Authentication authentication);

    ResponseEntity<List<DetailsQuoteDto>> getTopTenQuotes();
    ResponseEntity<List<DetailsQuoteDto>> getWorseTenQuotes();


}
