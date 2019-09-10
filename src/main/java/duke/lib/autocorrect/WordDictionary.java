package duke.lib.autocorrect;

import duke.lib.common.DukeException;
import duke.lib.datahandling.DataStorage;
import duke.lib.task.Task;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class WordDictionary {
    private final static String glossaryFilePath = "data/words.txt";
    private HashSet<String> wordList;
    private DataStorage dataStorage;

    public WordDictionary() throws IOException, DukeException {
        wordList = new HashSet<>();
        BufferedReader br = new BufferedReader(new FileReader(glossaryFilePath));
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
