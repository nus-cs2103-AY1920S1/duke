package cs2103t.duke.command;

import cs2103t.duke.exception.DukeException;
import cs2103t.duke.file.Storage;
import cs2103t.duke.parse.Parser;
import cs2103t.duke.task.Note;
import cs2103t.duke.task.NoteList;
import cs2103t.duke.task.Task;
import cs2103t.duke.task.TaskList;
import cs2103t.duke.ui.Ui;

import java.util.Scanner;

/**
 * Represents a command that lists notes. Notes can be general (not tagged to any task) or specific (tagged to task).
 */
public class AddNotesCommand extends Command {
    private boolean isGeneralNote;
    private int taskIdBoundTo;
    private String noteContent;

    /**
     * Constructs a list command.
     */
    public AddNotesCommand(String fulldescr) {//String n, int taskId) {
        Scanner sc = new Scanner(fulldescr);
        String taskIdStr = sc.next();
        this.isGeneralNote = false;
        int taskId = 0;
        String n = fulldescr;
        try {
            taskId = Parser.parseStrToInt(taskIdStr);
            n = sc.nextLine();
        } catch (DukeException e) {
            this.isGeneralNote = true;
        }
        sc.close();

        super.isExit = false;
        this.noteContent = n;
        this.taskIdBoundTo = taskId;
    }

    /**
     * Lists all general notes.
     * @param tasks TaskList agent to handle list of tasks.
     * @param ui Ui in charge of printing.
     * @param storageTasks Storage agent in charge of writing to file.
     * @param storageNotes
     * @param noteList NoteList agent to handle list of notes.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storageTasks, Storage storageNotes, NoteList noteList) {
        String val;
        if (isGeneralNote) {
            val = addGeneralNotes(ui, noteList, noteContent);
        } else {
            val = addNotesToTask(ui, noteList, noteContent, tasks, taskIdBoundTo);
        }
        storageNotes.updateFileWithNote(noteList);
        return val;
    }

    private String addNotesToTask(Ui ui, NoteList noteList, String n, TaskList tasks, int taskId) {
        //////CHECK TASK IF IS LEGIT/////

        Task t = tasks.retrieveTask(taskId);
        Note note = new Note(n, true);
        t.setNotes(note, noteList.getNoteListSize());
        noteList.addNote(note);

        return ui.dukeRespond("Got it. I've added this note:",
                "  " + note.toString(),
                "to this task:",
                t.toString(),
                "You can view it when you search/find a task.");
    }

    private String addGeneralNotes(Ui ui, NoteList noteList, String n) {
        Note note = new Note(n);
        noteList.addNote(note);
        noteList.addGeneralNote(note);
        return ui.dukeRespond("Got it. I've added this general note:",
                "  " + note.toString(),
                "Enter command: <listnotes> to list all general notes you've added.");
    }
}
