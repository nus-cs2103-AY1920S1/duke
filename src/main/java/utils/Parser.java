package utils;

import java.text.ParseException;

import exceptions.DukeException;

public class Parser {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    /**
     * Parser class takes care of strings from single line commands.
     * adds relevant tasks into the tasklist
     */

    public Parser(TaskList tasks, Ui ui, Storage storage) {
        this.tasks = tasks;
        this.ui = ui;
        this.storage = storage;
    }
    /**
     * takes user input and makes corresponding task object to be put inside tasklist.
     * error handling done through DukeException.
     * @param str single line from user input
     * @throws DukeException error handling
     */

    public String parse(String str) throws DukeException {
        String output = ""; 
        try {
            String[] splited = str.split(" ");
            String check = splited[0].toLowerCase();
            
            switch (check) {
                case "bye" :
                    output = ui.bye();
                    storage.saveFile(tasks);
                    System.exit(0);
                    break;

                case "list": 
                    output = ui.print(tasks.toString());
                    break;
                
                case "done":
                    int taskNum = Integer.parseInt(splited[1]) - 1;
                    tasks.markAsDone(taskNum);
                    output = ui.doneString(tasks.get(taskNum));
                    break;

                case "delete":
                    int taskNum2 = Integer.parseInt(splited[1]) - 1;
                    output = tasks.delete(taskNum2);
                    break;

                case "todo":
                    String description = str.replaceFirst("todo", "").trim();
                    
                    if (description.equals("")) { //error handling
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    
                    } else { //successful addition 
                        tasks.addTodo(description);
                        Task current = tasks.get(tasks.size() - 1);
                        output = ui.printMsg(current, tasks.size());

                    }
                    break;

                case "event":
                    String [] splitDate = str.replaceFirst("event", "").split("/at");
                    if (splitDate.length < 2) {
                        throw new DukeException("☹ OOPS!!! Events require both a description and a date /at");
                    } else {
                        //if it reaches here it is successful
                        output = tasks.addEvent(splitDate[0].trim(), new DateTime(splitDate[1].trim()));
                    }
                    break;

                case "deadline":
                    String [] splitDate2 = str.replaceFirst("deadline", "").split("/by");
                    if (splitDate2.length < 2) {
                        throw new DukeException("☹ OOPS!!! Deadlines require both a description and a date by");
                    } else {
                        output = tasks.addDeadline(splitDate2[0].trim(), new DateTime(splitDate2[1].trim()));
                    }
                    break;

                case "find":
                    String keyword = str.replaceFirst("find", "").trim();
                    output = tasks.find(keyword);
                    break;

                case "clone":
                    int taskNum3 = Integer.parseInt(splited[1]) - 1;
                    Task duplicate = tasks.get(taskNum3);
                    tasks.add(duplicate);
                    output = ui.printMsg(duplicate, tasks.size());
                    break;
                    
                default: //error handling
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                
            }
    
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Input cannot be empty!");
        } catch (ParseException e) {
            throw new DukeException("☹ OOPS!!! I'm sorry,Please enter the date in the format dd-MM-yyyy HH:mm");
        }             
        
        storage.saveFile(tasks);
        return output;
    }


}