package commands;

import components.Storage;
import components.TaskList;
import javafx.application.Platform;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class EndSessionCommand implements Command {
    @Override
    public String[] execute(Storage storage, TaskList taskList) {
        String[] t = new String[]{"Goodbye!"};
        CompletableFuture.delayedExecutor(500, TimeUnit.MILLISECONDS).execute(() -> {
            Platform.exit();
        });


        return t;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
