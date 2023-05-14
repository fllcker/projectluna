package ru.fllcker.tasksservice.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    public static LocalDate convertStringToDate(String dateString) {
        if (dateString == null || dateString.isBlank() || dateString.isEmpty()) return null;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        try {
            return LocalDate.parse(dateString, formatter);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong date!");
        }
    }
}
