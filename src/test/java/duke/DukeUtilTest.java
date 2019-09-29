package duke;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DukeUtilTest {
    @Test
    void concatArray_emptyArray_emptyString() {
        assertEquals(
                "",
                DukeUtil.concatStrings(new String[0], " | "));
    }

    @Test
    void concatArray_testStringArrays_success() {
        String[] testStringArray = new String[] { " a b c d | e f\\ g", " 1xf 0 9", "{[]}1|" };
        String[] testStringArray2 = new String[] { "a" };

        assertAll("Test string arrays concat",
                () -> assertEquals(
                        " a b c d | e f\\ g\\|*  1xf 0 9\\|* {[]}1|",
                        DukeUtil.concatStrings(testStringArray, "\\|* ")),
                () -> assertEquals(
                        "a",
                        DukeUtil.concatStrings(testStringArray2, " ")));
    }

    @Test
    void getIndexOfPattern_inEmptyArray_failure() {
        assertEquals(
                DukeUtil.FAIL_INDEX,
                DukeUtil.getIndexOfPattern(new String[0], "abc"));
    }

    @Test
    void getIndexOfPattern_primeStream_success() {
        String[] testPrimeStringStream = IntStream
                .range(2, 30)
                .filter(x -> IntStream
                    .range(2, x)
                    .noneMatch(y -> (double) x / (double) y ==  x / y))
                .mapToObj(primeNum -> primeNum + "")
                .toArray(size -> new String[15]);

        //2, 3, 5, 7, 11...
        assertEquals(
                3,
                DukeUtil.getIndexOfPattern(testPrimeStringStream, "7"));
    }
}
