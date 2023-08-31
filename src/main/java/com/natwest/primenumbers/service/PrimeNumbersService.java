package com.natwest.primenumbers.service;

import com.natwest.primenumbers.domain.PrimeNumbersResponse;

import java.util.List;

public interface PrimeNumbersService {

    PrimeNumbersResponse getPrimeNumbers(final int initial);

}
