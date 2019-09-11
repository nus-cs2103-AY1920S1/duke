package duke.command;

import duke.io.Storage;
import duke.io.Ui;
import duke.location.Location;
import duke.location.LocationList;
import duke.task.TaskList;

import java.text.ParseException;

public class PlaceCommand extends Command {
    private String input;

    public PlaceCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws ParseException {
        LocationList locations = Location.getLocations();
        locations.add(new Location(input));
        ui.out("Got it. I've added this place:");
        ui.out(locations.get(locations.size() - 1).toString());
        ui.out("Now you have " + locations.size() + " places in the list.");
    }
}
