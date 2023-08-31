package com.natwest.primenumbers.controller;

import com.natwest.primenumbers.domain.PrimeNumbersResponse;
import com.natwest.primenumbers.service.PrimeNumbersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestHeader;

import static org.springframework.http.HttpStatus.OK;


@RestController
@RequiredArgsConstructor
public class PrimeNumbersController {

    private final PrimeNumbersService primeNumbersService;

    @GetMapping("/primes/{initial}")
    public ResponseEntity<PrimeNumbersResponse> getPrimeNumbers(@PathVariable Integer initial,
                                                                @RequestHeader("Content-Type") String contentType) {
        MediaType mediaType;
        try {
            mediaType = MediaType.parseMediaType(contentType);
        } catch (Exception exception) {
            mediaType = MediaType.parseMediaType(MediaType.APPLICATION_JSON_VALUE);
        }

        return ResponseEntity.status(OK)
                .contentType(mediaType)
                .body(primeNumbersService.getPrimeNumbers(initial));
    }

}
