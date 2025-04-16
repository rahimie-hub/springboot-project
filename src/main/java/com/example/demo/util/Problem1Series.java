package com.example.demo.util;

import java.math.BigInteger;

/**
 * Utility class for Problem 1.
 * Computes the nth member of the series.
 */
public class Problem1Series {

    /**
     * Computes the nth term of the series.
     *
     * <p>Starting from the initial term of 1, the method iterates from 2 to n. For each index i:
     * <ul>
     *   <li>If i is even, it multiplies the current term by i.</li>
     *   <li>If i is odd, it adds i to the current term.</li>
     * </ul>
     *
     * @param n the position in the series whose value is to be computed (n should be at least 1)
     * @return the nth term as a BigInteger
     * 
     * The 1000ᵗʰ term of the series defined by
     * Term₁ = 1
     * For i ≥ 2:
     * – if i is even, Termᵢ = Termᵢ₋₁ × i
     * – if i is odd, Termᵢ = Termᵢ₋₁ + i
     */
    public static BigInteger computeSeriesMember(int n) {
        // Initialize the series with the first term, which is 1.
        BigInteger term = BigInteger.ONE;

        // Loop from 2 to n. Each loop iteration computes the next term.
        for (int i = 2; i <= n; i++) {
            // Check if i is even.
            if (i % 2 == 0) {
                // If i is even, multiply the current term by i.
                // BigInteger.valueOf(i) converts the integer i to a BigInteger.
                term = term.multiply(BigInteger.valueOf(i));
            } else {
                // If i is odd, add i to the current term.
                term = term.add(BigInteger.valueOf(i));
            }
        }
        // After processing all numbers up to n, return the computed term.
        return term;
    }
}