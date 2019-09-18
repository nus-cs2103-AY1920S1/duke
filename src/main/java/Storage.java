import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Scanner;

/**
 * Class to handle interfacing with the data file.
 */
public class Storage {

    private String filePath;
    private DateParser dateParser;

    /**
     * Constructs a Storage object with a given date parser and a given filepath.
     * 
     * @param dateParser DateParser object to handle the dates within the file
     * @param filePath Path of the data file
     */
    public Storage(DateParser dateParser, String filePath) {
        assert dateParser != null : "Storage constructor was passed a null arg (dateParser)";
        assert filePath != null : "Storage constructor was passed a null arg (filePath)";
        assert filePath.length() > 0 : "Storage constructor was passed an empty String (filePath)";

        this.dateParser = dateParser;
        this.filePath = filePath;
    }

    /**
     * Reads the tasks from the data file; returns them in a TaskList object.
     * 
     * @return TaskList representation of the contents of the data file
     */
    public TaskList readDataFile() {

        Scanner inStream;
        TaskList taskList = new TaskList(Duke.MAX_TASKS);

        try {
            inStream = new Scanner(new FileInputStream(filePath));

            while (inStream.hasNextLine()) {
                String line = inStream.nextLine();

                switch (line.charAt(0)) {
                case 'T':
                    taskList.add(new Todo(
                        line.substring(2, line.length())
                    ));
                    break;

                case 'E':
                    String eventLine = line.substring(2, line.length());
                    taskList.add(new Event(
                        eventLine.split("/")[0],
                        dateParser.parse(
                            eventLine.split("/")[1]
                        )
                    ));
                    break;

                case 'D':
                    String deadlineLine = line.substring(2, line.length());
                    taskList.add(new Deadline(
                        deadlineLine.split("/")[0],
                        dateParser.parse(
                            deadlineLine.split("/")[1]
                        )
                    ));
                    break;

                case 'A':
                    String afterTaskLine = line.substring(2, line.length());
                    taskList.add(new AfterTask(
                        afterTaskLine.split("/")[0],
                        dateParser.parse(
                            afterTaskLine.split("/")[1]
                        )
                    ));
                    break;
    
                default:
                    break;
                }

                if (line.charAt(1) == '1') {
                    taskList
                        .get(taskList.size() - 1)
                        .markAsDone();
                }
            }

            inStream.close();
            return taskList;

        } catch (FileNotFoundException e) {
            System.out.println("Data file not found, starting with empty task list");
        
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new TaskList(Duke.MAX_TASKS);
    }

    /**
     * Writes the provided TaskList to the data file.
     * 
     * @param taskList The TaskList to be written
     */
    public void writeDataFile(TaskList taskList) {
        assert taskList != null : "writeDataFile was passed a null argument";

        PrintWriter outStream;

        try {
            outStream = new PrintWriter(
                new FileOutputStream(filePath)
            );

            for (Task t : taskList) {
                outStream.println(
                    saveTask(t)
                );
            }

            outStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String saveTask(Task t) {
        
        assert t != null : "saveTask was passed a null Task to save";

        String doneChar = (t.isDone ? "1" : "0");
        String classChar;
        String timeStr;

        if (t instanceof Todo) {
            classChar = "T";

        } else if (t instanceof Event) {
            classChar = "E";

        } else if (t instanceof Deadline) {
            classChar = "D";

        } else if (t instanceof AfterTask) {
            classChar = "A";
        } else {
            classChar = " ";
        }

        if (t instanceof Event) {
            timeStr = "/" + dateParser.format(
                ((Event)t).getAt()
            );

        } else if (t instanceof Deadline) {
            timeStr = "/" + dateParser.format(
                ((Deadline)t).getBy()
            );
            
        } else if (t instanceof AfterTask) {
            timeStr = "/" + dateParser.format(
                ((AfterTask)t).getAfter()
            );
        } else {
            timeStr = "";
        }

        return classChar + doneChar + t.getDescription() + timeStr;
    }
}
