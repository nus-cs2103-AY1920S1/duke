package duke.lib.autocorrect;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class WordDictionary {
    final static String filePath = "data/words.txt";
    private HashSet<String> wordList;

    public WordDictionary() throws IOException {
        wordList = new HashSet<>();
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        try {
            String line;
            while ((line = br.readLine()) != null) {
                wordList.add(line);
            }
        } finally {
            br.close();
        }
    }

    public HashSet<String> getWordList() {
        return wordList;
    }
}
