package ui;

import financedata.CashFlow;
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


public class TaskCashFlows extends TableView {
    @FXML
    private TableView<CashFlow> cashFlowTableView;
    @FXML
    private TableColumn<CashFlow, String> descriptionCol;
    @FXML
    private TableColumn<CashFlow, Double> valueCol;
    @FXML
    private TableColumn<CashFlow, LocalDateTime> dateDueCol;
    @FXML
    private TableColumn<CashFlow, LocalDateTime> dateCreatedCol;
    private DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd/MM/yy HHmm");

    public TaskCashFlows() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(NewGUI.class.getResource("/view/TaskCashFlows.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //cashFlowTableView.setItems(cashFlows);
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("sourceDescription"));
        valueCol.setCellValueFactory(new PropertyValueFactory<>("value"));
        dateDueCol.setCellValueFactory(new PropertyValueFactory<>("dateDue"));
        dateDueCol.setCellFactory(new ColumnFormatter<>(outputFormat));
        dateCreatedCol.setCellValueFactory(new PropertyValueFactory<>("dateDue"));
        dateCreatedCol.setCellFactory(new ColumnFormatter<>(outputFormat));
    }

    public TableView<CashFlow> getCashFlowTableView(){
        return cashFlowTableView;
    }

    public void setCashFlowTableView(ObservableList<CashFlow> cashFlows){
        cashFlowTableView.setItems(cashFlows);
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



