package com.example.demo.controller;

import com.example.demo.util.Problem1Series;
import com.example.demo.util.Problem2TextProcessor;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping("/test")
public class TestController {

    /**
     * Computes the nth term of the series.
     * Example: GET /test/series?n=1000
     */
    @GetMapping("/series")
    public String getSeriesTerm(@RequestParam(defaultValue = "1000") int n) {
        BigInteger result = Problem1Series.computeSeriesMember(n);
        return "The " + n + "th term in the series is: " + result;
    }

    /**
     * Processes a given text string according to Problem 2 rules.
     * Example: GET /test/text-process?input=abcdaabcdeabaaacbfaaaabcab
     */
    @GetMapping("/text-process")
    public String processText(@RequestParam String input) {
        return Problem2TextProcessor.processText(input);
    }
}
