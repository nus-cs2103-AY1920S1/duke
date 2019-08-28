import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

public class Duke {

    private static void writeToFile(String path, String text) throws IOException {

        FileWriter fw = new FileWriter(path);// create a FileWriter for the given file path
        fw.write(text);
        fw.close();
    }

    private static void importFileContents(String path, Bot b) throws FileNotFoundException {
        File f = new File(path); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String command = s.nextLine();
            b.read(command);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bot b = new Bot();

        try {
            importFileContents("../data/duke.txt", b);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        b.greet();
        b.getResponse();

        while (true) {
            String command = sc.nextLine();//read user input
            if (command.equals("bye")) {
                b.exit();
                b.getResponse();
                break;
            } else if (command.equals("list")) {
                b.list();
                b.getResponse();
            } else if (command.length() > 4 && command.substring(0, 4).equals("done")) {
                if (command.charAt(4) != ' ') {
                    try {
                        b.add(command);
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    try {
                        b.done(Integer.valueOf(command.substring(5)));
                        try {
                            writeToFile("../data/duke.txt", b.generateInfo());
                        } catch (IOException e) {
                            System.out.println("Something went wrong: " + e.getMessage());
                        }
                        b.getResponse();
                    } catch (Exception e) {
                        System.out.println("    ____________________________________________________________\n     " +
                                "\u2639" + " OOPS!!! I'm sorry, but I don't know what that means :-(" +
                                "\n    ____________________________________________________________\n");
                    }
                }
            } else if (command.length() > 6 && command.substring(0, 6).equals("delete")) {
                if (command.charAt(4) != ' ') {
                    try {
                        b.add(command);
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    try {
                        b.delete(Integer.valueOf(command.substring(7)));
                        try {
                            writeToFile("../data/duke.txt", b.generateInfo());
                        } catch (IOException e) {
                            System.out.println("Something went wrong: " + e.getMessage());
                        }
                        b.getResponse();
                    } catch (Exception e) {
                        System.out.println("    ____________________________________________________________\n     " +
                                "\u2639" + " OOPS!!! I'm sorry, but I don't know what that means :-(" +
                                "\n    ____________________________________________________________\n");
                    }
                }
            } else {
                try {
                    b.add(command);
                    b.getResponse();
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
                try {
                    writeToFile("../data/duke.txt", b.generateInfo());
                } catch (IOException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                }
            }
        }

    }
}
