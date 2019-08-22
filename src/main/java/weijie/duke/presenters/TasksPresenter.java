package weijie.duke.presenters;

import weijie.duke.models.Task;
import weijie.duke.repos.IRepository;
import weijie.duke.views.ConsoleView;

import java.util.List;

public class TasksPresenter implements ConsoleInputListener {

    private ConsoleView view;
    private IRepository<Task> repo;

    public TasksPresenter(ConsoleView view, IRepository<Task> repo) {
        view.registerListener(this);
        this.view = view;
        this.repo = repo;
    }

    @Override
    public void onInputReceived(String input) {
        if (input.equals("bye")) {
            view.exit();

        } else if (input.equals("list")) {
            List<Task> tasks = repo.getAll();
            StringBuilder output = new StringBuilder();

            for (int i = 0; i < tasks.size(); i++) {
                output.append(i + 1).append(". ").append(tasks.get(i).getDescription()).append("\n");
            }

            view.print(output.toString().trim());

        } else {
            Task task = new Task(input);
            repo.create(task);
            view.print("added: " + task.getDescription());
        }
    }

    public void start() {
        view.startDisplay();
    }
}

