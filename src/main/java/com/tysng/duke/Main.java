package com.tysng.duke;

import com.tysng.duke.service.Duke;
import com.tysng.duke.storage.Storage;
import com.tysng.duke.ui.Ui;

/**
 * This class is the entry point of the Application.
 */
public class Main {
    public static void main(String[] args) {
        Storage storage = Storage.initialize();
        Duke duke = new Duke(storage);
        Ui ui = new Ui(duke);
        ui.run();
    }
}
