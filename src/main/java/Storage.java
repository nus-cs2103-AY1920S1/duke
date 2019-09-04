import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private String fileLocation =  "data/duke.txt";
    private File file = new File(fileLocation);

    //reading from saved state
    public ArrayList<Task> recoverTasks(){
        ArrayList<Task> tasks = new ArrayList<>();
        try{
            Scanner sc = new Scanner(file);
            assert sc != null;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] splited = line.split("\\|");
                String type = splited[0];
                for (int i = 0; i < splited.length; i++) {
                    splited[i] = splited[i].trim();
                }
                switch(splited[0]) {
                    case "T":
                        Task todo = new Todo(splited[2]);
                        if(splited[1].equals("true")){
                            todo.markAsDone();
                        }
                        tasks.add(todo);
                        break;

                    case "D":
                        Task deadline = new Deadline(splited[2], splited[3]);
                        if(splited[1].equals("true")){
                            deadline.markAsDone();
                        }
                        tasks.add(deadline);
                        break;

                    case "E":
                        Task event = new Event(splited[2], splited[3]);
                        if(splited[1].equals("true")){
                            event.markAsDone();
                        }
                        tasks.add(event);
                        break;

                    default:
                        break;
                    }
                }
                sc.close();
        }catch(FileNotFoundException e){
            System.out.println("File not found");
        }
        return tasks;
    }
    public void saveFile(ArrayList<Task> tasks){
        try {
            FileWriter fw = new FileWriter("data/duke.txt");
            String total = "";
            for (int i = 0; i < tasks.size(); i ++) {
                String current = tasks.get(i).getStorageString();
                if (i == 0) {
                    total = current;
                } else {
                    total = total + "\n" + current;
                }
            }
 			fw.write(total);
 			fw.close();
 		} catch (IOException e) {
 			System.out.println("Something went wrong: " + e.getMessage());
 		}
 	}
    
}