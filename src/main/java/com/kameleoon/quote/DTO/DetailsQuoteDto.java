package com.kameleoon.quote.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DetailsQuoteDto {
    private String content;
    private LocalDate creationDate;
    private LocalDate updateDate;
    private String creatorName;
    private String score;
}
