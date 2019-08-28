import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class WriteFile{
    String path;
    boolean append_to_file = false;   //set to false so we don't append but rather erase everything in the file//
    WriteFile(String file_path){      //constructor1: erases all data
        path = file_path;
    }
    WriteFile(String file_path, boolean append_value){  //constructor2: appends data
        append_to_file = append_value;
        path = file_path;
    }
    void writeToFile(String textLine) throws IOException {
        FileWriter write = new FileWriter(path , append_to_file);
        PrintWriter print_line = new PrintWriter( write );
        print_line.printf("%s" + "%n", textLine);
        print_line.close();
    }
}
