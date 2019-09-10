package duke.command;

import duke.dukeinterface.Tasklist;

import duke.task.Task;

import java.lang.StringBuilder;

import java.util.StringJoiner;

/**
 * Search the task list based on the keyword given by the user.
 */
public class SearchCommand extends Command {
    /**
     * Search the task list based on the keyword given by the user.
     *
     * @param commandArr input given by the user.
     * @param taskList task list which contains all current tasks.
     * @return list of tasks which has the indicated keywords.
     */
    @SuppressWarnings("StringConcatenationInsideStringBufferAppend")
    public String searchKeyword(Tasklist taskList, String... commandArr) {
        StringBuilder sb = new StringBuilder();
        StringJoiner sj = new StringJoiner(" ");
        for (int i = 1; i < commandArr.length; i++) {
            sj.add(commandArr[i]);
        }

        String phrase = sj.toString();

        sb.append(printLine());
        sb.append("     Here are the matching tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            Task currTask = taskList.get(i);
            String[] taskArr = currTask.toString().split(" ");
            for (String s : taskArr) {
                if (s.contains(phrase)) {
                    sb.append("     " + currTask.toString()
                            + " {List index: " + (i + 1) + "}\n");
                }
            }
        }
        sb.append(printLine());
        sb.append("\n");
        return sb.toString();
    }

    /**
     * Gives a string as a border.
     *
     * @return a string that forms the border for duke.
     */
    private String printLine() {
        return "    ____________________________________________________________\n";
    }
}
