import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) throws Exception{
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        */

        Scanner sc = new Scanner(System.in);
        List<Task> list = new ArrayList<>();
        int pos = 0;
        // Writer

        // Load from saved file
        File file = new File("duke.txt");
        Scanner fileSc = new Scanner(file);
        while (fileSc.hasNextLine()) {
            String type = fileSc.next();
            int status;
            switch (type) {
                case "T":
                    fileSc.next();
                    status = fileSc.nextInt();
                    fileSc.next();
                    String description = fileSc.nextLine().trim();
                    Task curr = new Todo(description);
                    list.add(curr);
                    pos++;
                    if (status == 1) {
                        int pos1 = pos - 1;
                        list.get(pos1).markAsDone();
                    }
                    break;
                case "D":
                    fileSc.next();
                    status = fileSc.nextInt();
                    fileSc.next();
                    String[] split1 = new String[2];
                    split1 = fileSc.nextLine().trim().split(" \\| ");
                    Task curr1 = new Deadline(split1[0], split1[1]);
                    list.add(curr1);
                    pos++;
                    if (status == 1) {
                        int pos1 = pos - 1;
                        list.get(pos1).markAsDone();
                    }
                    break;
                case "E":
                    fileSc.next();
                    status = fileSc.nextInt();
                    fileSc.next();
                    String[] split2 = new String[2];
                    split2 = fileSc.nextLine().trim().split(" \\| ");
                    Task curr2 = new Event(split2[0], split2[1]);
                    list.add(curr2);
                    pos++;
                    if (status == 1) {
                        int pos1 = pos - 1;
                        list.get(pos1).markAsDone();
                    }
                    break;
                default:
                    break;
            }
        }

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        while (sc.hasNextLine()) {
            if (sc.hasNext("done")) {
                String dummy = sc.next();
                int number = sc.nextInt();
                int pos1 = number - 1;
                String dummy1 = sc.nextLine();
                list.get(pos1).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(list.get(pos1));

                //Write file
                String str = "";
                for (int i = 0; i < pos; i++) {
                    if (list.get(i) instanceof Todo) {
                        str = str + "T | " + list.get(i).getStatus() + " | "
                                + list.get(i).getDescription() + "\n";
                    } else if (list.get(i) instanceof Deadline) {
                        str = str + "D | " + list.get(i).getStatus() + " | "
                                + list.get(i).getDescription() + " | "
                                + ((Deadline) list.get(i)).getBy() + "\n";
                    } else {
                        str = str + "E | " + list.get(i).getStatus() + " | "
                                + list.get(i).getDescription() + " | "
                                + ((Event) list.get(i)).getAt() + "\n";
                    }
                }
                try (FileWriter fileWriter = new FileWriter("duke.txt")) {
                    String fileContent = str;
                    fileWriter.write(fileContent);
                } catch (IOException e) {
                    System.out.println("File not found.");
                }


            } else if (sc.hasNext("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            } else if (sc.hasNext("todo")) {
                try {
                    String dummy = sc.next();
                    String description = sc.nextLine().trim();
                    if (description.equals("")) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    Task curr = new Todo(description);
                    list.add(curr);
                    pos++;
                    System.out.println("Got it. I've added this task:");
                    System.out.println(curr);
                    System.out.println("Now you have " + pos + " tasks in the list.");

                    //Write file
                    String str = "";
                    for (int i = 0; i < pos; i++) {
                        if (list.get(i) instanceof Todo) {
                            str = str + "T | " + list.get(i).getStatus() + " | "
                                    + list.get(i).getDescription() + "\n";
                        } else if (list.get(i) instanceof Deadline) {
                            str = str + "D | " + list.get(i).getStatus() + " | "
                                    + list.get(i).getDescription() + " | "
                                    + ((Deadline) list.get(i)).getBy() + "\n";
                        } else {
                            str = str + "E | " + list.get(i).getStatus() + " | "
                                    + list.get(i).getDescription() + " | "
                                    + ((Event) list.get(i)).getAt() + "\n";
                        }
                    }
                    try (FileWriter fileWriter = new FileWriter("duke.txt")) {
                        String fileContent = str;
                        fileWriter.write(fileContent);
                    } catch (IOException e) {
                        System.out.println("File not found.");
                    }

                } catch (DukeException e) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                }

            } else if (sc.hasNext("deadline")) {
                String dummy = sc.next();
                String[] split = new String[2];
                split = sc.nextLine().trim().split(" /by ");
                Task curr = new Deadline(split[0], split[1]);
                list.add(curr);
                pos++;
                System.out.println("Got it. I've added this task:");
                System.out.println(curr);
                System.out.println("Now you have " + pos + " tasks in the list.");

                //Write file
                String str = "";
                for (int i = 0; i < pos; i++) {
                    if (list.get(i) instanceof Todo) {
                        str = str + "T | " + list.get(i).getStatus() + " | "
                                + list.get(i).getDescription() + "\n";
                    } else if (list.get(i) instanceof Deadline) {
                        str = str + "D | " + list.get(i).getStatus() + " | "
                                + list.get(i).getDescription() + " | "
                                + ((Deadline) list.get(i)).getBy() + "\n";
                    } else {
                        str = str + "E | " + list.get(i).getStatus() + " | "
                                + list.get(i).getDescription() + " | "
                                + ((Event) list.get(i)).getAt() + "\n";
                    }
                }
                try (FileWriter fileWriter = new FileWriter("duke.txt")) {
                    String fileContent = str;
                    fileWriter.write(fileContent);
                } catch (IOException e) {
                    System.out.println("File not found.");
                }

            } else if (sc.hasNext("event")) {
                String dummy = sc.next();
                String[] split = new String[2];
                split = sc.nextLine().trim().split(" /at ");
                Task curr = new Event(split[0], split[1]);
                list.add(curr);
                pos++;
                System.out.println("Got it. I've added this task:");
                System.out.println(curr);
                System.out.println("Now you have " + pos + " tasks in the list.");

                //Write file
                String str = "";
                for (int i = 0; i < pos; i++) {
                    if (list.get(i) instanceof Todo) {
                        str = str + "T | " + list.get(i).getStatus() + " | "
                                + list.get(i).getDescription() + "\n";
                    } else if (list.get(i) instanceof Deadline) {
                        str = str + "D | " + list.get(i).getStatus() + " | "
                                + list.get(i).getDescription() + " | "
                                + ((Deadline) list.get(i)).getBy() + "\n";
                    } else {
                        str = str + "E | " + list.get(i).getStatus() + " | "
                                + list.get(i).getDescription() + " | "
                                + ((Event) list.get(i)).getAt() + "\n";
                    }
                }
                try (FileWriter fileWriter = new FileWriter("duke.txt")) {
                    String fileContent = str;
                    fileWriter.write(fileContent);
                } catch (IOException e) {
                    System.out.println("File not found.");
                }

            } else if (sc.hasNext("delete")){
                String dummy = sc.next();
                int line = sc.nextInt();
                String dummy1 = sc.nextLine();
                Task curr = list.get(line - 1);
                System.out.println("Noted. I've removed this task:");
                System.out.println(curr);
                list.remove(line - 1);
                pos--;
                System.out.println("Now you have " + pos + " tasks in the list.");

                //Write file
                String str = "";
                for (int i = 0; i < pos; i++) {
                    if (list.get(i) instanceof Todo) {
                        str = str + "T | " + list.get(i).getStatus() + " | "
                                + list.get(i).getDescription() + "\n";
                    } else if (list.get(i) instanceof Deadline) {
                        str = str + "D | " + list.get(i).getStatus() + " | "
                                + list.get(i).getDescription() + " | "
                                + ((Deadline) list.get(i)).getBy() + "\n";
                    } else {
                        str = str + "E | " + list.get(i).getStatus() + " | "
                                + list.get(i).getDescription() + " | "
                                + ((Event) list.get(i)).getAt() + "\n";
                    }
                }
                try (FileWriter fileWriter = new FileWriter("duke.txt")) {
                    String fileContent = str;
                    fileWriter.write(fileContent);
                } catch (IOException e) {
                    System.out.println("File not found.");
                }

            } else {
                try {
                    String input = sc.nextLine();
                    if (!input.equals("list")) {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                    System.out.println("Here are the tasks in your list:");
                    int k = 0;
                    while (k != pos) {
                        int bullet = k + 1;
                        System.out.println(bullet + "." + list.get(k));
                        k++;
                    }
                } catch (DukeException e) {
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }

        }

    }

}
