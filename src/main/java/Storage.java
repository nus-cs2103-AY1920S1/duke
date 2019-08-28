import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class fileManager {
    public static Task createTask(String[] textArr) {
        String type = textArr[0];
        Task task = null;
        boolean done = textArr[1].equals("1"); //1 means done
        switch (type) {
        case "T": //todo
            task = new ToDo(done, textArr[2]);
            break;
        case "D": //deadline
            LocalDateTime deadline = Parser.parseDateTime(textArr[3]);
            task = new Deadlines(done, textArr[2], deadline);
            break;
        case "E": //event
            String[] startEndStr = textArr[3].split(" - ");

            LocalDateTime start = Parser.parseDateTime(startEndStr[0]);
            LocalDateTime end = Parser.parseDateTime(startEndStr[1]);
            task = new Events(done, textArr[2], start, end);
            break;
        default:
            break;
        }
        return task;
    }

    public static void updateFile(List<Task> taskList, String filePath) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < taskList.size(); i++) {
            Task t = taskList.get(i);
            sb.append(t.toFileString());
            if (i != taskList.size() - 1) { //final item, dont add new line
                sb.append("\n");
            }
        }
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println("Cannot write to file: " + e.getMessage());
        }
    }
}
