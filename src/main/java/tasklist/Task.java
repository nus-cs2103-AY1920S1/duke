package tasklist;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import Notes.Notes;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    protected SimpleStringProperty taskType;
    protected SimpleStringProperty description;
    protected SimpleBooleanProperty isDone;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm", timezone = "UTC")
    protected ObjectProperty<LocalDateTime> dateDue;
    protected ArrayList<Notes> noteBook = new ArrayList<>();
    protected TextUi ui = new TextUi();
    protected static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("dd MMMM hhmm a");

    /**
     * First of two constructors for deadline and event tasks.
     */
    public Task(String description, boolean completionStatus, LocalDateTime date) {
        this.description = new SimpleStringProperty(description);
        this.isDone = new SimpleBooleanProperty(completionStatus);
        this.dateDue = new SimpleObjectProperty<>(date);
    }

    /**
     * Second of two constructors for todo tasks.
     */
    public Task(String description, boolean completionStatus) {
        this.description = new SimpleStringProperty(description);
        this.isDone = new SimpleBooleanProperty(completionStatus);
    }

    /**
     * Returns the current status of the task.
     * @return returns either a tick or cross according to the completion status
     */
    @JsonIgnore
    public String getCurrentStatus() {
        return ((isDone.getValue() ? "[✓] " : "[✗] ")); //return tick or X symbols
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


    public void addNote(String category, String description,LocalDateTime dateDue){
        noteBook.add(new Notes(category, description, dateDue));
        ui.printAddedItem(category + " " + description + " " + dateDue, noteBook.size());
    }

    public void removeNote(String noteToRemove){
        if (noteToRemove.contains("all")){
            noteBook.clear();
            ui.printRemovedItem("All entries", noteBook.size()+1);
        }else {
            int NoteToDelete = Integer.parseInt(noteToRemove);
            noteBook.remove(NoteToDelete - 1);
        }
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

    public ObservableList<Notes> getNoteBook() {
        return FXCollections.observableArrayList(noteBook);
    }

}