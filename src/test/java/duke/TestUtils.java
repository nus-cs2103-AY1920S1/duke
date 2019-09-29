package duke;

/**
 * Collection of methods and constants used in testing.
 */
public interface TestUtils {

    /**
     * Generates a random string of length 0 to maxChars.
     * Characters generated are from 33 - 126 in the ASCII table.
     *
     * @param minChars Minimum length of string to generate.
     * @param maxChars Maximum length of string to generate.
     * @return Random string generated.
     */
    static String generateRandomString(int minChars, int maxChars) {
        final int MIN_ASCII_CHAR = 33;
        final int MAX_ASCII_CHAR = 126;

        if (maxChars < 0) {
            throw new IllegalArgumentException(
                    "argument to generateRandomString cannot be negative.");
        }

        int length = (int) (Math.random() * (maxChars - minChars)) + minChars;
        StringBuilder string = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            string.append(
                    (char) (
                            Math.random() * (MAX_ASCII_CHAR - MIN_ASCII_CHAR)
                            + MIN_ASCII_CHAR));
        }

        return string.toString();
    }
}
