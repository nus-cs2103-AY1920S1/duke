package tasklist;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import javafx.beans.property.*;
import notes.Note;
import ui.TextUi;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


@JsonTypeInfo(use = Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @Type(value = Todo.class, name = "Todo"),
        @Type(value = Deadline.class, name = "Deadline"),
        @Type(value = Event.class, name = "Event"),
        @Type(value = Notebook.class, name = "Notebook")
})
/**
 * Abstract class for all tasks.
 * gurantees that all important methods are implemented
 */
public abstract class Task {
    protected Integer index;
    protected SimpleStringProperty taskType;
    protected SimpleStringProperty description;
    protected SimpleBooleanProperty isDone;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm", timezone = "UTC")
    protected ObjectProperty<LocalDateTime> dateDue;
    protected ArrayList<Note> notes = new ArrayList<>();
    protected TextUi ui = new TextUi();
    protected static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("dd MMMM hhmm a");

    /**
     * First of two constructors for deadline and event tasks.
     */
    public Task(int index, String description, boolean completionStatus, LocalDateTime date) {
        this.index = index;
        this.description = new SimpleStringProperty(description);
        this.isDone = new SimpleBooleanProperty(completionStatus);
        this.dateDue = new SimpleObjectProperty<>(date);
    }

    /**
     * Returns the current status of the task.
     * @return returns either a tick or cross according to the completion status
     */
    @JsonIgnore
    public String getCurrentStatus() {
        return ((isDone.getValue() ? "[1] " : "[0] ")); //return tick or X symbols
    }

    /**
     * overall status of task that includes name,completion status, description and date(if applicable).
     * to be implemented according to task type.
     * @return the overall status in a assignment correct format
     */
    @JsonIgnore
    public abstract String getOverallStatus();

    /**
     * changes task status to done.
     */
    public void completeTask() {
        isDone.setValue(true);
    }

    /**
     * adds a note to a particular task or notebook.
     * @param category contains the category type of the note
     * @param description contains the actual note
     * @param date contains an optional date
     */
    public void addNote(String category, String description,LocalDateTime date) {
        int index = notes.size() + 1;
        notes.add(new Note(index,getSourceName(),category, description, date));
        ui.printAddedItem(category + " " + description + " " + date, notes.size());
    }

    /**
     * Method to remove the note or all the notes of a designated task or notebook.
     * @param index contains command indicating whether all tasks are to be deleted or 1 particular one
     */
    public void removeNote(int index) {
        if (index == -1) {
            notes.clear();
            ui.printRemovedItem("All entries", 0);
        } else {
            ui.printNoteRemoved(notes.get(index).getDescription(),
                    notes.get(index).getSource(),
                    notes.size() - 1);
            notes.remove(index);
            updateNoteIndex();
        }
    }
    public void updateNoteIndex(){
        if (!notes.isEmpty()) {
            int i = 1;
            for (Note note : notes) {
                note.setIndex(i);
                note.setSource(getSourceName());
                i++;
            }
        }
    }

    @JsonIgnore
    public String getSourceName(){
        return "[" + index + "] " + description.get() ;
    }


    public String getTaskType() {
        return taskType.get();
    }

    public SimpleStringProperty taskTypeProperty() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType.set(taskType);
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public boolean isIsDone() {
        return isDone.get();
    }

    public SimpleBooleanProperty isDoneProperty() {
        return isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone.set(isDone);
    }

    public LocalDateTime getDateDue() {
        return dateDue.get();
    }

    public ObjectProperty<LocalDateTime> dateDueProperty() {
        return dateDue;
    }

    public void setDateDue(LocalDateTime dateDue) {
        this.dateDue.set(dateDue);
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }


}