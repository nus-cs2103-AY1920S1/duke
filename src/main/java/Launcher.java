package com.leeyiyuan;


import javafx.application.Application;
import com.leeyiyuan.view.DukeGui;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(DukeGui.class, args);
    }
}
