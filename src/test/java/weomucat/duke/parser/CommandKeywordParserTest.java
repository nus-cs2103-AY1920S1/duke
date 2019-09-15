package weomucat.duke.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import weomucat.duke.exception.UnknownCommandException;

public class CommandKeywordParserTest {

  @Test
  void invalidUsage() {
    String[] tests = {"", " ", "one", "two", "three"};

    for (String test : tests) {
      assertThrows(UnknownCommandException.class, () -> new CommandKeywordParser(test).parse());
    }
  }
}
