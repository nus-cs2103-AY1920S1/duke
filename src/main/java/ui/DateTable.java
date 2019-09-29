package ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Control;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.util.Callback;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTable extends TableView {

    DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public DateTable(String filepath) {
        loadFxml(filepath);
    }


    private void loadFxml(String filepath) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(NewGui.class.getResource(filepath));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //@@author lzw12345-reused
    //reused from https://stackoverflow.com/questions/42979872/formatting-date-column-in-javafx-tableview-for-data-retrieved-from-oracle-db
    //with minor modifications
    static class ColumnFormatter<S, T> implements Callback<TableColumn<S, T>, TableCell<S, T>> {

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
    //@@author

    //@@author lzw12345 - reused
    //reused from https://stackoverflow.com/questions/54900311/javafx-how-to-make-text-wrap-inside-a-tablecolumn-in-tableview
    //with minor modifications
    static class TextWrapFormatter<S, T> implements Callback<TableColumn<S, T>, TableCell<S, T>> {

        TextWrapFormatter() {
            super();
        }

        @Override
        public TableCell<S, T> call(TableColumn<S, T> arg0) {
            TableCell<S, String> cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(arg0.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return (TableCell<S, T>) cell;
        }
    }
    //@@author

}
