public class Parser {
    TaskList schedule;

    public Parser(TaskList schedule){
        this.schedule = schedule;
    }
    public void parse(String instruction){
        String[] word_Arr = instruction.split(" ", 2);
        try{
            Commands command = Commands.getByName(word_Arr[0]);
            switch(command){
            case LIST:
                list();
                break;
            case BYE:
                schedule.stop();
                break;
            default:
                if (word_Arr.length < 2) {
                    throw new DukeException((new Border())
                            + "\n     ☹ OOPS!!! Give instructions in the format: (instruction type) (details)\n"
                            + (new Border()));
                }
                edit(command, word_Arr[1]);
            }
        } catch(DukeException e){
            System.out.println(e.getMessage());
        }
    }

    private void list(){
        System.out.println(schedule);
    }

    private void edit(Commands command, String specifics) throws DukeException{
        switch (command) {
        case DONE:
            try {
                if (Integer.parseInt(specifics) == 0){
                    throw new IndexOutOfBoundsException();
                }
                schedule.complete(Integer.parseInt(specifics) - 1);
                System.out.println(new Border());
                System.out.println("     Nice! I've marked this task as done:");
                System.out.println("       " + schedule.getTask(Integer.parseInt(specifics) - 1).toString());
                System.out.println(new Border());
            } catch (NullPointerException | IndexOutOfBoundsException e) {
                throw new DukeException((new Border()) + "\n     ☹ OOPS!!! Index out of bounds.\n" + (new Border()));
            } catch (NumberFormatException e) {
                throw new DukeException((new Border()) + "\n     ☹ OOPS!!! Please enter a single integer index of task to delete.\n" + (new Border()));
            } finally {
                break;
            }
        case DELETE:
            try {
                    Task removed_Task = schedule.remove(Integer.parseInt(specifics) - 1);
                    System.out.println(new Border());
                    System.out.println("     Noted. I've removed this task:");
                    System.out.println("       " + removed_Task.toString());
                    System.out.println("      Now you have " + schedule.task_Num + " tasks in the list.");
                    System.out.println(new Border());
                } catch (NullPointerException | IndexOutOfBoundsException e) {
                    throw new DukeException((new Border()) + "\n     ☹ OOPS!!! Index out of bounds.\n" + (new Border()));
                } catch (NumberFormatException e) {
                    throw new DukeException((new Border()) +
                            "\n     ☹ OOPS!!! Please enter a single integer for index of task to delete.\n"
                            + (new Border()));
                } finally {
                    break;
            }
        default:
            Task new_task = track(command, specifics);
            schedule.addTask(new_task);
            System.out.println(new Border());
            System.out.println("     Got it. I've added this task:");
            System.out.println("       " + new_task.toString());
            System.out.println("     Now you have " + schedule.task_Num + " tasks in the list.");
            System.out.println(new Border() + "\n");
            break;
        }
    }

    private Task track(Commands command, String specifics) throws DukeException{
        switch(command){
        case TODO:
            return new Todo(specifics);
        case DEADLINE:
            return new Deadline(specifics);
        default:
            return new Event(specifics);
        }
    }
}
