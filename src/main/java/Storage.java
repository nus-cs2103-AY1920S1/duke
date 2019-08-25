import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Storage {

    File tempFile;
    ArrayList<String> storingStrings = new ArrayList<>();

    public Storage(String filePath) {
        try{
            this.tempFile = new File(filePath);
            Stream<String> stream = Files.lines(Paths.get(filePath));
            stream.forEach(storingStrings::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void saveTaskToFile(ArrayList<Task> list) {
        try {
            FileWriter fileWriter = new FileWriter(this.tempFile);
            for (int i = 0; i < list.size(); i++) {
                Task currentTask = list.get(i);
                fileWriter.write(currentTask.createTaskInFileFormat() + "\n");
            }
            fileWriter.close();

        } catch(IOException e) {
            System.out.println(e);
        }
    }

    public ArrayList<Task> createTasksFromFile() {
        ArrayList<Task> store = new ArrayList<>();
        for (int i = 0; i < this.storingStrings.size(); i++) {
            String wholeLine = this.storingStrings.get(i);
            String[] argumentArray = wholeLine.split(" ");

            boolean isCompleted = false;
            if (argumentArray[1].equals("1"))  {
                //completed
                //System.out.println(argumentArray[1]);
                isCompleted = true;
            }

            if (argumentArray[0].equals("T")) {

                String toDoTaskString = "";
                for (int j = 2; j < argumentArray.length; j++) {
                    toDoTaskString += argumentArray[j];
                    toDoTaskString += " ";
                }
                //.trim() to remove trailing space
                Task toDoTask = new ToDo(toDoTaskString.trim());

                if(isCompleted) {
                    toDoTask.markAsDone();
                }
                store.add(toDoTask);

            } else if (argumentArray[0].equals("D")) {

                String deadlineTaskDescriptionString = "";
                String deadlineTaskDateAndTimeString = "";
                boolean createDesc = true;
                for (int j = 2; j < argumentArray.length; j++) {
                    if (argumentArray[j].equals("/by")) {
                        createDesc = false;
                    } else if (createDesc) {
                        deadlineTaskDescriptionString += argumentArray[j];
                        deadlineTaskDescriptionString += " ";
                    } else {
                        deadlineTaskDateAndTimeString += argumentArray[j];
                        deadlineTaskDateAndTimeString += " ";
                    }
                }

                //use .trim() method to eliminate trailing white space
                Task deadlineTask = new Deadline(deadlineTaskDescriptionString.trim(), deadlineTaskDateAndTimeString.trim());
                if (isCompleted) {
                    deadlineTask.markAsDone();
                }
                store.add(deadlineTask);

            } else if (argumentArray[0].equals("E")) {
                String eventTaskDescriptionString = "";
                String eventTaskDateAndTimeString = "";
                boolean createDesc = true;


                for (int j = 2; j < argumentArray.length; j++) {
                    if (argumentArray[j].equals("/at")) {
                        createDesc = false;
                    } else if (createDesc) {
                        eventTaskDescriptionString += argumentArray[j];
                        eventTaskDescriptionString += " ";
                    } else {
                        eventTaskDateAndTimeString += argumentArray[j];
                        eventTaskDateAndTimeString += " ";

                    }
                }

                //use of .trim() to avoid trailing whitespace
                Task eventTask = new Event(eventTaskDescriptionString.trim(), eventTaskDateAndTimeString.trim());
                if (isCompleted) {
                    eventTask.markAsDone();
                }
                store.add(eventTask);
            } else {

            }
        }


        return store;
    }

}
