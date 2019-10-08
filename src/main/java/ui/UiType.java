package ui;

import ui.cli.ClInput;
import ui.cli.ClOutput;
import ui.fx.FxDukeInput;
import ui.fx.FxDukeOutput;

import java.util.function.Supplier;

/**
 * Enum used by UiController factory to configure which Input and Output channels to create for the program. This
 * allows clients of the UiController to not be concerned about its implementation details and not have to instantiate
 * the necessary dependencies of the UiController class themselves.
 */
public enum UiType {
    CLI(ClInput::new, ClOutput::new),
    JAVAFX(FxDukeInput::new, FxDukeOutput::new);

    public final Supplier<DukeInput> input;
    public final Supplier<DukeOutput> output;

    UiType(Supplier<DukeInput> input, Supplier<DukeOutput> output) {
        this.input = input;
        this.output = output;
    }
}
