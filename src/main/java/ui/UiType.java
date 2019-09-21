package ui;

import ui.cli.ClInput;
import ui.cli.ClOutput;
import ui.fx.FxDukeInput;
import ui.fx.FxDukeOutput;

import java.util.function.Supplier;

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
