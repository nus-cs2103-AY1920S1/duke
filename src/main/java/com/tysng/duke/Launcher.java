package com.tysng.duke;

import com.tysng.duke.service.Duke;
import com.tysng.duke.storage.Storage;
import com.tysng.duke.ui.Gui;
import com.tysng.duke.ui.Cli;
import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Gui.class, args);
    }
}
