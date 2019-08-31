package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;

public class MainWindowController {

    @FXML
    private MenuBar menuBar;
    @FXML
    private AnchorPane masterPane;
    @FXML
    private ListView<String> masterListView;
    @FXML
    private ScrollPane listPane;
    @FXML
    private Button randomButton;
    @FXML
    private AnchorPane detailPane;
    @FXML
    private TextArea middleTextArea;
    @FXML
    private ListView<Task> middleListView;
    @FXML
    private MenuItem fileNew;
    @FXML
    private MenuItem fileOpen;
    private Duke duke;

    public void setDuke(Duke duke) {
        this.duke = duke;
        displayTaskList.addAll(duke.getTasks());
        middleListView.setItems(displayTaskList);
    }

    protected Duke getDuke() {
        return duke;
    }

    protected void refreshView() {
        switch (masterListView.getSelectionModel().getSelectedItem()) {
        case "To-Do":
            displayTaskList.setAll(duke.getTodos());
            break;
        case "Deadline":
            displayTaskList.setAll(duke.getDeadlines());
            break;
        case "Event":
            displayTaskList.setAll(duke.getEvent());
            break;
        case "All":
            displayTaskList.setAll(duke.getTasks());
            break;
        default:
            break;
        }
    }

    private class CustomListCell extends ListCell<Task> {
        private HBox content;
        private Text title;
        private Text subtitle;
        private Label hboxLead;

        public CustomListCell() {
            super();
            title = new Text();
            subtitle = new Text();
            VBox vBox = new VBox(title, subtitle);
            hboxLead = new Label("Status");
            content = new HBox(hboxLead, vBox);
            content.setSpacing(10);
        }

        @Override
        protected void updateItem(Task item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null && !empty) { // <== test for null item and empty parameter
                if (item instanceof Deadline) {
                    subtitle.setText("by " + ((Deadline) item).getBy().toString());
                } else if (item instanceof Event) {
                    subtitle.setText("at " + ((Event) item).getAt().toString());
                } else {
                    subtitle.setText("To-Do");
                }
                title.setText(item.getDescription());
                hboxLead.setText(item.getStatusIcon());
                setGraphic(content);
            } else {
                setGraphic(null);
            }
        }
    }

    private ObservableList<Task> displayTaskList;

    public MainWindowController() {
        displayTaskList = FXCollections.observableArrayList();
    }

    @FXML
    private void initialize() {
        middleListView.setCellFactory(new Callback<ListView<Task>, ListCell<Task>>() {
            @Override
            public ListCell<Task> call(ListView<Task> param) {

                ListCell<Task> cell = new CustomListCell();
                ContextMenu contextMenu = new ContextMenu();
                MenuItem deleteItem = new MenuItem();
                deleteItem.setText("Delete");
                deleteItem.setOnAction(event -> {
                    duke.getTasks().remove(cell.getItem());
                    try {
                        duke.updateStorage();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    refreshView();
                });
                contextMenu.getItems().add(deleteItem);
                cell.emptyProperty().addListener((obs, wasEmpty, isNowEmpty) -> {
                    if (isNowEmpty) {
                        cell.setContextMenu(null);
                    } else {
                        cell.setContextMenu(contextMenu);
                    }
                });

                return cell;
            }
        });
        ObservableList<String> items = FXCollections.observableArrayList(
                "To-Do", "Deadline", "Event", "All");
        masterListView.setItems(items);
        masterListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                refreshView();
            }
        });
        masterListView.getSelectionModel().select(3);
    }

    @FXML
    void addListItem(ActionEvent event) {

    }

    @FXML
    void createNewTask(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(Launcher.class.getResource("/view/NewTaskWindow.fxml"));
            Parent root = loader.load();
            loader.<NewTaskWindowController>getController().setParentController(this);
            Stage stage = new Stage();
            stage.setTitle("Create New Task");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void openFile(ActionEvent event) {
        System.out.println("open");
    }

}
