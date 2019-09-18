package ui;

import notes.Notes;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class TaskNoteBook extends DateTable {
    @FXML
    private TableView<Notes> taskNoteBook;
    @FXML
    private TableColumn<Notes, String> categoryCol;
    @FXML
    private TableColumn<Notes, String> descriptionCol;
    @FXML
    private TableColumn<Notes, LocalDateTime> dateCol;
    @FXML
    private TableColumn<Notes, LocalDateTime> dateCreatedCol;


    /**
     * Constructor for to create a tasknotebook table that display notes within a task or notebook.
     * @param filepath contains the filepath to the FXML file
     */
    public TaskNoteBook(String filepath) {
        super(filepath);
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        dateCol.setCellFactory(new ColumnFormatter<>(outputFormat));
        dateCreatedCol.setCellValueFactory(new PropertyValueFactory<>("dateCreated"));
        dateCreatedCol.setCellFactory(new ColumnFormatter<>(outputFormat));
    }

    public TableView<Notes> getTaskNoteBook() {
        return taskNoteBook;
    }

    public void setTaskNoteBook(ObservableList<Notes> noteBook) {
        taskNoteBook.setItems(noteBook);
    }

}



