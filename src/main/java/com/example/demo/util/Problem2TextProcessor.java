package com.example.demo.util;

/**
 * Utility class for Problem 2.
 * Processes an input string by replacing occurrences of 'a' (or 'A') according to these rules:
 *   1. A single 'a' becomes '#'
 *   2. In a run of consecutive 'a's, the first 'a' remains unchanged, and each subsequent 'a' becomes '$'
 *
 * Example:
 *   Input:  "abcdaabcdeabaaacbfaaaabcab"
 *   Output: "#bcda$bcde#ba$$cbfa$$$bc#b"
 */
public class Problem2TextProcessor {

    /**
     * Processes the given input string and returns the transformed result.
     *
     * @param input the original text to process
     * @return the processed text with 'a' replacements
     */
    public static String processText(String input) {
        // Use StringBuilder for efficient string concatenation
        StringBuilder result = new StringBuilder();
        int i = 0;

        // Iterate over the input characters one by one
        while (i < input.length()) {
            char ch = input.charAt(i);

            // Check if the current character is 'a' or 'A'
            if (Character.toLowerCase(ch) == 'a') {
                // Count how many 'a's (case-insensitive) are in a row
                int count = 0;
                int j = i;
                while (j < input.length() && Character.toLowerCase(input.charAt(j)) == 'a') {
                    count++;
                    j++;
                }

                // If there's exactly one 'a', replace it with '#'
                if (count == 1) {
                    result.append('#');
                } else {
                    // For multiple 'a's: keep the first 'a' as is
                    result.append(input.charAt(i));
                    // Replace each of the remaining 'a's in this group with '$'
                    for (int k = 1; k < count; k++) {
                        result.append('$');
                    }
                }

                // Skip past this entire group of 'a's
                i = j;
            } else {
                // If it's not an 'a', just copy the character unchanged
                result.append(ch);
                i++;
            }
        }

        // Convert StringBuilder back to String and return
        return result.toString();
    }
}
