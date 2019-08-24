package duke;

import duke.task.Task;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public Storage() {
        this.filePath = getSaveFilePath();
    }

    private String getSaveFilePath() {
        if (System.getProperty("os.name").equals("Windows 10"))
            return "/Users/uicfa/Downloads/data.json";
        else
            return "/Users/leo/Downloads/data.json";
    }

    private void createSaveFile() throws IOException {
        File file = new File(this.filePath);
        if (!file.createNewFile()) throw new IOException();
        writeToSaveFile("{\"data\":[]}");
        Ui.showCreateSaveFileMessage();
    }

    private JSONObject readSaveFile() throws IOException, JSONException {
        // TODO: handle the exception where data.json doesn't exist or format is wrong
        InputStream is;
        try {
            is = new FileInputStream(this.filePath);
        } catch (FileNotFoundException e) {
            createSaveFile();
            is = new FileInputStream(this.filePath);
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

    private void writeToSaveFile(String content) throws IOException {
        FileWriter fileWriter = new FileWriter(this.filePath);
        fileWriter.write(content);
        fileWriter.close();
    }

    public void appendToSaveFile(Task task) throws IOException, JSONException {
        JSONObject obj = readSaveFile();
        obj.append("data", task.toMap());
        writeToSaveFile(obj.toString());
    }

    public void syncSaveFile(TaskList tasks) throws IOException, JSONException {
        JSONObject obj = new JSONObject();
        obj.put("data", new ArrayList<>());
        for (Task t : tasks.getList()) {
            obj.append("data", t.toMap());
        }
        writeToSaveFile(obj.toString());
    }

    public List<Task> loadFromSaveFile() throws IOException, JSONException, DukeException {
        List<Task> tasks = new ArrayList<>();
        JSONObject obj = readSaveFile();
        JSONArray dataArray = obj.getJSONArray("data");
        for (int i = 0; i < dataArray.length(); i++) {
            tasks.add(Task.fromJson(dataArray.getJSONObject(i)));
        }
        return tasks;
    }
}
