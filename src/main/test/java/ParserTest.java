import duke.command.CommandNotFoundException;
import duke.parser.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
	
	@Test
	public void parserTest_incorrectCommand_throwException() {
		assertThrows(CommandNotFoundException.class, () -> Parser.parse("eijfioj"));
	}
	
}
