package com.natwest.primenumbers.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@XmlRootElement
public class PrimeNumbersResponse {

    @JsonProperty("Initial")
    private int initial;
    @JsonProperty("Primes")
    private List<Integer> primes;
}
