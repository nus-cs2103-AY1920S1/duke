


public class Parser {


    public Parser() {
    }


    public Command parseCommand(String input) throws IllegalArgumentException, EmptyDescException {
        switch (input) {

            case "bye":
                return new ByeCommand();


            case "list":
                return new ListCommand();


            default:
               return determineInputType(input);

        }
    }

        public Command determineInputType (String input) throws IllegalArgumentException, EmptyDescException {
            if (input.contains("done")) {


                int taskNum = -1;
                try{
                   taskNum = Integer.parseInt(input.substring(5));
                    return new DoneCommand(taskNum);

                } catch(ArrayIndexOutOfBoundsException e){
                    throw new EmptyDescException("done");
                }




            } else if (input.contains("todo")) {

               try {
                   return new TodoCommand(false, input.substring(5));
               } catch(ArrayIndexOutOfBoundsException e){
                   throw new EmptyDescException("todo");
               }


            } else if (input.contains("deadline")) {

                if (input.length() < 9) {
                    throw new EmptyDescException("deadline");
                } else {

                    String[] parts = input.split("/by");

                    return new DeadlineCommand(false, parts[0].substring(9), parts[1]);
                }

            } else if (input.contains("event")) {
                if (input.length() < 9) {
                    throw new EmptyDescException("event");
                } else {

                    String[] parts = input.split("/at");

                    return new EventCommand(false, parts[0].substring(5), parts[1]);
                }
            } else if (input.contains("delete")) {

                int taskNum;
                  try {
                      taskNum = Integer.parseInt(input.substring(7));
                      return new DeleteCommand(taskNum);

                  } catch(ArrayIndexOutOfBoundsException e){
                    throw new EmptyDescException("delete");
                }


            } else {
                throw new IllegalArgumentException();
            }
        }


    }







    /*private boolean exited;
    private TaskList tasklist

    public Parser(tasklist){
        this.exited = false;
        this.tasklist = tasklist
    }



    public void giveRespond(String input) throws IllegalArgumentException, EmptyDescException {
        switch (input) {

            case "bye":

                exited = true;
                System.out.println("Bye. Hope to see you again soon!");
                break;

            case "list":

                System.out.println("Here are the tasks in your list:");
                int index = 1;
                for (Task s : toDoList) {
                    System.out.print(index + ". " + s);
                    index++;
                }
                break;

            default:
                determineInputType(input);
        }
    }


    public void determineInputType(String input) throws IllegalArgumentException, EmptyDescException {
        if (input.contains("done")) {


            tasklist.markTaskDone(input);


        } else if (input.contains("todo")) {

            tasklist.addTodoTask(input);


        } else if (input.contains("deadline")) {

            tasklist.addDeadlineTask(input);

        } else if (input.contains("event")) {

            tasklist.addEventTask(input);

        } else if (input.contains("delete")) {
            tasklist.deleteTask(input);
        } else {
            throw new IllegalArgumentException();
        }
    } */


