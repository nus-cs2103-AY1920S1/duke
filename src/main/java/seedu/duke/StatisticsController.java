package seedu.duke;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class StatisticsController implements Initializable {

    private Stage stage;
    private Scene scene;

    @FXML
    private BarChart<?, ?> taskChart;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;

    /**
     * Sets the stage
     *
     * @param stage the stage to be set
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Sets the scene
     *
     * @param scene the scene to be set
     */
    public void setScene(Scene scene) {
        this.scene = scene;
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        XYChart.Series set1 = new XYChart.Series<>();

        set1.getData().add(new XYChart.Data("Done", TaskList.numTaskDone));
        set1.getData().add(new XYChart.Data("Not Done", TaskList.numTaskNotDone));
        set1.getData().add(new XYChart.Data("Done Last Week", TaskList.numDonePastWeek));
        set1.getData().add(new XYChart.Data("Done Last Month", TaskList.numDonePastMonth));
        set1.getData().add(new XYChart.Data("Done Last Year", TaskList.numDonePastYear));

        taskChart.getData().addAll(set1);
    }

}
