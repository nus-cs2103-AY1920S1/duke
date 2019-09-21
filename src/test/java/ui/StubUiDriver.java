package ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StubUiDriver implements UiDriver {
    String expectedInput;

    public void setExpectedInput(String input) {
        this.expectedInput = input;
    }

    @Override
    public void receiveUserInput(String input) {
        if (expectedInput != null) {
            assertEquals(input, expectedInput);
        }
    }
}
