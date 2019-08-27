import java.time.LocalDateTime;

public class Parser {
    public static LocalDateTime getDateAndTimeFromString(String inputs){
        String[] time = inputs.split(" ");
        String[] dateInString = time[0].split("\\/");

        LocalDateTime dateAndTime = LocalDateTime.of(Integer.parseInt(dateInString[2]),
                Integer.parseInt(dateInString[1]),
                Integer.parseInt(dateInString[0]),
                Integer.parseInt(time[1]) / 100,
                Integer.parseInt(time[1]) % 100);

        return dateAndTime;
    }

    public static String[] breakDownIntoNameAndTime(String[] inputs) {
        String[] result = {"", ""};

        int index = 1;

        while (index < inputs.length && !(inputs[index].charAt(0) == '/')) {
            result[0] = result[0] + inputs[index] + " ";
            index++;
        }

        //Skip the /at or /by
        index++;

        while (index < inputs.length) {
            result[1] = result[1] + inputs[index] + " ";
            index++;
        }
        return result;
    }

    public static String[] breakDownString(String input, String seperator) {
        return input.split(seperator);
    }
}
