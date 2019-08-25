import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class ListFile {

        private Scanner listFile;
        private int index = 0;

        public void openFile() {
            try {
                // Delimiters are "|" and a new line
                listFile = new Scanner(new File("list.txt")).useDelimiter("[|\\n]");
            }
            catch (Exception e) {
                System.out.println("Cannot find file");
            }
        }

        public ArrayList readAndWriteList(ArrayList<Task> list) {
            while(listFile.hasNext()) {
                String action = listFile.next().trim();
                if (action.equals("T")) {
                    String status = listFile.next();
                    String description = listFile.next().trim();
                    list.add(new ToDo(description));
                    if (status.contains("1"))
                        list.get(index).isDone = true;
                    index += 1;
                }
                else {
                    String status = listFile.next();
                    String description = listFile.next().trim();
                    String time = listFile.next().trim();

                    if (action.equals("E"))
                        list.add(new Event(description, time));
                    else if (action.equals("D"))
                        list.add(new Deadline(description, time));

                    if (status.contains("1"))
                        list.get(index).isDone = true;
                    index += 1;
                }

            }
            return list;
        }

        public void closeFile() {
            listFile.close();
        }
}
