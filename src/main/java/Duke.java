import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String border = "____________________________________________________________";
        System.out.println(border + "\nHello! I'm Duke\nWhat can I do for you?\n" + border);

        ArrayList<Task> userList = new ArrayList<>();
        int counter = 0;
        Scanner scanner = new Scanner(System.in);
        DukeReadFile rf = new DukeReadFile("data/duke.txt");
        DukeWriteFile wf = new DukeWriteFile("data/duke.txt");
        try {

            rf.readFileContent();
            userList.addAll(rf.myTask());

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println(border + "\nBye. Hope to see you again soon!\n" + border);
                break;
            } else if (input.equals("list")) {
                int itemNumber = 1;
                counter = userList.size();
                if (counter == 0) {
                    System.out.println(border + "\nYou currently have no task!\n" + border);
                } else {
                    System.out.println(border + "\nHere are the tasks in your list:");
                    for (int i = 0; i < counter; i++) {
                        System.out.println(itemNumber + "." + userList.get(i).toString());
                        itemNumber ++;
                    }
                    System.out.println(border);
                }

            } else if (input.startsWith("done")) {
                try {
                    String[] taskDone = input.split(" ");
                    if (taskDone.length == 1) {
                        throw new DukeException(border +
                                "\n☹ OOPS!!! Which task do you want to complete?\n" + border);
                    }
                    if (taskDone.length > 2) {
                        throw new DukeInvalidArgumentException("OOPS!! Wrong format! Format: done [task number]", input);
                    }

                    int taskIndex = Integer.parseInt(taskDone[1]);
                    userList.get(taskIndex - 1).markAsDone();
                    System.out.println(border + "\nNice! I've marked this task as done:");
                    System.out.println(userList.get(taskIndex - 1).toString() +  "\n" + border);
                    wf.writeToFile(writeFile(userList));

                } catch (DukeInvalidArgumentException e) {

                    System.out.println(border +"\n" + e + "\n" + border);

                } catch (DukeException e) {

                    System.out.println(e);

                } catch (IndexOutOfBoundsException e) {

                    System.out.println(border + "\nTask number not found! Try again!\n" + border);
                } catch (NumberFormatException e) {

                    System.out.println(border + "\nOOPS!! Please input a task number!\n" + border);
                } catch (IOException e) {

                    System.out.println(border +"\n" + e + "\n" + border);
                }

            } else if (input.startsWith("deadline")) {
                try {
                    if (input.length() < 9) {
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }

                    String[] deadLineDate = input.substring(9).split(" /by ");
                    if (deadLineDate.length != 2) {
                        throw new  DukeInvalidArgumentException("OOPS!! Wrong format! Format: deadline [Task] /by [deadline]",
                                input);
                    }
                    String taskD = deadLineDate[0];
                    String dateD = deadLineDate[1];
                    Deadline newDeadLine = new Deadline(taskD, dateD);
                    userList.add(newDeadLine);
                    counter ++;
                    System.out.println(border + "\nGot it. I've added this task:");
                    System.out.println(newDeadLine.toString());
                    System.out.println("Now you have " + counter + " tasks in the list.\n" + border);
                    wf.appendToFile("D/" + newDeadLine.getStatus() + "/" +
                            newDeadLine.getDescription() + "/" +newDeadLine.getDeadline() + "\n");

                } catch (DukeInvalidArgumentException e) {

                    System.out.println(border +"\n" + e + "\n" + border);

                } catch (DukeException e) {

                    System.out.println(border + "\n" + e + "\n" + border);
                } catch (IOException e) {

                    System.out.println(border + "\n" + e + "\n" + border);
                }

            }else if (input.startsWith("event")) {

                try {
                    if (input.length() < 6) {
                        throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                    }

                    String[] eventDate = input.substring(6).split(" /at ");
                    if (eventDate.length != 2) {
                        throw new  DukeInvalidArgumentException("OOPS!! Wrong format! Format: event [Task] /at [time]",
                                input);
                    }
                    String taskE = eventDate[0];
                    String dateE = eventDate[1];
                    Event newEvent = new Event(taskE, dateE);
                    userList.add(newEvent);
                    counter ++;
                    System.out.println(border + "\nGot it. I've added this task:");
                    System.out.println(newEvent.toString());
                    System.out.println("Now you have " + counter + " tasks in the list.\n" + border);
                    wf.appendToFile("E/" + newEvent.getStatus() + "/" +
                            newEvent.getDescription() + "/" +newEvent.getVenue() + "\n");

                } catch (DukeInvalidArgumentException e) {

                    System.out.println(border +"\n" + e + "\n" + border);

                } catch (DukeException e) {

                    System.out.println(border + "\n" + e + "\n" + border);
                } catch (IOException e) {

                    System.out.println(border + "\n" + e + "\n" + border);
                }

            } else if (input.startsWith("todo")) {
                try {
                    if (input.length() < 5) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }

                    String taskToDo = input.substring(5);
                    Todo newToDo = new Todo(taskToDo);
                    userList.add(newToDo);
                    counter ++;
                    System.out.println(border + "\nGot it. I've added this task:");
                    System.out.println(newToDo.toString());
                    System.out.println("Now you have " + counter + " tasks in the list.\n" + border);
                    wf.appendToFile("T/" + newToDo.getStatus() + "/" +
                            newToDo.getDescription() + "\n");

                } catch (DukeException e) {
                    System.out.println(border + "\n" + e + "\n" + border);
                } catch (IOException e) {

                    System.out.println(border + "\n" + e + "\n" + border);
                }

            } else if (input.startsWith("delete")) {
                try {
                    String[] taskDelete = input.split(" ");
                    if (taskDelete.length == 1) {
                        throw new DukeException("☹ OOPS!!! Which task would you like to delete?");
                    }

                    if (taskDelete.length > 2) {
                        throw new DukeInvalidArgumentException("OOPS!! Wrong format! Format: delete [task number]", input);
                    }

                    int taskIndex = Integer.parseInt(taskDelete[1]);
                    String removedTask = userList.get(taskIndex - 1).toString();
                    userList.remove(taskIndex - 1);
                    System.out.println(border + "\nNoted. I've removed this task:");
                    System.out.println(removedTask);

                    counter --;
                    System.out.println("Now you have " + counter + " tasks in the list.\n" + border);
                    wf.writeToFile(writeFile(userList));

                } catch (DukeInvalidArgumentException e) {

                    System.out.println(border + "\n" + e + "\n" + border);

                } catch (NumberFormatException e) {

                    System.out.println(border + "\nOOPS!! Please input a task number!\n" + border);

                } catch (IndexOutOfBoundsException e) {

                    System.out.println(border + "\nTask number not found! Try again!\n" + border);

                } catch (DukeException e) {

                    System.out.println(border + "\n" + e + "\n" + border);

                } catch (IOException e) {

                    System.out.println(border + "\n" + e + "\n" + border);

                }
            } else {
                try {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                } catch (DukeException e){
                    System.out.println(border + "\n" + e + "\n" + border);
                }
            }

        }


    }

    public static String writeFile(ArrayList<Task> currentTask) {
        String writeTask = "";

        for(int i = 0; i < currentTask.size(); i++) {

            if (currentTask.get(i) instanceof Todo) {
                writeTask += "T/" + currentTask.get(i).getStatus() +
                        "/" + currentTask.get(i).getDescription() + "\n";
            } else if (currentTask.get(i) instanceof Event) {
                writeTask += "E/" + currentTask.get(i).getStatus() +
                        "/" + currentTask.get(i).getDescription() + "/" + ((Event) currentTask.get(i)).getVenue() + "\n";
            } else {
                writeTask += "D/" + currentTask.get(i).getStatus() +
                        "/" + currentTask.get(i).getDescription() + "/" + ((Deadline) currentTask.get(i)).getDeadline() + "\n";
            }
        }

        return writeTask;
    }
}
