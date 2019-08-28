import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

public class Duke {
    public static LocalDateTime convertDateAndTime(String rawTimestamp) throws DukeException {
        String[] dateTime = rawTimestamp.split(" ");
        String[] datePortion = dateTime[0].split("/");
        String timePortion = dateTime[1];


        if (dateTime.length == 2) {
            int date = Integer.parseInt(datePortion[0]);
            int month = Integer.parseInt(datePortion[1]);
            int year = Integer.parseInt(datePortion[2]);
            int hour = Integer.parseInt(timePortion.substring(0, 2));
            int minute = Integer.parseInt(timePortion) % 100;

            return LocalDateTime.of(year, month, date, hour, minute);
        } else {
            throw new DukeException("Date time format invalid");
        }
    }

    public static void main(String[] args) {
        LinkedList<Task> taskList = new LinkedList<>();
        taskList = DukeFileEditor.loadFile();

//        taskList.add(new Todo("buset"));
//        DukeFileEditor.writeFile(taskList);

//        for (Task t1: taskList) {
//            System.out.println(t1);
//        }

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);
        String inputText = sc.nextLine();

        while (!inputText.equals("bye")) {
            // parsing the input details into 2 parts: actionKey and descriptionKey (keyList[1])
            String[] keyList = inputText.split(" ", 2);
            String actionKey = keyList[0];

            try {
                if (inputText.equals("list")) { // to print all the list of plans
                    System.out.println("Here are the tasks in your list:");
                    int counter = 1;

                    for (Task subTask : taskList) {
                        System.out.println(counter + ". " + subTask);
                        counter++;
                    }
                } else if (actionKey.equals("done")) { // mark as done if the plan is finished
                    System.out.println("Nice! I've marked this task as done: ");

                    int index = Integer.parseInt(inputText.split(" ")[1]);
                    Task selectedTask = taskList.get(index - 1);
                    selectedTask.markAsDone();
                    DukeFileEditor.writeFile(taskList);

                    System.out.println("[" + selectedTask.getStatusIcon() + "] " + selectedTask.getDescription());
                } else if (actionKey.equals("delete")) { // delete a specific plan
                    int index = Integer.parseInt(keyList[1]);
                    System.out.println("Noted. I've removed this task: ");
                    System.out.println(taskList.get(index - 1));
                    taskList.remove(index - 1);
                    DukeFileEditor.writeFile(taskList);

                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                } else { // to handle addition of a specific type of plan
                    if (actionKey.equals("deadline")) {
                        if (keyList.length <= 1) {
                            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                        }

                        String[] contentList = keyList[1].split(" /by ");
                        if (contentList.length <= 1) {
                            throw new DukeException("☹ OOPS!!! Time need to be specified");
                        }

                        System.out.println("Got it. I've added this task: ");
                        LocalDateTime convertedTimeStamp = convertDateAndTime(contentList[1]);
                        Deadline newDeadline = new Deadline(contentList[0], convertedTimeStamp);
                        System.out.println(newDeadline);
                        taskList.add(newDeadline);
                        DukeFileEditor.writeFile(taskList);
                    } else if (actionKey.equals("event")) {
                        if (keyList.length <= 1) {
                            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                        }

                        String[] contentList = keyList[1].split(" /at ");
                        if (contentList.length == 1) {
                            throw new DukeException("☹ OOPS!!! Time need to be specified");
                        }

                        System.out.println("Got it. I've added this task: ");
                        LocalDateTime convertedTimeStamp = convertDateAndTime(contentList[1]);
                        Event newEvent = new Event(contentList[0], convertedTimeStamp);
                        System.out.println(newEvent);
                        taskList.add(newEvent);
                        DukeFileEditor.writeFile(taskList);
                    } else if (actionKey.equals("todo")) {
                        if (keyList.length <= 1) {
                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                        }

                        System.out.println("Got it. I've added this task: ");
                        Todo newTodo = new Todo(inputText.split(" ", 2)[1]);
                        System.out.println(newTodo);
                        taskList.add(newTodo);
                        DukeFileEditor.writeFile(taskList);
                    } else {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }

                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                }
            } catch (DukeException err) {
                System.out.println(err.getMessage());
            } catch (Exception err) {
                System.out.println(err);
            } finally {
                inputText = sc.nextLine();
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
