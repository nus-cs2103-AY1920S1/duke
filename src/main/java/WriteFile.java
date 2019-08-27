import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;


public class WriteFile {
    private String  file_path;
    private boolean append_to_file = false;

    public WriteFile(String file_path, boolean append_value) {
        this.file_path = file_path;
        append_to_file = append_value;
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
            FileWriter write = new FileWriter(file_path,append_to_file);
            PrintWriter write_line = new PrintWriter(write);
            write_line.printf("%s" + "%n",textLine);
            write_line.close();
        } catch (IOException e) {
            System.out.println("No can do son.");
        }

    }
    public void setAppend(boolean append) {
        append_to_file = append;
    }

}
