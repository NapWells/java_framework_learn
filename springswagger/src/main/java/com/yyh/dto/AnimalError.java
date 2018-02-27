package com.yyh.dto;

import lombok.Data;

@Data
public class AnimalError {
    private int status;
    private String message;
    private boolean result;
}
