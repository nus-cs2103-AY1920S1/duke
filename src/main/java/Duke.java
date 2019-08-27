import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.text.SimpleDateFormat;

public class Duke {

    private static void appendFile(Task tsk) throws IOException {
        FileWriter fw = new FileWriter("../../../data/duke.txt", true);
        String status = tsk.getIsDone() ? "1" : "0";

        try {
            if (tsk instanceof Todo) {
                fw.write("T, " + status + ", " + tsk.getDescription() + "\n");
            } else if (tsk instanceof Deadline) {
                fw.write("D, " + status + ", " + tsk.getDescription() + ", " + ((Deadline) tsk).getBy() + "\n");
            } else if (tsk instanceof Event) {
                fw.write("E, " + status + ", " + tsk.getDescription() + ", " + ((Event) tsk).getAt() + "\n");
            } else {
                throw new DukeException("☹ OOPS! Error in file handling");
            }
        } catch (DukeException exp) {
            System.out.println(exp);
        }
        fw.close();

    }

    private static void updateFile(ArrayList<Task> list) throws IOException {

        BufferedWriter bfw = new BufferedWriter(new FileWriter("../../../data/duke.txt"));
        int counter = 0;
        while(counter < list.size()) {
            Task tsk = list.get(counter);
            String status = tsk.getIsDone() ? "1" : "0";
            try {
                if (tsk instanceof Todo) {
                    bfw.write("T, " + status + ", " + tsk.getDescription());
                    bfw.newLine();
                    counter++;
                } else if (tsk instanceof Deadline) {
                    bfw.write("D, " + status + ", " + tsk.getDescription() + ", " + ((Deadline) tsk).getBy());
                    bfw.newLine();
                    counter++;
                } else if (tsk instanceof Event) {
                    bfw.write("E, " + status + ", " + tsk.getDescription() + ", " + ((Event) tsk).getAt());
                    bfw.newLine();
                    counter++;
                } else {
                    throw new DukeException("☹ OOPS! Error in file handling");
                }
            } catch (DukeException exp) {
                System.out.println(exp);
            }
        }
        bfw.close();
    }

    private static ArrayList<Task> fetchDataFromFile() throws IOException {
        String filePath = "../../../data/duke.txt";
        File f = new File(filePath);
        ArrayList<Task> list = new ArrayList<>();
        Scanner s = new Scanner(f);
        while(s.hasNextLine()) {
            String[] task = s.nextLine().replaceAll(", ", ",").split(",");
            Task tsk;
            if(task[0].equals("T")) {
                tsk = new Todo(task[2]);
            } else if(task[0].equals("D")) {
                try {
                    SimpleDateFormat formatter = new SimpleDateFormat("EEEEE MMMMM dd HH:mm:ss z yyyy");
                    tsk = new Deadline(task[2], formatter.parse(task[3]));
                } catch (java.text.ParseException exp) {
                    exp.printStackTrace();
                    break;
                }
            } else {
                try {
                    SimpleDateFormat formatter = new SimpleDateFormat("EEEEE MMMMM dd HH:mm:ss z yyyy");
                    tsk = new Event(task[2], formatter.parse(task[3]));
                } catch (java.text.ParseException exp) {
                    exp.printStackTrace();
                    break;
                }

            }

            if(task[1].equals("1")) {
                tsk.markAsDone();
            }
            list.add(tsk);
        }
        s.close();
        return list;
    }

    /**
     * The duke project.
     */

    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println(logo);

        String line = "    __________________________________";

        System.out.println(line);
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        System.out.println(line);

        Scanner sc = new Scanner(System.in);
        String echo = "";
        ArrayList<Task> list = fetchDataFromFile();

        if (sc.hasNextLine()) {
            echo = sc.nextLine();
            while (!echo.equals("bye")) {
                String[] echoArr = echo.split(" ");
                System.out.println(line);
                try {
                    if (echo.equals("list")) {
                        System.out.println("    Here are the tasks in your list:");
                        for (int i = 0; i < list.size(); i++) {
                            System.out.println("    " + (i + 1) + ". " + list.get(i));
                        }
                    } else if (echoArr[0].equals("done")) {
                        try {
                            if(echoArr.length == 1) {
                                throw new DukeException("☹ OOPS!!! Please specify which task is done");
                            } else if (Integer.parseInt(echoArr[1]) > list.size()) {
                                throw new DukeException("☹ OOPS!!! Task " + echoArr[1] + " does not exist");
                            } else {
                                list.get(Integer.parseInt(echoArr[1]) - 1).markAsDone();

                                System.out.println("    Nice! I've marked this task as done: ");
                                System.out.println("     " + list.get(Integer.parseInt(echoArr[1]) - 1));
                                updateFile(list);

                            }
                        } catch (DukeException exp) {
                            System.out.println("    " + exp.getMessage());
                        }

                    } else if (echoArr[0].equals("delete")) {
                        try {
                            if(echoArr.length == 1) {
                                throw new DukeException("☹ OOPS!!! Please specify which task is to be deleted");
                            } else if (Integer.parseInt(echoArr[1]) > list.size()) {
                                throw new DukeException("☹ OOPS!!! Task " + echoArr[1] + " does not exist");
                            } else {
                                Task tsk = list.remove(Integer.parseInt(echoArr[1]) - 1);
                                System.out.println("    Noted. I've removed this task:");
                                System.out.println("     " + tsk);
                                System.out.println("    Now you have " + list.size() + " tasks in the list.");
                                updateFile(list);
                            }
                        } catch (DukeException exp) {
                            System.out.println("    " + exp.getMessage());
                        }

                    } else if (echoArr[0].equals("deadline") || echoArr[0].equals("todo") || echoArr[0].equals("event")) {
                        if (echoArr[0].equals("deadline")) {
                            try {
                                if (echoArr.length == 1) {
                                    throw new DukeException("☹ OOPS!!! The description of deadline cannot be empty");
                                } else if (!echo.contains("/by")) {
                                    throw new DukeException("☹ OOPS!!! The deadline must be completed by a certain date");
                                } else {
                                    try {
                                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
                                        list.add(new Deadline(echo.substring(echo.indexOf(" ") + 1, echo.indexOf("/") - 1),
                                                formatter.parse(echo.substring(echo.indexOf("/by") + 4))));
                                    } catch (java.text.ParseException exp) {
                                        exp.printStackTrace();
                                        break;
                                    }

                                    appendFile(list.get(list.size() - 1));
                                }
                            } catch (DukeException exp) {
                                System.out.println("    " + exp.getMessage());
                            }
                        } else if (echoArr[0].equals("todo")) {
                            try {
                                if (echoArr.length == 1) {
                                    throw new DukeException("☹ OOPS!!! The description of deadline cannot be empty");
                                } else {
                                    list.add(new Todo(echo.substring(echo.indexOf(" ") + 1)));
                                    appendFile(list.get(list.size() - 1));
                                }
                            } catch (DukeException exp) {
                                System.out.println("    " + exp.getMessage());
                            }
                        } else if (echoArr[0].equals("event")) {
                            try {
                                if (echoArr.length == 1) {
                                    throw new DukeException("☹ OOPS!!! The description of event cannot be empty");
                                } else if (!echo.contains("/at")) {
                                    throw new DukeException("☹ OOPS!!! The event must be at by a certain date");
                                } else {

                                    try {
                                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
                                        list.add(new Event(echo.substring(echo.indexOf(" ") + 1, echo.indexOf("/") - 1),
                                                formatter.parse(echo.substring(echo.indexOf("/at") + 4))));
                                    } catch (java.text.ParseException exp) {
                                        exp.printStackTrace();
                                        break;
                                    }

                                    appendFile(list.get(list.size() - 1));
                                }
                            } catch (DukeException exp) {
                                System.out.println("    " + exp.getMessage());
                            }
                        }

                        if (list.size() > 0) {
                            System.out.println("    Got it. I've added this task:");
                            System.out.println("     " + list.get(list.size() - 1));
                            System.out.println("    Now you have " + list.size() + " in the list.");
                        }
                    } else {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                } catch (DukeException exp) {
                    System.out.println("    " + exp.getMessage());
                }

                System.out.println(line);
                if (sc.hasNextLine()) {
                    echo = sc.nextLine();
                } else {
                    break;
                }
            }
        }

        if (echo.equals("bye")) {
            System.out.println(line);
            System.out.println("    Bye. Hope to see you again soon!");
            System.out.println(line);
        }
        sc.close();
    }
}