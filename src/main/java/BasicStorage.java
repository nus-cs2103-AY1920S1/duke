import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;
class BasicStorage implements TaskObserver, StorageInterface {


    private TaskModelInterface model;
    private String path = "../../../../data/duke.txt";

    public BasicStorage(TaskModelInterface model) {
        this.model = model;
        model.registerObserver(this);
    }

    public void update(TaskModelInterface model) {
        String toWrite = "dummy";
        writeData(toWrite);
    }

    public void loadData() {
        try {
            File f = new File(this.path);
            Scanner fileScanner = new Scanner(f);
            while (fileScanner.hasNext()) {
                System.out.println(fileScanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    private void writeData(String textToAdd) {
//        File f = new File(this.path);
 //       System.out.println("file exists?: " + f.exists());
        try {

            FileWriter fw = new FileWriter(this.path);
            fw.write(textToAdd);
            fw.close();

        } catch (IOException e) {
            System.out.println(e);
            System.out.println("you need to create a folder" 
                    + " called "data" in "duke" dir");
        }
                
    }

}
