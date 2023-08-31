package com.natwest.primenumbers.service;

import com.natwest.primenumbers.domain.PrimeNumbersResponse;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.BDDAssertions.then;

class PrimeNumberServiceImplTest {

    private final PrimeNumbersService underTest = new PrimeNumbersServiceImpl();

    /**
     * Testing strategy one without cache
     *
     * @param initial
     * @param expected
     */
    @ParameterizedTest
    @MethodSource("expectedOutputToGivenInputArguments")
    void testGetPrimeNumbersLessThanGivenNumbers(int initial, List<Integer> expected) {
        // Given
        // When
        PrimeNumbersResponse result = underTest.getPrimeNumbers(initial);

        // Then
        then(result.getInitial()).isEqualTo(initial);
        then(result.getPrimes().size()).isEqualTo(expected.size());
        then(result.getPrimes()).isEqualTo(expected);
    }

    static Stream<Arguments> expectedOutputToGivenInputArguments() {
        return Stream.of(Arguments.of(1, List.of()),
                Arguments.of(2, List.of(2)),
                Arguments.of(3, List.of(2, 3)),
                Arguments.of(4, List.of(2, 3)),
                Arguments.of(5, List.of(2, 3, 5)),
                Arguments.of(6, List.of(2, 3, 5)),
                Arguments.of(7, List.of(2, 3, 5, 7)),
                Arguments.of(8, List.of(2, 3, 5, 7)),
                Arguments.of(9, List.of(2, 3, 5, 7)),
                Arguments.of(50, List.of(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47)),
                Arguments.of(10, List.of(2, 3, 5, 7)),
                Arguments.of(25, List.of(2, 3, 5, 7, 11, 13, 17, 19, 23)));
    }
}