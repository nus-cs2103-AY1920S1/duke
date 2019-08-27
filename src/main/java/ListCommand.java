import java.util.ArrayList;

public class ListCommand extends Command {
	protected String command;
	
	public ListCommand(String command) {
		this.command = command;
	}
	
	public void execute(TaskList tasks, Storage storage){
		ArrayList<Task> tasksList = tasks.getTasks();
		for (int i = 0; i < tasksList.size(); i++) {
			System.out.println(tasksList.get(i));
		}
	}
	
	public boolean isExit() {
		return false;
	}
}
