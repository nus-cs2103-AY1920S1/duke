package ui;

import error.ui.UiException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

class UiControllerTest {

    private StubUiDriver stubUiDriver = new StubUiDriver();


    @Test
    void controllerShouldNotDisplayMessageIfUninitialized() {
        UiController controller = new UiController(new StubInput(null), new StubOutput(), stubUiDriver);
        UiOutputAccessor outputAccessor = controller.getUiOutputAccessor();

        Assertions.assertThrows(UiException.class, () -> {
          outputAccessor.displayOutput("hello");
       });
    }

    @Test
    void receiveInput() {
        stubUiDriver.setExpectedInput("ABCDE12345");
        UiController controller = new UiController(new StubInput("ABCDE12345"), new StubOutput(), stubUiDriver);
        controller.initializeUi();
    }

    @Test
    void displayOutput() throws UiException {
        StubOutput output = new StubOutput();
        UiController controller = new UiController(new StubInput(null), output, stubUiDriver);
        UiOutputAccessor outputAccessor = controller.getUiOutputAccessor();
        controller.initializeUi();
        outputAccessor.displayOutput("1");
        outputAccessor.displayOutput("2");
        outputAccessor.displayOutput("3");

        Assertions.assertEquals(output.getReceivedOutputs().get(0), "1");
        Assertions.assertEquals(output.getReceivedOutputs().get(1), "2");
        Assertions.assertEquals(output.getReceivedOutputs().get(2), "3");
    }

    @Test
    void initializeUi() {
        StubInput input = new StubInput(null);
        StubOutput output = new StubOutput();
        UiController controller = new UiController(input, output, stubUiDriver);
        controller.initializeUi();

        Assertions.assertTrue(controller.isUiInitialized());
        Assertions.assertTrue(input.isOpen);
        Assertions.assertTrue(output.isOpen);
    }

    @Test
    void stopUi() {
        StubInput input = new StubInput(null);
        StubOutput output = new StubOutput();
        UiController controller = new UiController(input, output, stubUiDriver);
        controller.initializeUi();

        controller.stopUi();

        CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS).execute(() -> {
            Assertions.assertFalse(input.isOpen);
            Assertions.assertFalse(output.isOpen);
        });
    }
}