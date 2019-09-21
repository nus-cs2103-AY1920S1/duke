import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;


public class Storage {

    //public static ArrayList<Task> tasks;
    private File tempFile;
    private Tasklist tasklist;

    /**
     * Constructor for Storage instance
     *
     * @throws IOException  If file is wrong
     */
    public Storage() {
        //ArrayList<Task> tasks = new ArrayList<Task>();
        Tasklist tasklist = new Tasklist();
        this.tasklist = tasklist;
        //this.tasks = tasks;
        String root = new File(System.getProperty("user.dir")).getParentFile().getPath();
        StringBuilder path = new StringBuilder();
        path.append(root);

        File directory = new File(path + "/duke/src/main//java/data");

        if (!directory.exists()) {
            directory.mkdirs();
        }

        this.tempFile = new File(directory + "/duke.txt");
        try {
            if (hasFile()) {
                readFile();
            } else {
                createFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to retrieve Tasklist to get tasks
     *
     * @return tasklist of tasks
     */

    public Tasklist getTasks() {
        assert tasklist != null : "tasklist cannot be null";
        return tasklist;
    }

    /**
     * Saves the current tasks at hand into the txt file
     *
     * @param tasks  ArrayList of the tasks
     * @throws IOException  If file is wrong
     */

    public void saveFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(tempFile);
        String texttoadd = "";
        System.out.println("These will be saved");
        for (int i = 0; i < tasks.size(); i ++) {
            int num = i + 1;
            String textpart = num + ". " + tasks.get(i);
            if (i == 0) {
                texttoadd = textpart;
            } else {
                texttoadd = texttoadd + "\n" + textpart;
            }
        }
        try {
            fw.write(texttoadd);
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public boolean hasFile() {
        return tempFile.exists();
    }

    /**
     * Creates a file for the data to be saved
     *
     * @throws IOException  If file is wrong
     */

    public void createFile() throws IOException {
        boolean exists = tempFile.exists();
        boolean fileCreated = tempFile.createNewFile();    //no file yet so create
    }

    /**
     * Reads a file and prints the data.
     * Also calls a createTasks function to save those on the list to Tasklist.
     *
     * @throws FileNotFoundException  If file is wrong
     */

    public void readFile() throws FileNotFoundException {
        ArrayList<String> filetasks = new ArrayList<String>();

        Scanner s = new Scanner(tempFile);
        while (s.hasNext()) {
            String t = s.nextLine();
            //System.out.println(t);
            filetasks.add(t);
        }
        createTasks(filetasks);
    }

    public void createTasks(ArrayList<String> filetasks) {
        while (!filetasks.isEmpty()) {
            String tas = filetasks.remove(0);
            String[] splittedtas = tas.split("");
            if (splittedtas[4].equals("T")) {
                String word = "";
                for (int i = 10; i < splittedtas.length; i ++) {
                    word = word + splittedtas[i];
                }
                tasklist.addTodo(new Todo(word));
                if (isDone(splittedtas[7])) {
                    Task t = tasklist.returnTasks().get(tasklist.size()-1);
                    t.markAsDone();
                }
            } else if (splittedtas[4].equals("D")) {
                String word = "";
                String deadline = "";
                for (int i = 10; i < splittedtas.length; i ++) {
                    if (splittedtas[i].equals("(")) {
                        for (int j = i+5; j < splittedtas.length-1; j++) {
                            deadline = deadline + splittedtas[j];
                        }
                        break;
                    } else {
                        if (splittedtas[i+1].equals("(")) {

                        } else {
                            word = word + splittedtas[i];
                        }
                    }
                }
                tasklist.addDeadline(new Deadline(word, deadline));
                if (isDone(splittedtas[7])) {
                    Task t = tasklist.returnTasks().get(tasklist.size()-1);
                    t.markAsDone();
                }
            } else if (splittedtas[4].equals("E")) {
                String word = "";
                String date = "";
                for (int i = 10; i < splittedtas.length; i ++) {
                    if (splittedtas[i].equals("(")) {
                        for (int j = i+5; j < splittedtas.length-1; j++) {
                            date = date + splittedtas[j];
                        }
                        break;
                    } else {
                        if (splittedtas[i+1].equals("(")) {

                        } else {
                            word = word + splittedtas[i];
                        }
                    }
                }
                tasklist.addEvent(new Event(word, date));
                if (isDone(splittedtas[7])) {
                    Task t = tasklist.returnTasks().get(tasklist.size()-1);
                    t.markAsDone();
                }
            } else {

            }
        }
    }

    public static boolean isDone(String op) {
        return (op.equals("\u2713"));
    }
}