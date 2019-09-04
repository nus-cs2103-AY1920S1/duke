import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
import java.io.IOException;
import java.io.FileNotFoundException;

public class MainManager {
    private ArrayList<Task> list;

    public MainManager() {
        this.list = new ArrayList<>();
    }

    private String[] getEventDetails(String command) throws TaskException{
        if(command.length() <= 5) {
            throw new TaskException();
        }
        String woCommand = command.substring(6);
        return woCommand.split(" /at ");
    }

    private String[] getDeadlineDetails(String command) throws TaskException{
        if(command.length() <= 8) {
            throw new TaskException();
        }
        String woCommand = command.substring(9);
        return woCommand.split(" /by ");
    }

    private void writeToFile() throws FileNotFoundException, IOException {
        try {
            Path p = Paths.get(System.getProperty("user.dir"));
            File data = new File(p + "/data/duke.txt");
            FileWriter fw = new FileWriter(data);

            String toWrite = "";
            for(Task task : list) {
                toWrite += task.toFile() + " \n";
            }

            fw.write(toWrite);
            fw.close();
        } catch (FileNotFoundException Fe) {
            System.out.println(Fe);
        } catch (IOException IOe) {
            System.out.println(IOe);
        }
    }

    public void readFromFile() throws FileNotFoundException{
        Path p = Paths.get(System.getProperty("user.dir"));
        File data = new File(p + "/data/duke.txt");
        try {
            Scanner sc = new Scanner(data);

            while(sc.hasNextLine()) {
                String[] next = sc.nextLine().split("-");

                switch (next[0]) {
                case ("T"):
                    Task todo = new Todo(next[2]);

                    if (Integer.valueOf(next[1]) == 1) {
                        todo.setDone();
                    }
                    list.add(todo);
                    break;


                case ("E"):
                    Task event = new Event(next[2], next[3]);

                    if (Integer.valueOf(next[1]) == 1) {
                        event.setDone();
                    }
                    list.add(event);
                    break;

                case ("D"):
                    Task deadline = new Deadline(next[2], next[3]);

                    if (Integer.valueOf(next[1]) == 1) {
                        deadline.setDone();
                    }
                    list.add(deadline);
                    break;
                }
            }
            sc.close();
        } catch(FileNotFoundException fE) {
            System.out.println(fE);
        }


    }

    public void run(){
        Scanner sc = new Scanner(System.in);

        while(sc.hasNext()) {
            String toPrint = sc.nextLine();
            String[] command = toPrint.split(" ");

            switch(command[0]) {
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                break;

            case "list":
                for(int i = 0; i < list.size(); i++) {
                    if(list.get(i) != null) {
                        System.out.println(i + 1 + "." + list.get(i));
                    }
                }
                break;

            case "done" :
                try {
                    if(toPrint.length() <= 4) {
                        throw new DoneException();
                    }
                    System.out.println("Nice! I've marked this task as done:");
                    Task currTask = list.get(Integer.parseInt(command[1]) - 1);
                    currTask.setDone();
                    System.out.println(" " + currTask);

                    writeToFile();
                } catch (DoneException doEx) {
                    System.out.println(doEx);
                } catch (FileNotFoundException Fe) {
                    System.out.println(Fe);
                } catch (IOException IOe) {
                    System.out.println(IOe);
                }
                break;

            case "todo":
                try {
                    if(toPrint.length() <= 4) {
                        throw new TaskException();
                    }
                    String todoName = toPrint.substring(5);
                    Task todo = new Todo(todoName);
                    list.add(todo);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println("    " + todo);
                    System.out.println("Now you have " + list.size() + " tasks in the list.");

                    writeToFile();
                } catch(TaskException toEx)  {
                    System.out.println(toEx);
                } catch (FileNotFoundException Fe) {
                    System.out.println(Fe);
                } catch (IOException IOe) {
                    System.out.println(IOe);
                }
                break;

            case "deadline":
                try {
                    String[] deadlineDetails = getDeadlineDetails(toPrint);
                    Task deadline = new Deadline(deadlineDetails[0], deadlineDetails[1]);
                    list.add(deadline);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println("    " + deadline);
                    System.out.println("Now you have " + list.size() + " tasks in the list.");

                    writeToFile();
                } catch(TaskException dlEx) {
                    System.out.println(dlEx);
                } catch (FileNotFoundException Fe) {
                    System.out.println(Fe);
                } catch (IOException IOe) {
                    System.out.println(IOe);
                }
                break;

            case "event":
                try {
                    String[] eventDetails = getEventDetails(toPrint);
                    Task event = new Event(eventDetails[0], eventDetails[1]);
                    list.add(event);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println("    " + event);
                    System.out.println("Now you have " + list.size() + " tasks in the list.");

                    writeToFile();
                } catch (TaskException evEx) {
                    System.out.println(evEx);
                } catch (FileNotFoundException Fe) {
                    System.out.println(Fe);
                } catch (IOException IOe) {
                    System.out.println(IOe);
                }
                break;

            case "delete":
                try {
                    if (command.length <= 1){
                        throw new DeleteException();
                    } else {
                        int index = Integer.parseInt(command[1]);

                        if (command.length <= 1 || index > list.size() || index <= 0) {
                            throw new DeleteException();
                        }

                        System.out.println(" Noted. I've removed this task: ");
                        System.out.println("    " + list.remove(index - 1));
                        System.out.println("Now you have " + list.size() + " tasks in the list.");

                        writeToFile();
                    }
                } catch(DeleteException deEx) {
                    System.out.println(deEx);
                } catch (FileNotFoundException Fe) {
                    System.out.println(Fe);
                } catch (IOException IOe) {
                    System.out.println(IOe);
                }
                break;


            default:
                System.out.println(new UnknownCommandException());
                break;
            }

            if(toPrint.equals("bye")) {
                break;
            }
        }
    }
}
