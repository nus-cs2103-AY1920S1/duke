package ui;

/**
 * Factory to create instances of UiControllers based on the set of available configurations specified in the UiType
 * enum. This allows other components using the Ui controller to not have to be concerned about its implementation and
 * instantiating the necessary dependencies.
 */
public class UiControllerFactory {
    /**
     * Creates an instance of a UiController based on the type specified by the client.
     * @param driver the driver component that wishes to utilize the ui controller.
     * @param type the type of Ui to be created.
     * @return a UiController instance for the corresponding Ui type that is selected.
     */
    public static UiController createUiController(UiDriver driver, UiType type) {
        return new UiController(type.input.get(), type.output.get(), driver);
    }
}
