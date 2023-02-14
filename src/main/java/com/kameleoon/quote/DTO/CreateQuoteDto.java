package com.kameleoon.quote.DTO;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateQuoteDto {
    @NotBlank
    private String content;
}
