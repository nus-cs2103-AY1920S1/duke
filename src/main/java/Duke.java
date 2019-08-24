import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    private static void formattedPrint(String content) {
        System.out.println("    ____________________________________________________________\n" +
                "     " + content + "\n" +
                "    ____________________________________________________________\n    ");
    }

    private static void addTask(Task task, List<Task> list) throws IOException, JSONException {
        list.add(task);
        String output = "Got it. I've added this task: " + "\n" + "       "
                + task.toString() + "\n" + "     "
                + "Now you have " + list.size() + (list.size() == 1 ? " task in the list." : " tasks in the list.");
        appendToSaveFile(task);
        formattedPrint(output);
    }

    private static String[] splitByKeyword(String input, String keyword) throws DukeException {
        try {
            int index;
            String[] res = new String[2];
            index = input.indexOf(keyword);
            if (index == -1)
                throw new DukeException("No keyword " + keyword + " is found.");
            res[0] = input.substring(0, index - 1);
            if (res[0].length() == 0)
                throw new DukeException("No description found before keyword " + keyword + ".");
            res[1] = input.substring(index + keyword.length() + 1);
            if (res[1].length() == 0)
                throw new DukeException("No description found after keyword " + keyword + ".");
            return res;
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeException("Please check your format around keyword " + keyword);
        }
    }

    private static String getSaveFilePath() {
        if (System.getProperty("os.name").equals("Windows 10"))
            return "/Users/uicfa/Downloads/data.json";
        else
            return "/Users/leo/Downloads/data.json";
    }

    private static void createSaveFile() throws IOException {
        formattedPrint("data.json not found. Creating a new one...");
        File file = new File(getSaveFilePath());
        if (!file.createNewFile()) throw new IOException();
        writeToSaveFile("{\"data\":[]}");
    }

    private static JSONObject readSaveFile() throws IOException, JSONException {
        // TODO: handle the exception where data.json doesn't exist or format is wrong
        String path = getSaveFilePath();
        InputStream is;
        try {
            is = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            createSaveFile();
            is = new FileInputStream(path);
        }
        BufferedReader buf = new BufferedReader(new InputStreamReader(is));
        String line = buf.readLine();
        StringBuilder sb = new StringBuilder();
        while (line != null) {
            sb.append(line).append("\n");
            line = buf.readLine();
        }
        return new JSONObject(new JSONTokener(sb.toString()));
    }

    private static void writeToSaveFile(String content) throws IOException {
        String path = getSaveFilePath();
        FileWriter fileWriter = new FileWriter(path);
        fileWriter.write(content);
        fileWriter.close();
    }

    private static void appendToSaveFile(Task task) throws IOException, JSONException {
        JSONObject obj = readSaveFile();
        obj.append("data", task.toMap());
        writeToSaveFile(obj.toString());
    }

    private static void syncSaveFile(List<Task> tasks) throws IOException, JSONException {
        JSONObject obj = new JSONObject();
        obj.put("data", new ArrayList<>());
        for (Task t : tasks) {
            obj.append("data", t.toMap());
        }
        writeToSaveFile(obj.toString());
    }

    private static List<Task> loadFromSaveFile() throws IOException, JSONException, DukeException {
        List<Task> tasks = new ArrayList<>();
        JSONObject obj = readSaveFile();
        JSONArray dataArray = obj.getJSONArray("data");
        for (int i = 0; i < dataArray.length(); i++) {
            tasks.add(Task.fromJson(dataArray.getJSONObject(i)));
        }
        return tasks;
    }

    public static void main(String[] args) throws JSONException, IOException, DukeException {
        System.out.println("    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n");
        Scanner sc = new Scanner(System.in);
        List<Task> tasks = loadFromSaveFile();
        boolean shouldRun = true;
        while (shouldRun) {
            try {
                String userInput = sc.nextLine();
                switch (DukeCommand.parseCommand(userInput)) {
                    case BYE: {
                        formattedPrint("Bye. Hope to see you again soon!");
                        shouldRun = false;
                        sc.close();
                        break;
                    }
                    case LIST: {
                        StringBuilder builder = new StringBuilder("Here are the tasks in your list:");
                        for (int i = 0; i < tasks.size(); i++) {
                            builder.append("\n" + "     ");
                            builder.append(i + 1).append(".").append(tasks.get(i).toString());
                        }
                        formattedPrint(builder.toString());
                        break;
                    }
                    case DONE: {
                        int doneNo = Integer.parseInt(userInput.split(" ")[1]) - 1;
                        tasks.get(doneNo).markAsDone();
                        String tempOut = "Nice! I've marked this task as done: " + "\n" + "       " +
                                tasks.get(doneNo).toString();
                        syncSaveFile(tasks);
                        formattedPrint(tempOut);
                        break;
                    }
                    case DELETE: {
                        int removeNo = Integer.parseInt(userInput.split(" ")[1]) - 1;
                        String tempOut = "Noted. I've removed this task: " + "\n" + "       " +
                                tasks.get(removeNo).toString() + "\n" + "     " +
                                "Now you have " + (tasks.size() - 1) +
                                (tasks.size() - 1 == 1 ? " task in the list." : " tasks in the list.");
                        tasks.remove(removeNo);
                        syncSaveFile(tasks);
                        formattedPrint(tempOut);
                        break;
                    }
                    case TODO: {
                        if (userInput.length() == 4)
                            throw new DukeException("The description of a todo cannot be empty.");
                        String restOfInput = userInput.substring(5);
                        addTask(new ToDo(restOfInput), tasks);
                        break;
                    }
                    case DEADLINE: {
                        if (userInput.length() == 8)
                            throw new DukeException("The description of a deadline cannot be empty.");
                        userInput = userInput.substring(9);
                        String[] temp = splitByKeyword(userInput, "/by");
                        addTask(new Deadline(temp[0], LocalDateTime.from(dateTimeFormatter.parse(temp[1]))), tasks);
                        break;
                    }
                    case EVENT: {
                        if (userInput.length() == 5)
                            throw new DukeException("The description of an event cannot be empty.");
                        userInput = userInput.substring(6);
                        String[] temp = splitByKeyword(userInput, "/at");
                        addTask(new Event(temp[0], LocalDateTime.from(dateTimeFormatter.parse(temp[1]))), tasks);
                        break;
                    }
                    default: {
                        throw new DukeException("Command found but no implementation is provided.");
                    }
                }
            } catch (DukeException e) {
                formattedPrint("☹ OOPS!!! " + e.getMessage());
            }
        }
    }
}