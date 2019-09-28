package duke.gui;

import duke.Duke;

import java.util.ArrayList;
import java.util.List;

class GuiDuke extends Duke {
    private boolean exit;
    private List<String> messages;

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
        this(filePath, new ArrayList<>());
    }

    private GuiDuke(String filePath, List<String> messages) {
        super(filePath, message -> messages.add(String.join("\n", message)));
        this.messages = messages;
    }

    String getResponse(String input) {
        exit = runInput(input);
        List<String> messageList = getAndClearMessages();
        assert messageList.size() == 1;
        return messageList.get(0);
    }

    List<String> getAndClearMessages() {
        List<String> messageList = List.copyOf(messages);
        messages.clear();
        return messageList;
    }

    boolean shouldExit() {
        return exit;
    }
}
