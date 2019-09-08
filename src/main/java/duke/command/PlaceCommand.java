package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import place.Place;

public class PlaceCommand extends Command {
    private String alias;
    private double latitude;
    private double longitude;

    /**
     * Initialises PlaceCommand.
     *
     * @param alias Alias as String
     * @param latitude Latitude as double
     * @param longitude Longitude as double
     */
    public PlaceCommand(String alias, double latitude, double longitude) {
        this.alias = alias;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Adds place if does not exists.
     * Then updates storage.
     *
     * @param tasks   duke.TaskList
     * @param ui      duke.Ui
     * @param storage duke.Storage
     * @throws DukeException when problems with duke.Storage writing
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Place p = Place.addIfAbsent(alias, latitude, longitude);
        storage.rewrite(tasks);
        ui.show("Noted. I have recorded " + alias + p.toString());
    }
}
