import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Storage {

        private Scanner file;
        private String filePath;
        private int index = 0;
        private Ui ui;

        public Storage(String filePath) throws DukeException {
            this.filePath = filePath;
            try {
                // Delimiters are "|" and a new line
                file = new Scanner(new File(filePath)).useDelimiter("[|\\n]");
            }
            catch (Exception e) {
//                File file = new File(filePath); // create new file if file does not exist
//                throw new DukeException("");

//                ui.loadError();

            }
        }

        public String getFilePath() {
            return this.filePath;
        }

        public ArrayList loadFile() throws DukeException {
            ArrayList<Task> list = new ArrayList<>();
            try {
                if (!file.hasNext()) // Check if file is empty
                    throw new DukeException("empty file");
            } catch (Exception e) {
                throw new DukeException("empty file");
//                ui.loadError();
//                System.out.println("here");
            }

            while(file.hasNext()) {
                String action = file.next().trim();
                if (action.equals("T")) {
                    String status = file.next();
                    String description = file.next().trim();
                    list.add(new ToDo(description));
                    if (status.contains("1"))
                        list.get(index).isDone = true;
                    index += 1;
                }
                else {
                    String status = file.next();
                    String description = file.next().trim();
                    String time = file.next().trim();

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
            file.close();
        }

        public void saveFile(TaskList tasks, String saveFile) throws DukeException {
            try {
                FileWriter fw = new FileWriter(saveFile);
                BufferedWriter output = new BufferedWriter(fw);
                int size = tasks.getSize();
                for (int i = 0; i < size; i++) {
                    output.write(tasks.get(i).toSave());
                    output.newLine();
                }
                output.close();
            } catch (IOException e) {
                throw new DukeException("");
            }
        }
}
