package duke.util;

/**
 * Utility methods for String comparisons.
 */
public class Match {

    /**
     * Returns True if both Strings provided are an exact match.
     *
     * @param word1 The word to match with word2.
     * @param word2 The word to match with word1.
     * @return True if word1 matches word2, otherwise False.
     */
    public static boolean matchExact(String word1, String word2) {
        return word1.equals(word2);
    }

    /**
     * Returns True if both Strings provided are a non-case-sensitive match.
     *
     * @param word1  The word to match with word2
     * @param word2 The word to match with word1
     * @return True if both Strings are provided are a non-case-sensitive, False otherwise.
     */
    public static boolean matchExactIgnoreCase(String word1, String word2) {
        return word1.equalsIgnoreCase(word2);
    }

    /**
     * Returns True if the two provided words have a levenshtein distance less than or equal to
     * a given threshold.
     *
     * @param word1 The word to match with word2
     * @param word2 The word to match with word1
     * @param threshold The maximum levenshtein distance between words for them to consider a match.
     * @return True if the levenshtein distance between the two provided words is within the threshold.
     */
    public static boolean matchFuzzy(String word1, String word2, int threshold) {
        return levenshteinDistance(word1, word1.length(), word2, word2.length()) <= threshold;
    }

    /**
     * Returns True if the words provided, ignoring case, have a levenshtein distance below or equal
     * to the threshold.
     *
     * @param word1 The word to match with word2
     * @param word2 The word to match with word1
     * @param threshold The maximum levenshtein distance between words for them to consider a match.
     * @return Returns True if the words provided, ignoring case, have a levenshtein distance below the threshold.
     */
    public static boolean matchFuzzyIgnoreCase(String word1, String word2, int threshold) {
        return matchFuzzy(word1.toLowerCase(), word2.toLowerCase(), threshold);
    }

    /**
     * Calculate the Levenshtein distance between two words.
     */
    // Reused from https://en.wikipedia.org/wiki/Levenshtein_distance#Recursive with minor modifications
    private static int levenshteinDistance(String word, int wordLen, String other, int otherLen) {
        if (wordLen == 0 || otherLen == 0) {
            return Math.max(wordLen, otherLen);
        }

        int cost = word.charAt(wordLen - 1) == other.charAt(otherLen - 1) ? 0 : 1;

        int deleteFromWord = levenshteinDistance(word, wordLen - 1, other, otherLen) + 1;
        int deleteFromOther = levenshteinDistance(word, wordLen, other, otherLen - 1) + 1;
        int deleteFromBoth = levenshteinDistance(word, wordLen - 1, other, otherLen - 1) + cost;

        return Math.min(deleteFromWord, Math.min(deleteFromOther, deleteFromBoth));
    }
}
