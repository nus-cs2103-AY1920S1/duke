package duke.command;

import duke.dukeinterface.Tasklist;

import duke.task.Task;

import java.lang.StringBuilder;

import java.util.StringJoiner;

public class searchCommand extends command{
    @SuppressWarnings("StringConcatenationInsideStringBufferAppend")
    public String searchKeyword(String[] commandArr, Tasklist taskList) {
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
            if (currTask.getDescription().contains(phrase)) {
                sb.append("     "  + currTask.toString()
                        + " {List index: " + (i + 1) + "}\n");
            }
        }
        sb.append(printLine());
        sb.append("\n");
        return sb.toString();
    }

    private String printLine() {
        return "    ____________________________________________________________\n";
    }
}
