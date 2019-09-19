import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;


public class WriteFile {
    private String  filePath;
    private boolean append_to_file = false;

    public WriteFile(String path,boolean append_value) {
        this.filePath = path;
        append_to_file = append_value;
    }
    public void createFile() {
        try {
            File file = new File(filePath);
            if (file.exists()) {

                return;
            }
            file.createNewFile();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void writeToFile( String textLine ) {
        /**
         *  Writes specified text to file in filepath specified upon construction
         *  Notes: The %s between double quotes means a string of characters of any length.
         *  The %n means a newline.
         *  So we're telling the printf method to format a string of characters
         *  and add a newline at the end
         * @params String textLine contains text to write to file
         * @return none
         */
        try {
            FileWriter write = new FileWriter(filePath,append_to_file);
            BufferedWriter write_line = new BufferedWriter(write);
            write_line.write(textLine);
            write_line.newLine();
            write_line.close();
        } catch (IOException e) {
            System.out.println("No can do son.");
        }
    }
    public void setAppend(boolean append) {
        append_to_file = append;
    }

}
