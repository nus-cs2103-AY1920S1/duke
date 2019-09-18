import expenses.Expenses;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ExpensesMain extends Application {
    private Expenses expenses = new Expenses();

    @Override
    public void start(Stage stage) throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Expenses.class.getResource("/view/Expenses.fxml"));
            BorderPane bp = fxmlLoader.load();
            Scene scene = new Scene(bp);
            stage.setScene(scene);
            fxmlLoader.<ExpensesWindow>getController().setExpensesObject(expenses);
            fxmlLoader.<ExpensesWindow>getController().setError(expenses.initialisationErrorMessage);
            fxmlLoader.<ExpensesWindow>getController().setListeners();
            fxmlLoader.<ExpensesWindow>getController().render();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
