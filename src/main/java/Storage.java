import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

public class Storage {
    String filePath;
    Storage(String filePath){
        this.filePath = filePath;
    }

    BufferedReader bufferedReader;
    FileReader fileReader;

    /**
     * This function will load the file
     * @return the buffered file for TaskList usage
     * @throws DukeException
     */
    BufferedReader load() throws DukeException {
        try {
            fileReader = new FileReader(filePath);
            bufferedReader = new BufferedReader(fileReader);
            return bufferedReader;
        } catch (FileNotFoundException ex) {
            throw new DukeException("Unable to open file '" + filePath + "'");
        }
    }

    /**
     * Writes the file
     * @param input string of the text to write in the file
     * @throws DukeException
     */
    void write(String input) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(input);
            fileWriter.close();
        } catch(Exception e){
            throw new DukeException("Unable to write to file '" + filePath + "'");
        }
    }
}
