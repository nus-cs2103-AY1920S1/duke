import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

public class Duke {
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd);
        fw.close();
    }

    private static void rewriteFile(String filePath, ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        String textToAdd = "";
        for (Task t : tasks) {
            String type = "";
            String time = "";
            if (t instanceof Event) {
                type = "E";
                time = ((Event) t).getTime();
            } else if (t instanceof ToDo) {
                type = "T";
            } else if (t instanceof Deadline) {
                type = "D";
                time = ((Deadline) t).getTime();
            }
            textToAdd += "[" + type + "] -- " + "[" + t.getStatusIcon() + "] -- " + t.getDescription();
            if (t instanceof Event || t instanceof Deadline) {
                textToAdd += " -- " + time;
            }
            textToAdd += "\n";
        }
        fw.write(textToAdd);
        fw.close();
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        //hr is horizontal row
        String hr = "______________________________________________________________________";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(hr);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println(hr);
        ArrayList<Task> arr = new ArrayList<>();
        try {
            File f = new File("C:/Users/mtg-1/OneDrive/Documents/NUS/Y2S1/CS2103/repos/" +
                    "dukerepo/data/history.txt");
            Scanner reader = new Scanner(f);
            while(reader.hasNextLine()) {
                String s = reader.nextLine();
                String[] tempArray = s.split(" -- ");
                Task t;
                if (tempArray[0].equals("[E]")) {
                    t = new Event(tempArray[2], tempArray[3]);
                } else if (tempArray[0].equals("[T]")) {
                    t = new ToDo(tempArray[2]);
                } else if (tempArray[0].equals("[D]")) {
                    t = new Deadline(tempArray[2], tempArray[3]);
                } else {
                    throw new DukeException("Not a valid Task type");
                }
                if (tempArray[1].equals("[+]")) {
                    t.markAsDone();
                }
                arr.add(t);
            }
        } catch (IOException | DukeException e) {
            System.err.println(e.getMessage());
        }
        while(true) {
            String command = sc.nextLine();
            System.out.println(hr);
            String[] commandWords = command.split(" ");
            String order = commandWords[0];
            if (order.equals("done")){
                int index = Integer.parseInt(commandWords[1]) - 1;
                try {
                    if (index >= arr.size() || index < 0) {
                        throw new DukeException(" :( OOPS!!! Requested task number is not available");
                    }
                    Task temp = arr.get(index);
                    temp.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + temp);
                    rewriteFile("C:/Users/mtg-1/OneDrive/Documents/NUS/Y2S1/CS2103/repos/" +
                            "dukerepo/data/history.txt",
                            arr);
                } catch (DukeException | IOException de) {
                    System.err.println(de.getMessage());
                } catch (NumberFormatException nfe) {
                    System.err.println(" :( OOPS!!! Invalid format. Enter number of task to be marked as done.");
                } finally {
                    System.out.println(hr);
                }
            } else if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(hr);
                break;
            } else if(command.equals("list")) {
                for(int i = 0; i < arr.size(); i++) {
                    Task temp = arr.get(i);
                    System.out.println((i + 1) + ". " + temp);
                }
                System.out.println(hr);
            } else {
                if (order.equals("todo")) {
                    try {
                        if (commandWords.length == 1) {
                            throw new DukeException(" :( OOPS!!! The description of a todo cannot be empty.");
                        } else {
                            Task temp = new ToDo(command.split("todo ")[1]);
                            arr.add(temp);
                            System.out.println("Got it. I've added this task:");
                            System.out.println(" " + temp);
                            System.out.println("Now you have " + arr.size() + " tasks in the list.");
                        }
                        rewriteFile("C:/Users/mtg-1/OneDrive/Documents/NUS/Y2S1/CS2103/repos/" +
                                "dukerepo/data/history.txt",
                                arr);
                    } catch (DukeException | IOException de) {
                        System.err.println(de.getMessage());
                    } finally {
                        System.out.println(hr);
                    }
                } else if (order.equals("deadline")) {
                    try {
                        if (commandWords.length == 1) {
                            throw new DukeException(" :( OOPS!!! The description of a deadline cannot be empty.");
                        } else {
                            if (command.split("/by").length == 1) {
                                throw new DukeException(" :( OOPS!!! A deadline must have a time.");
                            } else {
                                String instruction = command.split("deadline ")[1];
                                String[] details = instruction.split(" /by ");
                                Task temp = new Deadline(details[0], details[1]);
                                arr.add(temp);
                                System.out.println("Got it. I've added this task:");
                                System.out.println(" " + temp);
                                System.out.println("Now you have " + arr.size() + " tasks in the list.");
                            }
                            rewriteFile("C:/Users/mtg-1/OneDrive/Documents/NUS/Y2S1/CS2103/repos/" +
                                    "dukerepo/data/history.txt",
                                    arr);
                        }
                    } catch (DukeException | IOException de) {
                        System.err.println(de.getMessage());
                    } finally {
                        System.out.println(hr);
                    }
                } else if (order.equals("event")){
                    try {
                        if (commandWords.length == 1) {
                            throw new DukeException(" :( OOPS!!! The description of a event cannot be empty.");
                        } else {
                            if (command.split("/at").length == 1) {
                                throw new DukeException(" :( OOPS!!! An event must have a time.");
                            } else {
                                String instruction = command.split("event ")[1];
                                String[] details = instruction.split(" /at ");
                                Task temp = new Event(details[0], details[1]);
                                arr.add(temp);
                                System.out.println("Got it. I've added this task:");
                                System.out.println(" " + temp);
                                System.out.println("Now you have " + arr.size() + " tasks in the list.");
                            }
                            rewriteFile("C:/Users/mtg-1/OneDrive/Documents/NUS/Y2S1/CS2103/repos/" +
                                    "dukerepo/data/history.txt",
                                    arr);
                        }
                    } catch (DukeException | IOException de) {
                        System.err.println(de.getMessage());
                    } finally {
                        System.out.println(hr);
                    }
                } else if (order.equals("delete")) {
                    try {
                        if (commandWords.length == 1) {
                            throw new DukeException(" :( OOPS!!! Provided task number does not exist.");
                        } else {
                            int index = Integer.parseInt(commandWords[1]) - 1;
                            if (index >= arr.size() || index < 0) {
                                throw new DukeException(" :( OOPS!!! Task to be deleted is not available");
                            } else {
                                Task temp = arr.remove(index);
                                System.out.println("Noted. I've removed this task:");
                                System.out.println(" " + temp);
                                System.out.println("Now you have " + arr.size() + " tasks in the list.");
                            }
                        }
                        rewriteFile("C:/Users/mtg-1/OneDrive/Documents/NUS/Y2S1/CS2103/repos/" +
                                "dukerepo/data/history.txt",
                                arr);
                    } catch (DukeException | IOException de) {
                        System.err.println(de.getMessage());
                    } catch (NumberFormatException nfe) {
                        System.err.println(" :( OOPS!!! Invalid format. Enter number of task to be deleted");
                    } finally {
                        System.out.println(hr);
                    }
                }
                else {
                    try {
                        throw new DukeException(" :( OOPS!!! I'm sorry but I don't know what that means :-(");
                    } catch (DukeException de) {
                        System.err.println(de.getMessage());
                    } finally {
                        System.out.println(hr);
                    }
                }
            }
        }
    }
}
