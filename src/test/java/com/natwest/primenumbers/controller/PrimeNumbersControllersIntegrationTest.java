package com.natwest.primenumbers.controller;

import com.natwest.primenumbers.domain.PrimeNumbersResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

import static org.assertj.core.api.BDDAssertions.then;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PrimeNumbersControllersIntegrationTest {

    @Autowired
    private PrimeNumbersController controller;

    @Test
    void testPrimeNumbers() {
        // Given
        int initial = 10;

        // When
        ResponseEntity<PrimeNumbersResponse> primeNumbers = controller.getPrimeNumbers(initial, "application/json");

        // Then
        then(primeNumbers.getBody().getPrimes()).isEqualTo(Arrays.asList(2, 3, 5, 7));
    }
}
