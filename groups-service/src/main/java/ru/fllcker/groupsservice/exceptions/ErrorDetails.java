package ru.fllcker.groupsservice.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDetails {
    private String type;
    private String title;
    private int status;
    private String detail;
    private String instance;
}
