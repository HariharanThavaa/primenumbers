package com.natwest.primenumbers.controller;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.natwest.primenumbers.domain.PrimeNumbersResponse;
import com.natwest.primenumbers.service.PrimeNumbersService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@AutoConfigureJsonTesters
@WebMvcTest(PrimeNumbersController.class)
class PrimeNumbersControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<PrimeNumbersResponse> jsonPrimeNumbersResponse;

    @MockBean
    private PrimeNumbersService service;

    @Value("${response.content-type}")
    private String responseMediaType;

    @Test
    void testGetPrimeNumbersTestJson() throws Exception {
        // Given
        int initial = 10;
        PrimeNumbersResponse expected = new PrimeNumbersResponse(initial, List.of(2, 3, 5, 7));
        given(service.getPrimeNumbers(initial)).willReturn(expected);

        // When
        MockHttpServletResponse actual = mvc.perform(get("/primes/" + initial)
                        .contentType("application/json"))
                .andReturn()
                .getResponse();

        // Then
        then(actual.getStatus()).isEqualTo(HttpStatus.OK.value());
        then(actual.getContentAsString()).isEqualTo(jsonPrimeNumbersResponse.write(expected).getJson());
    }

    @Test
    void testGetPrimeNumbersTestXML() throws Exception {
        // Given
        int initial = 10;
        PrimeNumbersResponse expected = new PrimeNumbersResponse(initial, List.of(2, 3, 5, 7));
        given(service.getPrimeNumbers(initial)).willReturn(expected);

        // When
        MockHttpServletResponse actual = mvc.perform(get("/primes/" + initial)
                        .contentType("application/xml"))
                .andReturn()
                .getResponse();

        // Then
        then(actual.getStatus()).isEqualTo(HttpStatus.OK.value());
        then(actual.getContentAsString()).isEqualTo(new XmlMapper().writeValueAsString(expected));
    }
}