package ui;

import error.ui.UiException;
import error.ui.UiInitializationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

class UiTest {

    private StubUiDriver stubUiDriver = new StubUiDriver();


    @Test
    void controllerShouldNotDisplayMessageIfUninitialized() {
        Ui ui = new Ui(new StubInput(null), new StubOutput(), stubUiDriver);
        UiOutputAccessor outputAccessor = ui.getUiOutputAccessor();

        Assertions.assertThrows(UiException.class, () -> {
          outputAccessor.displayOutput("hello");
       });
    }

    @Test
    void receiveInput() throws UiInitializationException {
        stubUiDriver.setExpectedInput("ABCDE12345");
        Ui ui = new Ui(new StubInput("ABCDE12345"), new StubOutput(), stubUiDriver);
        ui.initializeUi();
    }

    @Test
    void displayOutput() throws UiException, UiInitializationException {
        StubOutput output = new StubOutput();
        Ui ui = new Ui(new StubInput(null), output, stubUiDriver);
        UiOutputAccessor outputAccessor = ui.getUiOutputAccessor();
        ui.initializeUi();
        outputAccessor.displayOutput("1");
        outputAccessor.displayOutput("2");
        outputAccessor.displayOutput("3");

        Assertions.assertEquals(output.getReceivedOutputs().get(0), "1");
        Assertions.assertEquals(output.getReceivedOutputs().get(1), "2");
        Assertions.assertEquals(output.getReceivedOutputs().get(2), "3");
    }

    @Test
    void initializeUi() throws UiInitializationException {
        StubInput input = new StubInput(null);
        StubOutput output = new StubOutput();
        Ui ui = new Ui(input, output, stubUiDriver);
        ui.initializeUi();

        Assertions.assertTrue(ui.isUiInitialized());
        Assertions.assertTrue(input.isOpen);
        Assertions.assertTrue(output.isOpen);
    }

    @Test
    void stopUi() throws UiInitializationException {
        StubInput input = new StubInput(null);
        StubOutput output = new StubOutput();
        Ui ui = new Ui(input, output, stubUiDriver);
        ui.initializeUi();

        ui.stopUi();

        CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS).execute(() -> {
            Assertions.assertFalse(input.isOpen);
            Assertions.assertFalse(output.isOpen);
        });
    }
}