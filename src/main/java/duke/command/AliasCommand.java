package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;
import duke.exception.AliasParseDukeException;
import place.Place;

public class AliasCommand extends Command {
    private String[] args;

    public AliasCommand(String str) {
        args = str.trim().split("\\s+");
    }

    /**
     * Tries to search place for alias with 2 args.
     * If not found, tries with 1 args.
     * If still not found, then Place does not exist.
     *
     * @param tasks   duke.TaskList
     * @param ui      duke.ui.Ui
     * @param storage duke.Storage
     * @throws AliasParseDukeException When 2 args cannot be parsed as latitude and longitude
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AliasParseDukeException {
        Place p = null;
        if (args.length >= 2) {
            try {
                p = Place.of(Double.parseDouble(args[0]), Double.parseDouble(args[1]));
            } catch (Exception e) {
                throw new AliasParseDukeException();
            }
        }
        if (p == null) {
            p = Place.of(args[0]);
        }
        if (p == null) {
            ui.show("No matching place found with given arguments.");
        } else {
            ui.show(p.aliases());
        }
    }
}
