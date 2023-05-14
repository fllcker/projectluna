package ru.fllcker.authservice.utils;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

public class HttpUtils {
    @Value("${private.secret}")
    private static String secret;

    public static HttpEntity<String> generateWithPrivateHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("PrivateSecret", secret);
        return new HttpEntity<>(headers);
    }
}
