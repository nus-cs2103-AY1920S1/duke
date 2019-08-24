package duke.storage;

import duke.exception.DukeException;
import duke.task.Task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Storage {
  private String filePath;

  public Storage(String filePath) {
    this.filePath = filePath;
  }

  public List<Task> load() throws DukeException {
    try {
      List<Task> list = new ArrayList<>();
      List<String> lines = Files.readAllLines(Paths.get(filePath));
      for (String line : lines) {
        if (!line.isEmpty())
          list.add(Task.taskMaker(line.split(",")));
      }
      return list;
    } catch (IOException e) {
      throw new DukeException(e.getMessage());
    }
  }

  public void deleteLine(String toDelete) throws DukeException {
    try {
      List<String> lines = Files.readAllLines(Paths.get(filePath));
      List<String> newlines = new ArrayList<>();
      for (String line : lines) {
        if (!line.equals(toDelete) && !line.contains(toDelete)) {
          newlines.add(line);
        }
      }
      Files.write(Paths.get(filePath), newlines);
    } catch (IOException e) {
      throw new DukeException(e.getMessage());
    }
  }

  public void replaceLine(String before, String after) throws DukeException {
    try {
      List<String> lines = Files.readAllLines(Paths.get(filePath));
      List<String> newlines = new ArrayList<>();
      for (String line : lines) {
        if (line.equals(before) || line.contains(before)) {
          newlines.add(after);
        } else {
          newlines.add(line);
        }
      }
      Files.write(Paths.get(filePath), newlines);
    } catch (IOException e) {
      throw new DukeException(e.getMessage());
    }
  }

  public void addTask(Task t) throws DukeException {
    try {
      Files.write(Paths.get(filePath), Collections.singletonList(t.storageString()), StandardOpenOption.APPEND);
    } catch (IOException e) {
      throw new DukeException(e.getMessage());
    }
  }
}
