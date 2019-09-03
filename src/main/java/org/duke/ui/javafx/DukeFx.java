package org.duke.ui.javafx;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import org.duke.Duke;
import org.duke.cmd.CommandDispatcher;
import org.duke.ui.DukeIO;

import java.util.Iterator;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

public class DukeFx extends Application {

    private final TextArea inputBox = new TextArea();
    private final TextArea outputBox = new TextArea();
    private final DukeFxIO io = new DukeFxIO();
    private Font baseFont = Font.font(20);
    private Stage stage;

    public static void main(String[] args) {
        Application.launch(args);
    }

    private void onSubmit() {
        String input = inputBox.getText();
        if (!input.isEmpty()) {
            inputBox.clear();
            io.sendCommand(input);
        }
    }

    private BorderPane makeUserInputPane() {
        inputBox.setFont(baseFont);
        inputBox.addEventFilter(KeyEvent.ANY, evt -> {
            if (evt.getCode() == KeyCode.ENTER || "\n".equals(evt.getCharacter())) {
                evt.consume();
                this.onSubmit();
            }
        });

        Button submitBtn = new Button("Submit");
        submitBtn.setFont(baseFont);
        submitBtn.setDefaultButton(true);
        submitBtn.setMaxHeight(Double.MAX_VALUE);
        submitBtn.setOnAction(evt -> this.onSubmit());

        BorderPane pane = new BorderPane(inputBox);
        pane.setRight(submitBtn);
        return pane;
    }

    @Override
    public void start(Stage stage) {
        this.stage = stage;

        io.startDuke();
        stage.setOnCloseRequest(
                evt -> io.shutdown());

        outputBox.setEditable(false);
        outputBox.setFont(baseFont);
        BorderPane mainPane = new BorderPane(outputBox);
        mainPane.setBottom(makeUserInputPane());

        Scene scene = new Scene(mainPane); // Setting the scene to be our Label
        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    private void startClose() {
        KeyFrame shutdownFrame = new KeyFrame(Duration.seconds(2),
                evt -> {
                    stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
                    stage.close();
                });
        new Timeline(shutdownFrame).play();
    }

    public class DukeFxIO implements DukeIO {
        private final ExecutorService dukeExecutor;
        private final Duke duke;
        private CommandDispatcher dispatcher;

        DukeFxIO() {
            dukeExecutor = Executors.newSingleThreadExecutor();
            duke = new Duke(this);
        }

        void shutdown() {
            try {
                dukeExecutor.submit(duke::save).get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            dukeExecutor.shutdown();
        }

        private void startDuke() {
            dukeExecutor.execute(duke::run);
        }

        void sendCommand(String input) {
            dukeExecutor.submit(() -> {
                boolean shouldExit = withDialogBlock(
                        () -> dispatcher.dispatchCommand(input),
                        false);
                if (shouldExit) {
                    Platform.runLater(DukeFx.this::startClose);
                }
            });
        }

        @Override
        public void say(Iterator<String> lines) {
            Platform.runLater(() -> {
                while (lines.hasNext()) {
                    String line = lines.next();
                    outputBox.appendText(line);
                    outputBox.appendText("\n");
                }
            });
        }

        @Override
        public <T> T withDialogBlock(Supplier<T> action, T fallback) {
            try {
                return action.get();
            } catch (Exception e) {
                return fallback;
            }
        }

        @Override
        public void listen() {
        }

        @Override
        public void setCommandDispatcher(CommandDispatcher commandDispatcher) {
            this.dispatcher = commandDispatcher;
        }
    }
}

