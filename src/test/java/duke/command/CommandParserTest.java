package duke.command;


import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.Arrays;
import java.util.stream.Stream;

class CommandParserTest {
    @TestFactory
    Stream<DynamicTest> parseCommand_success() throws DukeInvalidCommandException {
        return Arrays.stream(Commands.values())
                .map(commandType ->
                        dynamicTest(
                                String.format(
                                        "%s dynamic test 1",
                                        commandType.toString()),
                                () -> CommandParser.parseCommand(commandType.toString())));
    }

    @TestFactory
    Stream<DynamicTest> parseCommand_success_trailing_random() throws DukeInvalidCommandException {
        return Arrays.stream(Commands.values())
                .map(commandType ->
                        dynamicTest(
                                String.format(
                                        "%s dynamic test 2",
                                        commandType.toString()),
                                () -> CommandParser.parseCommand(
                                        commandType.toString()
                                                + " "
                                                + generateRandomString(20))));
    }

    @TestFactory
    Stream<DynamicTest> parseCommand_invalid_leading_whitespace() throws DukeInvalidCommandException {
        return Arrays.stream(Commands.values())
                .map(commandType ->
                        dynamicTest(
                                String.format(
                                        "%s dynamic test 1",
                                        commandType.toString()),
                                () -> assertThrows(
                                        DukeInvalidCommandException.class,
                                        () -> CommandParser.parseCommand(
                                                " " + commandType.toString()))));
    }

    private String generateRandomString(int maxChars) {
        final int MIN_ASCII_CHAR = 33;
        final int MAX_ASCII_CHAR = 126;

        if (maxChars < 0) {
            throw new IllegalArgumentException(
                    "argument to generateRandomString cannot be negative.");
        }

        int length = (int) (Math.random() * maxChars);
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
