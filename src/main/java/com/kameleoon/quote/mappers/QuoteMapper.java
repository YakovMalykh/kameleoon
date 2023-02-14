package com.kameleoon.quote.mappers;

import com.kameleoon.quote.DTO.CreateQuoteDto;
import com.kameleoon.quote.DTO.DetailsQuoteDto;
import com.kameleoon.quote.models.Quote;
import com.kameleoon.quote.models.User;
import com.kameleoon.quote.models.Voting;
import com.kameleoon.quote.repositories.QuoteRepository;
import com.kameleoon.quote.repositories.VotingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.*;

@Slf4j
@Mapper
@RequiredArgsConstructor
public abstract class QuoteMapper {
    protected QuoteRepository quoteRepository;
    protected VotingRepository votingRepository;

    public abstract Quote createQuoteDtoToQuote(CreateQuoteDto createQuoteDto);

    @Mapping(target = "creatorName", source = "creator")
    @Mapping(target = "score", source = "voting")
    public abstract DetailsQuoteDto quoteToDetailsQuoteDto(Quote quote);

    protected String UserToString(User creator) {
        if (creator != null) {
            return creator.getUsername();
        }
        return null;
    }

    protected String VotingToString(Voting voting) {
        if (voting != null) {
            Long pros = voting.getAmountPros();
            if (voting.getAmountPros() == null) {
                pros = 0L;
            }
            Long cons = voting.getAmountCons();
            if (voting.getAmountCons() == null) {
                cons = 0L;
            }
            return "pros: " + pros + "/ cons: " + cons;
        }
        return null;
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateQuoteFromCreateQuoteDto(CreateQuoteDto createQuoteDto, @MappingTarget Quote quote);


}
