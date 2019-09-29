package duke.lib.autocorrect;

import duke.lib.common.DukeException;
import duke.lib.datahandling.DataStorage;
import duke.lib.task.Task;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class WordDictionary {
    final static String HOME = System.getProperty("user.home");
    final static String GLOSSARY_FILEPATH = HOME + File.separator + "data" + File.separator + "words.txt";
    private final static List<String> CORPUS = new ArrayList<String>(
            List.of("find", "bye", "list", "done", "delete", "todo", "event", "deadline"));

    private HashSet<String> wordList;
    private DataStorage dataStorage;

    public WordDictionary() throws IOException, DukeException {
        wordList = new HashSet<>();

        File file = new File(GLOSSARY_FILEPATH);
        if (!file.exists()) {
            File parent = file.getParentFile();
            parent.mkdirs();
            initializeWordList(file);
        }

        BufferedReader br = new BufferedReader(new FileReader(file));
        try {
            String line;
            while ((line = br.readLine()) != null) {
                wordList.add(line);
            }
            loadFromList();
        } finally {
            br.close();
        }
    }

    private void initializeWordList(File file) throws IOException {


        file.createNewFile();

        Files.write(Paths.get(GLOSSARY_FILEPATH), CORPUS);

    }

    public HashSet<String> getWordList() {
        return wordList;
    }

    public void updateWordList() throws DukeException {
        loadFromList();
    }

    private void loadFromList() throws DukeException {
        dataStorage = new DataStorage();
        ArrayList<Task> tasks;

        try {
            tasks = dataStorage.load();
        } catch (DukeException e) {
            throw new DukeException("Unable to load data from storage for spell check dictionary :(");
        }

        for (Task t : tasks) {
            wordList.add(t.getName());
        }
    }
}
