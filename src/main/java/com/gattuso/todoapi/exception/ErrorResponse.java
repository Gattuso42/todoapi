package com.gattuso.todoapi.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
public class ErrorResponse {
    private List<String> errorMessage;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timeStamp;

    public ErrorResponse(List<String> errorMessage) {
        this.errorMessage = errorMessage;
        this.timeStamp = LocalDateTime.now();
    }
}
