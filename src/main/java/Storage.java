import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents the loader and the file saver.
 */
public class Storage {

    /**
     * The path of the file.
     */
    private String filePath;

    /**
     * The saved file.
     */
    private File file;

    /**
     * The list of tasks.
     */
    private List<Task> storageList;

    /**
     * Number of tasks.
     */
    private int noOfTasks;

    /**
     * Constructs the loader cum file saver.
     * @param filepath path of the file
     */
    public Storage (String filepath) {
        this.filePath = filepath;
    }

    //create new storage before saving

    /**
     * Saves the state of the manager.
     * @param list list of tasks
     * @param noOfTasks number of tasks
     */
    public void save(List<Task> list, int noOfTasks) {
        //Write file
        this.storageList = list;
        this.noOfTasks = noOfTasks;
        String str = "";
        for (int i = 0; i < noOfTasks; i++) {
            if (storageList.get(i) instanceof Todo) {
                str = str + "T | " + storageList.get(i).getStatus() + " | "
                        + storageList.get(i).getDescription() + "\n";
            } else if (storageList.get(i) instanceof Deadline) {
                str = str + "D | " + storageList.get(i).getStatus() + " | "
                        + storageList.get(i).getDescription() + " | "
                        + ((Deadline) storageList.get(i)).getDate() + "\n";
            } else {
                str = str + "E | " + storageList.get(i).getStatus() + " | "
                        + storageList.get(i).getDescription() + " | "
                        + ((Event) storageList.get(i)).getDate() + "\n";
            }
        }
        try (FileWriter fileWriter = new FileWriter("duke.txt")) {
            String fileContent = str;
            fileWriter.write(fileContent);
        } catch (IOException e) {
            System.out.println("File not found.");
        }

    }

    /**
     * Loads the manager from a saved file.
     * @return list of tasks
     * @throws FileNotFoundException error exceptions
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        List<Task> list = new ArrayList<>();
        int pos = 0;
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
                    String day;
                    String month;
                    String year;
                    String time;

                    fileSc.next();
                    status = fileSc.nextInt();
                    fileSc.next();
                    String[] split1 = new String[2];
                    split1 = fileSc.nextLine().trim().split(" \\| ");
                    String[] dateTime = new String[2];
                    dateTime = split1[1].trim().split(", ");
                    String[] date = new String[4];
                    date = dateTime[0].trim().split(" ");
                    // day
                    if (date[0].length() == 3) {
                        day = date[0].substring(0, 1);
                    } else {
                        day = date[0].substring(0, 2);
                    }
                    // month
                    switch (date[2]) {
                        case "January":
                            month = "1";
                            break;
                        case "February":
                            month = "2";
                            break;
                        case "March":
                            month = "3";
                            break;
                        case "April":
                            month = "4";
                            break;
                        case "May":
                            month = "5";
                            break;
                        case "June":
                            month = "6";
                            break;
                        case "July":
                            month = "7";
                            break;
                        case "August":
                            month = "8";
                            break;
                        case "September":
                            month = "9";
                            break;
                        case "October":
                            month = "10";
                            break;
                        case "November":
                            month = "11";
                            break;
                        default:
                            month = "12";
                            break;
                    }
                    // year
                    year = date[3];
                    // time
                    if (dateTime[1].length() == 7) {
                        if (dateTime[1].contains("pm") && (Integer.parseInt(dateTime[1].substring(0, 2)) < 12)) {
                            int timing = Integer.parseInt(dateTime[1].substring(0, 2) + dateTime[1].substring(3, 5)) + 1200;
                            time = timing + "";
                        } else {
                            int timing = Integer.parseInt(dateTime[1].substring(0, 2) + dateTime[1].substring(3, 5));
                            time = timing + "";
                        }
                    } else if (dateTime[1].length() == 6) {
                        if (dateTime[1].contains("pm")) {
                            int timing = Integer.parseInt(dateTime[1].substring(0, 1) + dateTime[1].substring(2, 4)) + 1200;
                            time = timing + "";
                        } else {
                            int timing = Integer.parseInt(dateTime[1].substring(0, 1) + dateTime[1].substring(2, 4));
                            time = timing + "";
                        }
                    } else if (dateTime[1].length() == 4) {
                        if (dateTime[1].contains("pm")) {
                            int timing = Integer.parseInt(dateTime[1].substring(0, 2) + "00") + 1200;
                            time = timing + "";
                        } else {
                            int timing = Integer.parseInt(dateTime[1].substring(0, 2) + "00");
                            time = timing + "";
                        }
                    } else {
                        if (dateTime[1].contains("pm")) {
                            int timing = Integer.parseInt("0" + dateTime[1].substring(0, 1) + "00") + 1200;
                            time = timing + "";
                        } else {
                            int timing = Integer.parseInt("0" + dateTime[1].substring(0, 1) + "00");
                            time = timing + "";
                        }
                    }
                    // forming date string
                    String dateInput = day + "/" + month + "/" + year + " " + time;
                    Task curr1 = new Deadline(split1[0], dateInput);
                    list.add(curr1);
                    pos++;
                    if (status == 1) {
                        int pos1 = pos - 1;
                        list.get(pos1).markAsDone();
                    }
                    break;
                case "E":
                    String day1;
                    String month1;
                    String year1;
                    String time1;

                    fileSc.next();
                    status = fileSc.nextInt();
                    fileSc.next();
                    String[] split2 = new String[2];
                    split2 = fileSc.nextLine().trim().split(" \\| ");
                    String[] dateTime2 = new String[2];
                    dateTime2 = split2[1].trim().split(", ");
                    String[] date2 = new String[4];
                    date2 = dateTime2[0].trim().split(" ");
                    // day
                    if (date2[0].length() == 3) {
                        day = date2[0].substring(0, 1);
                    } else {
                        day = date2[0].substring(0, 2);
                    }
                    // month
                    switch (date2[2]) {
                        case "January":
                            month = "1";
                            break;
                        case "February":
                            month = "2";
                            break;
                        case "March":
                            month = "3";
                            break;
                        case "April":
                            month = "4";
                            break;
                        case "May":
                            month = "5";
                            break;
                        case "June":
                            month = "6";
                            break;
                        case "July":
                            month = "7";
                            break;
                        case "August":
                            month = "8";
                            break;
                        case "September":
                            month = "9";
                            break;
                        case "October":
                            month = "10";
                            break;
                        case "November":
                            month = "11";
                            break;
                        default:
                            month = "12";
                            break;
                    }
                    // year
                    year = date2[3];
                    // time
                    if (dateTime2[1].length() == 7) {
                        if (dateTime2[1].contains("pm") && (Integer.parseInt(dateTime2[1].substring(0, 2)) < 12)) {
                            int timing = Integer.parseInt(dateTime2[1].substring(0, 2) + dateTime2[1].substring(3, 5)) + 1200;
                            time = timing + "";
                        } else {
                            int timing = Integer.parseInt(dateTime2[1].substring(0, 2) + dateTime2[1].substring(3, 5));
                            time = timing + "";
                        }
                    } else if (dateTime2[1].length() == 6) {
                        if (dateTime2[1].contains("pm")) {
                            int timing = Integer.parseInt(dateTime2[1].substring(0, 1) + dateTime2[1].substring(2, 4)) + 1200;
                            time = timing + "";
                        } else {
                            int timing = Integer.parseInt(dateTime2[1].substring(0, 1) + dateTime2[1].substring(2, 4));
                            time = timing + "";
                        }
                    } else if (dateTime2[1].length() == 4) {
                        if (dateTime2[1].contains("pm")) {
                            int timing = Integer.parseInt(dateTime2[1].substring(0, 2) + "00") + 1200;
                            time = timing + "";
                        } else {
                            int timing = Integer.parseInt(dateTime2[1].substring(0, 2) + "00");
                            time = timing + "";
                        }
                    } else {
                        if (dateTime2[1].contains("pm")) {
                            int timing = Integer.parseInt("0" + dateTime2[1].substring(0, 1) + "00") + 1200;
                            time = timing + "";
                        } else {
                            int timing = Integer.parseInt("0" + dateTime2[1].substring(0, 1) + "00");
                            time = timing + "";
                        }
                    }
                    // forming date string
                    String dateInput2 = day + "/" + month + "/" + year + " " + time;
                    Task curr2 = new Deadline(split2[0], dateInput2);
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
        storageList = list;
        noOfTasks = pos;
        return (ArrayList<Task>) list;
    }

}
