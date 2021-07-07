package weijie.duke.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StringUtilsTest {

    @Test
    void indent_singleLineInput_indentedOutput() {
        String input = "input string";
        String expectedOutput = "    input string";
        assertEquals(expectedOutput, StringUtils.indent(input));
    }

    @Test
    void indent_multiLineInput_indentedOutput() {
        String input = "hello\n   this is a test \n  ok bye";
        String expectedOutput = "    hello\n       this is a test \n      ok bye";
        assertEquals(expectedOutput, StringUtils.indent(input));
    }

    @Test
    void indent_emptyStringInput_fourSpaces() {
        String input = "";
        String expectedOutput = "    ";
        assertEquals(expectedOutput, StringUtils.indent(input));
    }

    @Test
    void indentf_inputWithArgs_indentedOutput() {
        String input = "%sfirst line.\n%s";
        String arg1 = "this is the ";
        String arg2 = "this is the second line.";
        String expectedOutput = "    this is the first line.\n    this is the second line.";
        assertEquals(expectedOutput, StringUtils.indentf(input, arg1, arg2));
    }
}
