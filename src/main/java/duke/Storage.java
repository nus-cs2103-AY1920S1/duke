package duke;

import java.io.*;
import java.util.ArrayList;

public class Storage {
    BufferedReader objReader = null;
    int no_of_task = 0;
    ArrayList<Task> taskList = new ArrayList<Task>();
    String filePath;
    File file;

    Storage(String filePath) throws IOException {                   //constructor: receiving the filepath
        this.filePath = filePath;
        File temp = new File(filePath);
        if (temp.exists()) {
            file = temp;
        }else{
            File dir = new File("src");
            if(!dir.exists()) {
                dir.mkdir();
            }
            file = new File("src", "Duke.txt");
            file.createNewFile();
        }
    }

    //////////////////////////// LOAD FILE Method /////////////////////////////////////////////
    public ArrayList<Task> load(){                      //loads the file onto an arraylist & returns the arrayList
        try {
        char type;
        int status;
        String des;
        String time;
        String strCurrentLine;
        String des_time;    //a substring for duke.Task description onwards
        objReader = new BufferedReader(new FileReader(file));


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

  public void AutoSave(TaskList tasks, int no_of_task) throws IOException {
      System.out.println("System performing autosave");
      FileWriter data = new FileWriter(file);

      for (int i = 0; i < no_of_task; i++) {
              if(tasks.taskList.get(i).type == 'T')
                  data.write(tasks.taskList.get(i).type + " | " + tasks.taskList.get(i).status + " | " + tasks.taskList.get(i).description + System.getProperty( "line.separator" ));
              else
                  data.write(tasks.taskList.get(i).type + " | " + tasks.taskList.get(i).status + " | " + tasks.taskList.get(i).description + " | " + tasks.taskList.get(i).get_TimeFrame() + System.getProperty( "line.separator" ));

      }
      data.close();
  }
 ////////////////////////////////////////  End of SAVE FILE Method  ///////////////////////////////////////////

}
