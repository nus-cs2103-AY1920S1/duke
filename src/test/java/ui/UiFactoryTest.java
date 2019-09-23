package ui;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

public class UiFactoryTest {
    @Test
    void testUiControllerFactory() throws NoSuchFieldException, IllegalAccessException {
        StubUiDriver driver = new StubUiDriver();

        for (UiType type : UiType.values()) {
            Ui controller = UiControllerFactory.createUiController(driver, type);

            Field dukeInputField = controller.getClass().getDeclaredField("inputChannel");
            Field dukeOutputField = controller.getClass().getDeclaredField("outputChannel");

            dukeInputField.setAccessible(true);
            dukeOutputField.setAccessible(true);

            DukeInput createdInput = (DukeInput) dukeInputField.get(controller);
            DukeOutput createdOutput = (DukeOutput) dukeOutputField.get(controller);

            Assertions.assertEquals(type.input.get().getClass(), createdInput.getClass());
            Assertions.assertEquals(type.output.get().getClass(), createdOutput.getClass());

        }
    }
}
