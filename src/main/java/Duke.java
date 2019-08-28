import java.io.FileNotFoundException;
//import java.io.FileWriter;
//import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private static final Scanner S = new Scanner(System.in);

    public static void main(String[] args) throws DukeException{
        ChatLike cl = new ChatLike(); //ChatLike object to call methods

        try {
            SaveRetrieve.getFileText("../data/duke.txt", cl);
        } catch (FileNotFoundException exc) {
            System.out.println("File not found");
        }

        cl.greet();
        cl.getResponseDirect();

        while (true) {
            String comm = S.nextLine();

            if (comm.equals("bye")) {
                cl.byeUser();
                cl.getResponseDirect();
                break;
            } else if (comm.equals("list")) {
                cl.list();
                cl.getResponseDirect();
            } else if (comm.length() > 4 && comm.substring(0, 4).equals("done")) {
                if (comm.charAt(4) != ' ') {
                    try {
                        cl.add(comm);
                    } catch (DukeException exc) {
                        System.out.println(exc.getMessage());
                    }
                } else {
                    try {
                        cl.done(Integer.parseInt(comm.substring(5)));
                        try {
                            SaveRetrieve.writeOnFile("../data/duke.txt", cl.genInfoForFile());
                        } catch (IOException exc) {
                            System.out.println("Something went wrong: " + exc.getMessage());
                        }
                        cl.getResponseDirect();
                    } catch (Exception exc) {
                        System.out.println("    ____________________________________________________________\n     " +
                                "\u2639" + " OOPS!!! I'm sorry, but I don't know what that means :-(" +
                                "\n    ____________________________________________________________\n");
                    }
                }

            } else if (comm.length() > 6 && comm.substring(0, 6).equals("delete")) {
                if (comm.charAt(7) != ' ') {
                    try {
                        cl.add(comm);
                    } catch (DukeException exc) {
                        System.out.println(exc.getMessage());
                    }
                } else {
                    try {
                        cl.delete(Integer.parseInt(comm.substring(7)));
                        try {
                            SaveRetrieve.writeOnFile("../data/duke.txt", cl.genInfoForFile());
                        } catch (IOException exc) {
                            System.out.println("Something went wrong: " + exc.getMessage());
                        }
                        cl.getResponseDirect();
                    } catch (Exception exc) {
                        System.out.println("    ____________________________________________________________\n     " +
                                "\u2639" + " OOPS!!! I'm sorry, but I don't know what that means :-(" +
                                "\n    ____________________________________________________________\n");
                    }
                }

            } else {
                try {
                    cl.add(comm);
                    cl.getResponseDirect();
                } catch (DukeException exc) {
                    System.out.println(exc.getMessage());
                }
                try {
                    SaveRetrieve.writeOnFile("../data/duke.txt", cl.genInfoForFile());
                } catch (IOException exc) {
                    System.out.println("Something went wrong: " + exc.getMessage());
                }
            }
        }
    }
}
