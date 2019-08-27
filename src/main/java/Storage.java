import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


public class Storage {
    private String filepath;

    public Storage(String filepath){
        this.filepath = filepath;

    }

    public void createFile(String filepath) throws IOException{
        File f = new File(filepath);
        String data = "";

        if (f.exists() == false){

            try {
                // Need to create a new empty text file
                Files.write(Paths.get(filepath), data.getBytes());
                System.out.println("File successfully created");
            } catch (IOException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public void writeToFile(String text) throws IOException{
        // appends the string to the text file as specified in filepath
        FileWriter fw = new FileWriter(filepath, false);
        fw.write(text + System.lineSeparator());
        fw.close();
    }

}
