package duke.memo.storage;

import duke.memo.data.RecordList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Storage {
    private FileReader fr;
    private String filePath;

    /**
     * Constructor for Storage.
     *
     * @param filePath  Path to the log file.
     * @throws IOException Unpredicted I/O exception.
     */
    public Storage(String filePath) throws IOException {
        String folderPath = this.filePath = System.getProperty("user.dir") + "/data";
        File folder = new File(folderPath);
        if (!(folder.exists() && folder.isDirectory())) {
            folder.mkdir();
        }
        this.filePath = System.getProperty("user.dir") + "/" + filePath;
        File file = new File(this.filePath);
        if (file.createNewFile()) {
            System.out.println("File has been created.");
        } else {
            System.out.println("File already exists.");
        }
        fr = new FileReader(file);
    }

    /**
     * Store file to local.
     *
     * @param recordList List of tasks.
     */
    public void store(RecordList recordList) {
        String filePath = System.getProperty("user.dir") + "/data/duke.txt";
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(recordList.stream()
                    .map(t -> t.getPrintableMsg() + System.lineSeparator())
                    .collect(Collectors.joining()));
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Load file to local.
     *
     * @return arraylist of string contains lines of log
     * @throws IOException  File does not exist.
     */
    public ArrayList<String> load() throws IOException {
        BufferedReader br = new BufferedReader(fr);
        return (ArrayList<String>) br.lines().collect(Collectors.toList());
    }

}
