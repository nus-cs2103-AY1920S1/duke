package duke;

import duke.task.Task;
import duke.task.ToDo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;

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

    private Duke duke;

    public void setDuke(Duke duke) {
        this.duke = duke;
    }

    public void updateOutputArea(String s) {

    }

    private class CustomListCell extends ListCell<Task> {
        private HBox content;
        private Text name;
        private Text detail;

        public CustomListCell() {
            super();
            name = new Text();
            detail = new Text();
            VBox vBox = new VBox(name, detail);
            content = new HBox(new Label("[Graphic]"), vBox);
//            content.setSpacing(10);
        }

        @Override
        protected void updateItem(Task item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null && !empty) { // <== test for null item and empty parameter
                name.setText(item.getStatusIcon());
                detail.setText(item.getDescription());
                System.out.println("here1");
                setGraphic(content);
            } else {
                System.out.println("here2");
                setGraphic(null);
            }
        }
    }

    private ObservableList<Task> displayTaskList;

    public MainWindowController() {
        displayTaskList = FXCollections.observableArrayList();
        displayTaskList.add(new ToDo("des"));
    }

    @FXML
    private void initialize() {
//        middleListView = new ListView<>();
        middleListView.setItems(displayTaskList);
        middleListView.setCellFactory(new Callback<ListView<Task>, ListCell<Task>>() {
            @Override
            public ListCell<Task> call(ListView<Task> param) {
                return new CustomListCell();
            }
        });
        ObservableList<String> items = FXCollections.observableArrayList(
                "To-Do", "Deadline", "Event", "All");
        masterListView.setItems(items);
        masterListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                switch (masterListView.getSelectionModel().getSelectedItem()) {
                    case "To-Do":
                        middleTextArea.setText(duke.getTodos().toString());
                        break;
                    case "Deadline":
                        middleTextArea.setText(duke.getDeadlines().toString());
                        break;
                    case "Event":
                        middleTextArea.setText(duke.getEvent().toString());
                        break;
                    case "All":
                        middleTextArea.setText(duke.getTasks().toString());
                        break;
                    default:
                        break;
                }
                System.out.println("clicked on " + masterListView.getSelectionModel().getSelectedItem());
            }
        });
    }

    @FXML
    void addListItem(ActionEvent event) {
        System.out.println("clicked");
    }

}
