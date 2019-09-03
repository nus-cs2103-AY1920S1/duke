import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private String filePath ;
    private File file;
    public Storage(String filePath) {
        this.filePath = filePath;
        file = new File(filePath);
    }

    public File getFile() {
        return file;
    }

    //save
    public void save (TaskList taskList) {
        String string = "";
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(file,false);
            for (int i = 0; i < taskList.getTasks().size(); i++) {
                //construct string
                string = "";
                //task type
                string = string.concat(String.valueOf(taskList.getTasks().get(i).getType()));
                string = string.concat("|");
                //task status
                string = string.concat(String.valueOf(taskList.getTasks().get(i).getStatus()));
                string = string.concat("|");
                //task name
                string = string.concat(taskList.getTasks().get(i).getTaskName());
                //(optional task start/end time)
                if (taskList.getTasks().get(i) instanceof Deadline ) {
                    string = string.concat("|");
                    //upside type casting
                    Deadline dtask = (Deadline) taskList.getTasks().get(i);
                    string = string.concat(dtask.getBy());
                } else if (taskList.getTasks().get(i) instanceof Event) {
                    string = string.concat("|");
                    Event etask = (Event) taskList.getTasks().get(i);
                    string = string.concat(etask.getStart());
                }
                // end of constructing string
                fos.write(string.getBytes());
                fos.write("\r\n".getBytes());
            }
        } catch (IOException ioe){
            System.out.println("    ____________________________________________________________");
            System.out.println("     ☹ OOPS!!! The target-output file does not exist.");
            System.out.println("    ____________________________________________________________");
        }
    }

    //load
    public void load (Duke duke) throws NullPointerException {
        int index = 0;
        String string;
        String [] buffer;
        String command = "";
        try {
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                //construct a command
                command = "";
                string = scanner.nextLine();
                //split
                //D|✗|return book |Sunday
                buffer = string.split("\\|");
                switch (buffer[0].charAt(0)) {
                    case 'T' :
                        command = command.concat("todo ");
                        command = command.concat(buffer[2]);
                        break;
                    case 'D':
                        command = command.concat("deadline "); //task type
                        command = command.concat(buffer[2]); //task name
                        command = command.concat(" /by ");
                        command = command.concat(buffer[3]); //task date
                        break;
                    case 'E':
                        command = command.concat("event "); //task type
                        command = command.concat(buffer[2]); //task name
                        command = command.concat(" /by ");
                        command = command.concat(buffer[3]); //task date
                        break;
                }
                duke.getParse().getCommand(duke, command, false);
                //if necessary, mark the task as done
                if (buffer[1].charAt(0) == '✓') {
                    duke.getParse().done(duke, index, false);
                }
            } //end of while
        } catch (IOException ioe){
            System.out.println("no such file");
        }
    }

    //list
    public void list (TaskList taskList){
        if(taskList.getTasks().size() == 0){
            //System.out.println("but size is zero");
            throw new RuntimeException();
        }
        System.out.println("    ____________________________________________________________");
        for (int i = 0; i < taskList.getTasks().size(); i++) {
            System.out.print("     "+(i+1)+"."+"["+taskList.getTasks().get(i).getType()+"]"+"["+ taskList.getTasks().get(i).getStatus()+"] "+taskList.getTasks().get(i).getTaskName()+" ");
            switch (taskList.getTasks().get(i).getType()){
                case 'T':
                    System.out.println();
                    break;
                case 'D':
                    //Forced type casting
                    Deadline dtask = (Deadline) taskList.getTasks().get(i);
                    System.out.println("(by: "+dtask.getBy()+")");
                    break;
                case 'E':
                    Event etask = (Event) taskList.getTasks().get(i);
                    System.out.println("(by: "+etask.getStart()+")");
                    break;
            }

        }
        System.out.println("    ____________________________________________________________");
    }
}
