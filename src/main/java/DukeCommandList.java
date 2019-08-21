public class DukeCommandList extends DukeCommand {

    /**
     * This method will list the current existing {@link DukeTask} from {@link DukeTaskList}.
     * @param tasks Instance of {@link DukeTaskList} which contains an existing list of {@link DukeTask}.
     * @param ui Instance of {@link DukeUi} which will show output to the user.
     * @param storage Instance of {@link DukeStorage} which will save the {@link DukeTaskList} to the hard disk.
     */
    @Override
    public void execute(DukeTaskList tasks, DukeUi ui, DukeStorage storage) {
        tasks.displayDukeTasks(ui);
    }
}
