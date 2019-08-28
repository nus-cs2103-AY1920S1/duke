import javax.annotation.processing.Filer;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileHandle {
    public static void appendToFile(String filepath, List<Task> tasks) {
        try {
            Path p = Paths.get(filepath);
            FileWriter fr = new FileWriter(p.toRealPath().toString());
            fr.write("");
            fr.flush();
            fr.close();

            fr = new FileWriter(new File(filepath), true);
            BufferedWriter br = new BufferedWriter(fr);
            //for each task, br.write("data");
            //descr, id, iscompleted
            for (Task t : tasks) {
                String details = String.format("%s | %d | %s\r\n",
                        t.getTaskType(), boolToInt(t.isCompleted()), t.getDescription());
                br.write(details);
            }

            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readIntoTasksListFromFile(String filepath) {
        try {
            Path p = Paths.get(filepath);
            BufferedReader br = new BufferedReader(
                    new FileReader(p.toRealPath().toString()));
            String line = br.readLine();
            while (line != null) {
                //handle line
                String[] sections = line.split(" \\| ");
                TaskType taskType = TaskType.convertToTaskType(sections[0]);
                boolean completed = intStrToBool(sections[1]);
                String description = sections[2];
                String datetime = "";
                String term = "";
                if (taskType == TaskType.E) {       //actually can just store as you read in one... but ok
                    term = " /at ";
                } else if (taskType == TaskType.D) {
                    term = " /by ";
                }
                if (sections.length > 3) {
                    datetime = term + sections[3];
                }

                Task task = Task.createTask(taskType, description + datetime);
                if (completed) {
                    task.setCompleted();
                }
                //when i create, they auto add to list and ++ count

                line = br.readLine();
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int boolToInt(boolean b) {
        return b ? 1 : 0;
    }
    private static boolean intStrToBool(String intStr) {
        return intStr.equals("1");
    }
}
