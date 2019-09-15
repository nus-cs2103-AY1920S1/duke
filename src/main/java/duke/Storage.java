package duke;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    BufferedReader objReader = null;
    int no_of_task = 0;
    ArrayList<Task> taskList = new ArrayList<Task>();
    String filePath;

    Storage(String filePath) {                   //constructor: receiving the filepath
        this.filePath = filePath;
    }

    //////////////////////////// LOAD FILE Method /////////////////////////////////////////////
    ArrayList<Task> load(){                      //loads the file onto an arraylist & returns the arrayList
        try {
        char type;
        int status;
        String des;
        String time;
        String strCurrentLine;
        String des_time;    //a substring for duke.Task description onwards
        objReader = new BufferedReader(new FileReader(filePath));


        while ((strCurrentLine = objReader.readLine()) != null) {
            type = strCurrentLine.charAt(0);
            status = Integer.parseInt(strCurrentLine.substring(4, 5));
            des_time = strCurrentLine.substring(8);
            if (type == 'D' || type == 'E') {
                int in = des_time.indexOf("|"); //this finds the first occurrence of "|"
                des = des_time.substring(0, in);
                time = des_time.substring(in + 2);
            } else { ///// to do case /////////
                des = des_time;
                time = "";
            }
            Task t = new Task(des, type, status, time);
            taskList.add(t);

        }
    }
        catch(
    IOException e)

    {
        e.printStackTrace();
    }
        finally

    {
        try {
            if (objReader != null)
                objReader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
        return taskList;
  }
  ///////////////////////////////////END of LOAD FILE method ////////////////////////////////////////////////

  ////////////////////////////////// SAVE FILE Method ///////////////////////////////////////////////////////

  public static void AutoSave(TaskList tasks, int no_of_task) throws IOException {
      System.out.println("System performing autosave");
      //WriteFile data = new WriteFile("D:\\madae\\School\\cs2103T\\IdeaProjects\\DUKE\\src\\main\\java\\duke\\Duke.txt");
      //WriteFile data_append = new WriteFile("D:\\madae\\School\\cs2103T\\IdeaProjects\\DUKE\\src\\main\\java\\duke\\Duke.txt", true);
      WriteFile data = new WriteFile("build/libs/src/Duke.txt");
      WriteFile data_append = new WriteFile("build/libs/src/Duke.txt", true);
      for (int i = 0; i < no_of_task; i++) {
          if(i>0){
              if(tasks.taskList.get(i).type == 'T')
                  data_append.writeToFile(tasks.taskList.get(i).type + " | " + tasks.taskList.get(i).status + " | " + tasks.taskList.get(i).description);
              else
                  data_append.writeToFile(tasks.taskList.get(i).type + " | " + tasks.taskList.get(i).status + " | " + tasks.taskList.get(i).description + " | " + tasks.taskList.get(i).get_TimeFrame());
          }
          else{
              if(tasks.taskList.get(i).type == 'T')
                  data.writeToFile(tasks.taskList.get(i).type + " | " + tasks.taskList.get(i).status + " | " + tasks.taskList.get(i).description);
              else
                  data.writeToFile(tasks.taskList.get(i).type + " | " + tasks.taskList.get(i).status + " | " + tasks.taskList.get(i).description + " | " + tasks.taskList.get(i).get_TimeFrame());
          }
      }

  }
 ////////////////////////////////////////  End of SAVE FILE Method  ///////////////////////////////////////////

}
