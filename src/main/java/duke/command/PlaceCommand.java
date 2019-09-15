package duke.command;

import duke.io.Storage;
import duke.io.Ui;
import duke.place.Place;
import duke.place.PlaceList;
import duke.task.TaskList;

public class PlaceCommand extends Command {
    private String input;

    public PlaceCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        PlaceList locations = Place.getList();
        locations.add(new Place(input));
        ui.out("Got it. I've added this place:");
        ui.out(locations.get(locations.size() - 1).toString());
        ui.out("Now you have " + locations.size() + " places in the list.");
    }
}
