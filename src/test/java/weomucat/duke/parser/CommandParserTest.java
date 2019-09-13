package weomucat.duke.parser;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import weomucat.duke.command.parameter.ParameterOptions;
import weomucat.duke.command.parameter.StringParameter;

class CommandParserTest {

  @Test
  void commandShouldBeEmptyString() {
    String[] tests = {"", " ", "  ", "   "};

    for (String test : tests) {
      CommandParser parser = new CommandParser(test);
      String command = parser.getCommand();

      assertEquals("", command, formatMessage(test));
    }
  }

  @Test
  void commandShouldBeOneWord() {
    String[] tests = {
        "todo", " todo ", "  todo  ", "   todo   ",
        "todo one", " todo one ", "  todo  one  ", "   todo   one   "
    };

    for (String test : tests) {
      CommandParser parser = new CommandParser(test);
      String command = parser.getCommand();

      assertEquals("todo", command, formatMessage(test));
    }
  }

  @Test
  void parametersShouldBeNull() {
    String[] tests = {
        "event", " event ", "  event  ", "   event   "
    };

    for (String test : tests) {
      CommandParser parser = new CommandParser(test);
      String command = parser.getCommand();

      StringParameter body = new StringParameter("", false);
      StringParameter from = new StringParameter("", false);
      StringParameter to = new StringParameter("", false);
      assertDoesNotThrow(() -> parser.parse(new ParameterOptions(body)
          .put("/from", from)
          .put("/to", to)));

      assertEquals("event", command, formatMessage(test));
      assertEquals("", body.value(), formatMessage(test));
      assertNull(from.value(), formatMessage(test));
      assertNull(to.value(), formatMessage(test));
    }
  }

  @Test
  void parametersShouldBeEmptyString() {
    String[] tests = {
        "event /from /to", " event /from /to ", "  event  /from  /to  ", "   event   /from   /to   "
    };

    for (String test : tests) {
      CommandParser parser = new CommandParser(test);
      String command = parser.getCommand();

      StringParameter body = new StringParameter("Body", false);
      StringParameter from = new StringParameter("From", false);
      StringParameter to = new StringParameter("To", false);
      assertDoesNotThrow(() -> parser.parse(new ParameterOptions(body)
          .put("/from", from)
          .put("/to", to)));

      assertEquals("event", command, formatMessage(test));
      assertEquals("", body.value(), formatMessage(test));
      assertEquals("", from.value(), formatMessage(test));
      assertEquals("", to.value(), formatMessage(test));
    }
  }

  @Test
  void parametersShouldBeOneWord() {
    String[] tests = {
        "todo one /from two /to three", " todo one /from two /to three ",
        "  todo  one  /from  two  /to  three  ", "   todo   one   /from   two   /to   three   "
    };

    for (String test : tests) {
      CommandParser parser = new CommandParser(test);
      String command = parser.getCommand();

      StringParameter body = new StringParameter("", true);
      StringParameter from = new StringParameter("", true);
      StringParameter to = new StringParameter("", true);
      assertDoesNotThrow(() -> parser.parse(new ParameterOptions(body)
          .put("/from", from)
          .put("/to", to)));

      assertEquals("todo", command, formatMessage(test));
      assertEquals("one", body.value(), formatMessage(test));
      assertEquals("two", from.value(), formatMessage(test));
      assertEquals("three", to.value(), formatMessage(test));
    }
  }

  @Test
  void parametersShouldBeWords() {
    String[] tests = {
        "todo one two three /from four five six /to seven eight nine",
        " todo one two three /from four five six /to seven eight nine ",
        "  todo  one two three  /from  four five six  /to  seven eight nine  ",
        "   todo   one two three   /from   four five six   /to   seven eight nine   "
    };

    for (String test : tests) {
      CommandParser parser = new CommandParser(test);
      String command = parser.getCommand();

      StringParameter body = new StringParameter("", true);
      StringParameter from = new StringParameter("", true);
      StringParameter to = new StringParameter("", true);
      assertDoesNotThrow(() -> parser.parse(new ParameterOptions(body)
          .put("/from", from)
          .put("/to", to)));

      assertEquals("todo", command, formatMessage(test));
      assertEquals("one two three", body.value(), formatMessage(test));
      assertEquals("four five six", from.value(), formatMessage(test));
      assertEquals("seven eight nine", to.value(), formatMessage(test));
    }
  }

  @Test
  void parametersShouldPreserveSpaces() {
    String[] tests = {
        "todo one   two   three /from four   five   six /to seven   eight   nine",
        " todo one   two   three /from four   five   six /to seven   eight   nine ",
        "  todo  one   two   three  /from  four   five   six  /to  seven   eight   nine  ",
        "   todo   one   two   three   /from   four   five   six   /to   seven   eight   nine   "
    };

    for (String test : tests) {
      CommandParser parser = new CommandParser(test);
      String command = parser.getCommand();

      StringParameter body = new StringParameter("", true);
      StringParameter from = new StringParameter("", true);
      StringParameter to = new StringParameter("", true);
      assertDoesNotThrow(() -> parser.parse(new ParameterOptions(body)
          .put("/from", from)
          .put("/to", to)));

      assertEquals("todo", command, formatMessage(test));
      assertEquals("one   two   three", body.value(), formatMessage(test));
      assertEquals("four   five   six", from.value(), formatMessage(test));
      assertEquals("seven   eight   nine", to.value(), formatMessage(test));
    }
  }

  @Test
  void parametersOrderDoesNotMatter() {
    String[] tests = {
        "todo one two /a three four /b five six /c seven eight",
        "todo one two /b five six /c seven eight /a three four",
        "todo one two /c seven eight /a three four /b five six",
    };

    for (String test : tests) {
      CommandParser parser = new CommandParser(test);
      String command = parser.getCommand();

      StringParameter body = new StringParameter("", true);
      StringParameter a = new StringParameter("", true);
      StringParameter b = new StringParameter("", true);
      StringParameter c = new StringParameter("", true);
      assertDoesNotThrow(() -> parser.parse(new ParameterOptions(body)
          .put("/a", a)
          .put("/b", b)
          .put("/c", c)));

      assertEquals("todo", command, formatMessage(test));
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
