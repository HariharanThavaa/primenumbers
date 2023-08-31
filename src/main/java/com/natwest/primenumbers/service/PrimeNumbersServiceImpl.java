package com.natwest.primenumbers.service;

import com.natwest.primenumbers.domain.PrimeNumbersResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Service
public class PrimeNumbersServiceImpl implements PrimeNumbersService {

    private static final PrimeNumbersResponse highestPrimeNumberResponseCached = new PrimeNumbersResponse(1, List.of());

    @Override
    public PrimeNumbersResponse getPrimeNumbers(final int initial) {
        List<Integer> primes;

        if (initial < highestPrimeNumberResponseCached.getInitial()) {
            primes = highestPrimeNumberResponseCached.getPrimes()
                    .stream()
                    .filter(n -> n <= initial)
                    .toList();
        } else {
            primes = IntStream.rangeClosed(2, initial)
                    .filter(n -> isPrime(n))
                    .boxed()
                    .toList();
        }

        writePrimeNumbersToCache(initial, primes);

        return new PrimeNumbersResponse(initial, primes);
    }

    private synchronized void writePrimeNumbersToCache(int initial, List<Integer> primes) {
        if (highestPrimeNumberResponseCached.getInitial() < initial) {
            highestPrimeNumberResponseCached.setInitial(initial);
            highestPrimeNumberResponseCached.setPrimes(primes);
        }
    }

    private boolean isPrime(int number) {
        if ((int) Math.sqrt(number) < highestPrimeNumberResponseCached.getInitial()) {
            return highestPrimeNumberResponseCached.getPrimes()
                    .stream()
                    .filter(n -> n <= (int) Math.sqrt(number))
                    .noneMatch(i -> number % i == 0);
        } else {
            return IntStream.rangeClosed(2, (int) Math.sqrt(number))
                    .noneMatch(i -> number % i == 0);
        }
    }
}
