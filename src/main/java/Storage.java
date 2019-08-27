import java.io.*;
import java.text.ParseException;
import java.util.LinkedList;

class Storage {
    private static String basePath = new File("").getAbsolutePath();
    private String filePath;

    Storage(String filePath) {
        this.filePath = filePath;
    }

    LinkedList<Task> load() throws IOException {
        LinkedList<Task> tasks = new LinkedList<>();
        try {
            FileReader in = new FileReader(new File(basePath + filePath));
            BufferedReader br = new BufferedReader(in);
            String line;
            while ( (line = br.readLine()) != null) {
                String type = line.substring(line.indexOf(".") + 2, line.indexOf(".") + 3);
                String state = line.substring(line.indexOf(".") + 5, line.indexOf(".") + 6);
                String content = line.substring(line.indexOf(".") + 8);
                String time = "0";
                if (line.indexOf("(") > 0) {
                    content = line.substring(line.indexOf(".") + 8, line.indexOf("(") - 1);
                    time = line.substring(line.indexOf(":") + 2, line.indexOf(")"));
                }
                switch (type) {
                    case "T":
                        Task todo = new ToDo(content);
                        if (state.contentEquals("Y")) {
                            todo.setDone();
                        }
                        tasks.add(todo);
                        break;
                    case "D":
                        Task deadline = new Deadline(content, time);
                        if (state.contentEquals("Y")) {
                            deadline.setDone();
                        }
                        tasks.add(deadline);
                        break;
                    case "E":
                        Task event = new Event(content, time);
                        if (state.contentEquals("Y")) {
                            event.setDone();
                        }
                        tasks.add(event);
                        break;
                }
            }
        } catch(ParseException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    void saveData() throws FileNotFoundException {
        File file = new File(basePath + filePath);
        try (PrintWriter out = new PrintWriter(file)) {
            for(Task task: TaskList.taskList) {
                out.println(task.toString());
            }
        }
    }
}
