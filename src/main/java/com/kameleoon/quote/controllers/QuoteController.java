package com.kameleoon.quote.controllers;

import com.kameleoon.quote.DTO.CreateQuoteDto;
import com.kameleoon.quote.DTO.DetailsQuoteDto;
import com.kameleoon.quote.servicies.interfacies.QuoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/quotes")
@RequiredArgsConstructor
@Validated
public class QuoteController {
    private final QuoteService quoteService;

    @Operation(summary = "create new quote", responses = {
            @ApiResponse(responseCode = "200", description = "new quote is created"),
            @ApiResponse(responseCode = "404", description = "if user wasn't found")
    })
    @PostMapping
    public ResponseEntity<DetailsQuoteDto> createQuote(@Valid @RequestBody CreateQuoteDto createQuoteDto, Authentication authentication) {
        return quoteService.createQuote(createQuoteDto, authentication);
    }

    @Operation(summary = "get quote", responses = {
            @ApiResponse(responseCode = "200", description = "quote was gotten"),
            @ApiResponse(responseCode = "404", description = "quote wasn't found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DetailsQuoteDto> getQuoteById(@PathVariable Long id) {
        return quoteService.getQuoteById(id);
    }

    @Operation(summary = "get random quote", responses = {
            @ApiResponse(responseCode = "200", description = "quote was gotten"),
            @ApiResponse(responseCode = "204", description = "any quotes don't exist")
    })
    @GetMapping("/random")
    public ResponseEntity<DetailsQuoteDto> getRandomQuote() {
        return quoteService.getRandomQuote();
    }

    @Operation(summary = "update quote", responses = {
            @ApiResponse(responseCode = "200", description = "quote was updated"),
            @ApiResponse(responseCode = "404", description = "quote wasn't found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<DetailsQuoteDto> updateQuote(@PathVariable Long id, @Valid @RequestBody CreateQuoteDto createQuoteDto) {
        return quoteService.updateQuote(id, createQuoteDto);
    }

    @Operation(summary = "delete quote", responses = {
            @ApiResponse(responseCode = "200", description = "quote was gotten"),
            @ApiResponse(responseCode = "404", description = "quote wasn't found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuote(@PathVariable Long id) {
        return quoteService.deleteQuote(id);
    }

    @Operation(summary = "vote for", responses = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "quote, user or voting weren't found")
    })

    @PostMapping("/{quotesId}/vote-for")
    public ResponseEntity<Void> voteFor(@PathVariable Long quotesId, Authentication auth) {
        return quoteService.voteFor(quotesId, auth);
    }

    @Operation(summary = "vote against", responses = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "quote, user or voting weren't found")
    })
    @PostMapping("/{quotesId}/vote_against")
    public ResponseEntity<Void> voteAgainst(@PathVariable Long quotesId, Authentication auth) {
        return quoteService.voteAgainst(quotesId, auth);
    }

    @Operation(summary = "get list of top 10 quotes", responses = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @GetMapping("/top")
    public ResponseEntity<List<DetailsQuoteDto>> getTopTen() {
        return quoteService.getTopTenQuotes();
    }

    @Operation(summary = "get list of worse 10 quotes", responses = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @GetMapping("/worse")
    public ResponseEntity<List<DetailsQuoteDto>> getWorseTen() {
        return quoteService.getWorseTenQuotes();
    }

}
