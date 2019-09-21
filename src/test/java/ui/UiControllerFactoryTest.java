package ui;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class UiControllerFactoryTest {
    @Test
    void testUiControllerFactory() {
        StubUiDriver driver = new StubUiDriver();

        Arrays.stream(UiType.values()).forEach(type -> {
            UiController controller = UiControllerFactory.createUiController(driver, type);

            Assertions.assertEquals(type.input.get().getClass(), controller.getInputChannel().getClass());
            Assertions.assertEquals(type.output.get().getClass(), controller.getOutputChannel().getClass());
        });
    }
}
