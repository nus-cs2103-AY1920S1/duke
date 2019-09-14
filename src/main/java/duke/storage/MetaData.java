package duke.storage;

import java.io.*;

import duke.dukeexception.DukeException;

public class MetaData {
    private String metaDataPath;

    public MetaData(String metaDataPath) {
        this.metaDataPath = metaDataPath;
    }

    public void writeMetaData(String newLastOpenedFilePath) throws DukeException {
        try {
            File tempFile = new File(metaDataPath);
            if(!tempFile.exists()) {
                tempFile.getParentFile().mkdirs();
                tempFile.createNewFile();
            }

            FileWriter fw = new FileWriter(metaDataPath);
            String linetoWrite = "lastOpened|" + newLastOpenedFilePath;
            fw.write(linetoWrite + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Error writing metadata");
        }
    }

    public String readMetaData() throws DukeException {
        try {
            File tempFile = new File(metaDataPath);
            if(!tempFile.exists()) {
                tempFile.getParentFile().mkdirs();
                tempFile.createNewFile();
            }

            BufferedReader fileInputStream = new BufferedReader(new FileReader(metaDataPath));
            String fileData = fileInputStream.readLine();
            return fileData;
        } catch (IOException e) {
            throw new DukeException(DukeException.METADATA_ERROR);
        }
    }
}
