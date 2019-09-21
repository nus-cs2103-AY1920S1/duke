package weomucat.doko.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import weomucat.doko.exception.UnknownCommandException;

public class CommandKeywordParserTest {

  @Test
  void invalidUsage() {
    String[] tests = {"", " ", "one", "two", "three"};

    for (String test : tests) {
      assertThrows(UnknownCommandException.class, () -> new CommandKeywordParser(test).parse());
    }
  }
}
