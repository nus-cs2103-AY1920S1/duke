package duke.gui;

import duke.Duke;

import java.util.List;

class GuiDuke extends Duke {
    private boolean exit;

    /**
     * Constructs a new copy of the Duke application for GUI with the default path.
     */
    GuiDuke() {
        this(Duke.DEFAULT_FILE_PATH);
    }

    /**
     * Constructs a new copy of the Duke application for GUI.
     *
     * @param filePath File path of the save file to store tasks.
     */
    private GuiDuke(String filePath) {
        super(filePath, new GuiMessage());
    }

    String getResponse(String input) {
        exit = runInput(input);
        List<String> messageList = getAndClearMessages();
        assert messageList.size() == 1;
        return messageList.get(0);
    }

    List<String> getAndClearMessages() {
        GuiMessage guiMessage = (GuiMessage) ui;
        List<String> messageList = guiMessage.getMessageList();
        guiMessage.clear();
        return messageList;
    }

    boolean shouldExit() {
        return exit;
    }
}
