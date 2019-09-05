package ui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UiTest {

    @Test
    void showLoadingError() {
        assertEquals("Note: task.TaskList storage is initially empty / the file is corrupted\n"
                + "New empty file will be created.", new Ui().showLoadingError());
    }
}