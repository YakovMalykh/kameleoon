package com.kameleoon.quote.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class VoteDto {

    private Long quotesId;
    private Long sumPros;
    private Long sumCond;
    private LocalDate creationDate;

}
