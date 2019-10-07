package weomucat.doko.parser;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import weomucat.doko.command.parameter.ParameterOptions;
import weomucat.doko.command.parameter.StubParameter;

class CommandParameterParserTest {

  @Test
  void parametersShouldBeNull() {
    String[] tests = {"", " ", "  ", "   "};

    for (String test : tests) {
      StubParameter body = new StubParameter();
      StubParameter from = new StubParameter();
      StubParameter to = new StubParameter();

      CommandParametersParser parser = new CommandParametersParser(test,
          new ParameterOptions(body)
              .put("/from", from)
              .put("/to", to));
      assertDoesNotThrow(parser::parse);

      assertEquals("", body.value(), formatMessage(test));
      assertNull(from.value(), formatMessage(test));
      assertNull(to.value(), formatMessage(test));
    }
  }

  @Test
  void parametersShouldBeEmptyString() {
    String[] tests = {
        "/from /to", " /from /to ", "  /from  /to  ", "   /from   /to   "
    };

    for (String test : tests) {
      StubParameter body = new StubParameter();
      StubParameter from = new StubParameter();
      StubParameter to = new StubParameter();

      CommandParametersParser parser = new CommandParametersParser(test,
          new ParameterOptions(body)
              .put("/from", from)
              .put("/to", to));
      assertDoesNotThrow(parser::parse);

      assertEquals("", body.value(), formatMessage(test));
      assertEquals("", from.value(), formatMessage(test));
      assertEquals("", to.value(), formatMessage(test));
    }
  }

  @Test
  void parametersShouldBeOneWord() {
    String[] tests = {
        "one /from two /to three", " one /from two /to three ",
        "  one  /from  two  /to  three  ", "   one   /from   two   /to   three   "
    };

    for (String test : tests) {
      StubParameter body = new StubParameter();
      StubParameter from = new StubParameter();
      StubParameter to = new StubParameter();

      CommandParametersParser parser = new CommandParametersParser(test,
          new ParameterOptions(body)
              .put("/from", from)
              .put("/to", to));
      assertDoesNotThrow(parser::parse);

      assertEquals("one", body.value(), formatMessage(test));
      assertEquals("two", from.value(), formatMessage(test));
      assertEquals("three", to.value(), formatMessage(test));
    }
  }

  @Test
  void parametersShouldBeWords() {
    String[] tests = {
        "one two three /from four five six /to seven eight nine",
        " one two three /from four five six /to seven eight nine ",
        "  one two three  /from  four five six  /to  seven eight nine  ",
        "   one two three   /from   four five six   /to   seven eight nine   "
    };

    for (String test : tests) {
      StubParameter body = new StubParameter();
      StubParameter from = new StubParameter();
      StubParameter to = new StubParameter();

      CommandParametersParser parser = new CommandParametersParser(test,
          new ParameterOptions(body)
              .put("/from", from)
              .put("/to", to));
      assertDoesNotThrow(parser::parse);

      assertEquals("one two three", body.value(), formatMessage(test));
      assertEquals("four five six", from.value(), formatMessage(test));
      assertEquals("seven eight nine", to.value(), formatMessage(test));
    }
  }

  @Test
  void parametersShouldPreserveSpaces() {
    String[] tests = {
        "one   two   three /from four   five   six /to seven   eight   nine",
        " one   two   three /from four   five   six /to seven   eight   nine ",
        "  one   two   three  /from  four   five   six  /to  seven   eight   nine  ",
        "   one   two   three   /from   four   five   six   /to   seven   eight   nine   "
    };

    for (String test : tests) {
      StubParameter body = new StubParameter();
      StubParameter from = new StubParameter();
      StubParameter to = new StubParameter();

      CommandParametersParser parser = new CommandParametersParser(test,
          new ParameterOptions(body)
              .put("/from", from)
              .put("/to", to));
      assertDoesNotThrow(parser::parse);

      assertEquals("one   two   three", body.value(), formatMessage(test));
      assertEquals("four   five   six", from.value(), formatMessage(test));
      assertEquals("seven   eight   nine", to.value(), formatMessage(test));
    }
  }

  @Test
  void parametersOrderDoesNotMatter() {
    String[] tests = {
        "one two /a three four /b five six /c seven eight",
        "one two /b five six /c seven eight /a three four",
        "one two /c seven eight /a three four /b five six",
    };

    for (String test : tests) {
      StubParameter body = new StubParameter();
      StubParameter a = new StubParameter();
      StubParameter b = new StubParameter();
      StubParameter c = new StubParameter();

      CommandParametersParser parser = new CommandParametersParser(test,
          new ParameterOptions(body)
              .put("/a", a)
              .put("/b", b)
              .put("/c", c));
      assertDoesNotThrow(parser::parse);

      assertEquals("one two", body.value(), formatMessage(test));
      assertEquals("three four", a.value(), formatMessage(test));
      assertEquals("five six", b.value(), formatMessage(test));
      assertEquals("seven eight", c.value(), formatMessage(test));
    }
  }

  private String formatMessage(String input) {
    return String.format("Input: '%s'", input);
  }
}
