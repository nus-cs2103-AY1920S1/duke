import java.time.LocalDateTime;

public class Parser {
    static String parseInstruction(String input) {
        return input.split(" ", 2)[0];
    }
    
    static int parseIndex(String input) {
        return Integer.parseInt(input.split(" ", 2)[1]);
    }
    
    static String parseTaskToFind(String input) {
        return input.split(" ", 2)[1];
    }
    
    static String parseDescription(String input, boolean isToDo) {
        return isToDo
                ? input.split(" ", 2)[1]
                : input.split("deadline|event", 2)[1];
    }
    
    static String parseContent(String input) {
        return input.split("/by|/at", 2)[0].strip();
    }
    
    static LocalDateTime parseTime(String input) {
        String taskTimeBeforeParse = input.split("/by|/at", 2)[1].strip();
        String[] taskTimeParsed = taskTimeBeforeParse.split("[ /]");
        return LocalDateTime.of(Integer.parseInt(taskTimeParsed[2]), Integer.parseInt(taskTimeParsed[1]),
                Integer.parseInt(taskTimeParsed[0]), Integer.parseInt(taskTimeParsed[3].substring(0, 2)),
                Integer.parseInt(taskTimeParsed[3].substring(2, 4)));
    }
    
    static String[] parseStoredLine(String input) {
        return input.split(" \\| ");
    }
    
    static String parseStoredInstruction(String[] input) {
        return input[0];
    }
    
    static void parseTaskForMarking(String[] line, Task task) {
        if (line[1].equals("+")) {
            task.markAsDone();
        }
    }
    
    static LocalDateTime parseStoredTime(String[] lineElements) {
        String[] taskTimeParsed = lineElements[3].split("[ /]");
        return LocalDateTime.of(Integer.parseInt(taskTimeParsed[2]),
                Integer.parseInt(taskTimeParsed[1]), Integer.parseInt(taskTimeParsed[0]),
                Integer.parseInt(taskTimeParsed[3].substring(0, 2)),
                Integer.parseInt(taskTimeParsed[3].substring(2, 4)));
    }
}
