package ui;

import notes.Note;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.time.LocalDateTime;


public class TaskNoteBook extends DateTable {
    @FXML
    private TableView<Note> taskNoteBook;
    @FXML
    private TableColumn<Note, Integer> indexCol;
    @FXML
    private TableColumn<Note, String> sourceCol;
    @FXML
    private TableColumn<Note, String> categoryCol;
    @FXML
    private TableColumn<Note, String> descriptionCol;
    @FXML
    private TableColumn<Note, LocalDateTime> dateCol;
    @FXML
    private TableColumn<Note, LocalDateTime> dateCreatedCol;


    /**
     * Constructor for to create a tasknotebook table that display notes within a task or notebook.
     * @param filepath contains the filepath to the FXML file
     */
    public TaskNoteBook(String filepath) {
        super(filepath);
        indexCol.setCellValueFactory(new PropertyValueFactory<>("index"));
        sourceCol.setCellValueFactory(new PropertyValueFactory<>("source"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        dateCol.setCellFactory(new ColumnFormatter<>(outputFormat));
        dateCreatedCol.setCellValueFactory(new PropertyValueFactory<>("dateCreated"));
        dateCreatedCol.setCellFactory(new ColumnFormatter<>(outputFormat));
    }

    public TableView<Note> getTaskNoteBook() {
        return taskNoteBook;
    }

    public void setTaskNoteBook(ObservableList<Note> noteBook) {
        taskNoteBook.setItems(noteBook);
    }

}



