package com.tysng.duke.ui;

import com.tysng.duke.service.DukeService;
import com.tysng.duke.storage.Storage;
import com.tysng.duke.ui.controller.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A GUI for Duke using FXML.
 */
public class Gui extends Application {

    private DukeService dukeService;

    @Override
    public void init() {
        this.dukeService = new DukeService(Storage.initialize());
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDukeService(dukeService);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}