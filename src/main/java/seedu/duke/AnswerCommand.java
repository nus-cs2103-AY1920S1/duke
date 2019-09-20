package seedu.duke;

public class AnswerCommand extends Command {
    private static String answer;

    public AnswerCommand(String ans) {
        answer = ans;
    }

    @Override
    public ChatDisplay execute(TaskList t, Ui u, Storage s) {
        String correctAnswer = u.getAnswer();
        if ((answer.toLowerCase().equals(correctAnswer.toLowerCase()) || answer.toLowerCase().contains(correctAnswer.toLowerCase())
            || correctAnswer.toLowerCase().contains(answer.toLowerCase())) && !answer.equals("")) {
            return u.correctAnswer();
        } else {
            return u.wrongAnswer();
        }
    }
}
