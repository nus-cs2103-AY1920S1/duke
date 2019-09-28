package duke.gui;

import duke.Ui;

import java.util.ArrayList;
import java.util.List;

class GuiMessage implements Ui {
    private List<String> messageList = new ArrayList<>();

    List<String> getMessageList() {
        return List.copyOf(messageList);
    }

    void clear() {
        messageList.clear();
    }

    @Override
    public void showMessage(List<String> message) {
        messageList.add(String.join("\n", message));
    }
}
