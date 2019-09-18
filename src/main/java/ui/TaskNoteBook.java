package ui;

import Notes.Notes;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class TaskNoteBook extends TableView {
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
    private DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd/MM/yy HHmm");

    public TaskNoteBook() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(NewGUI.class.getResource("/view/TaskNoteBook.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        dateCol.setCellFactory(new ColumnFormatter<>(outputFormat));
        dateCreatedCol.setCellValueFactory(new PropertyValueFactory<>("dateCreated"));
        dateCreatedCol.setCellFactory(new ColumnFormatter<>(outputFormat));
    }

    public TableView<Notes> getCashFlowTableView(){
        return taskNoteBook;
    }

    public void setCashFlowTableView(ObservableList<Notes> noteBook){
        taskNoteBook.setItems(noteBook);
    }

    private static class ColumnFormatter<S, T> implements Callback<TableColumn<S, T>, TableCell<S, T>> {

        private final DateTimeFormatter format;

        ColumnFormatter(DateTimeFormatter format) {
            super();
            this.format = format;
        }

        @Override
        public TableCell<S, T> call(TableColumn<S, T> arg0) {
            return new TableCell<S, T>() {
                @Override
                protected void updateItem(T item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setGraphic(null);
                    } else {
                        LocalDateTime ld = (LocalDateTime) item;
                        String val = ld.format(format);
                        setGraphic(new Label(val));
                    }
                }
            };
        }
    }
}



