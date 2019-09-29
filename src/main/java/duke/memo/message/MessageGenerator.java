package duke.memo.message;

import duke.memo.data.RecordList;
import duke.memo.record.Record;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class MessageGenerator {

    public MessageGenerator() {
    }

    public String generateByeMsg() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Display the added task.
     *
     * @param recordList ArrayList of task.
     * @return String contains all task details.
     */
    public String generateListRecordMsg(RecordList recordList) {
        int taskNum = recordList.size();
        String msg;
        if (taskNum == 0) {
            msg = "There is no record in your list.";
        } else if (taskNum == 1) {
            msg = String.format("There is 1 records in your list:\n1.%s", recordList.get(0).toString());
        } else {
            AtomicInteger index = new AtomicInteger();
            msg = String.format("There are %d records in your list:", recordList.size());
            msg += recordList.stream()
                    .map(t -> String.format("\n%d.%s", index.incrementAndGet(), t.toString()))
                    .collect(Collectors.joining());
        }
        return msg;
    }

    public String generateDoneTaskMsg(String taskDetails) {
        return "Nice! I've marked this task as done: \n" + taskDetails;
    }

    public String generateDeletedRecordMsg(String recordDetails, int recordNo) {
        return "Noted. I've removed this record: \n" + recordDetails
                + "\nNow you have " + recordNo + " records in the list.";
    }

    /**
     * Display the added task.
     *
     * @param addedRecord  Details of the added task.
     * @param taskListLength  Length of the task list.
     * @return String This returns the details of the added task.
     */
    public String generateAddedRecordMsg(String addedRecord, int taskListLength) {
        return String.format("Got it. I've added this record: \n%s\nNow you have %d records in the list.",
                addedRecord, taskListLength);
    }

    public String generateErrorMsg(String errMsg) {
        return errMsg;
    }

    /**
     * Display the matched task.
     *
     * @param resultRecords  Details of the matched task.
     * @return String This returns details of the match tasks.
     */
    public String generateResultRecordMsg(List<Record> resultRecords) {
        String msg = "Here are the matching records in your list:";
        msg += resultRecords.stream()
                .map(t -> "\n" + t.toString())
                .collect(Collectors.joining());
        return  msg;
    }
}