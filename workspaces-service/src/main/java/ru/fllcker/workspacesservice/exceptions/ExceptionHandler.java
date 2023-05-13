package ru.fllcker.workspacesservice.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.server.handler.ResponseStatusExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseStatusExceptionHandler {
}
