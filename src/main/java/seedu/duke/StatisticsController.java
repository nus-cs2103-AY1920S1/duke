package seedu.duke;

import javafx.fxml.FXML;

import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class StatisticsController {

    private Stage stage;
    private Scene scene;

    @FXML
    private BarChart<?, ?> taskChart;

    @FXML

    CategoryAxis xaxis = new CategoryAxis();

    @FXML
    NumberAxis yaxis = new NumberAxis();


    /**
     * Sets the stage.
     *
     * @param stage the stage to be set
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Sets the scene.
     *
     * @param scene the scene to be set
     */
    public void setScene(Scene scene) {
        this.scene = scene;
    }

    /**
     * Initializes the Statistics Controller and adds the data to be plotted to the bar chart.
     */
    @FXML
    public void initialize() {

        XYChart.Series set1 = new XYChart.Series<>();

        set1.getData().add(new XYChart.Data("Todo", TaskList.numTodo));
        set1.getData().add(new XYChart.Data("Event", TaskList.numEvent));
        set1.getData().add(new XYChart.Data("Deadline", TaskList.numDeadline));
        set1.getData().add(new XYChart.Data("Done", TaskList.numTaskDone));
        set1.getData().add(new XYChart.Data("Not Done", TaskList.numTaskNotDone));
        set1.getData().add(new XYChart.Data("Done Last Week", TaskList.numDonePastWeek));
        set1.getData().add(new XYChart.Data("Done Last Month", TaskList.numDonePastMonth));
        set1.getData().add(new XYChart.Data("Done Last Year", TaskList.numDonePastYear));

        taskChart.getData().addAll(set1);
    }

}
