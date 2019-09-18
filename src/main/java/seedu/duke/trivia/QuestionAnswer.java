package seedu.duke.trivia;

import java.util.ArrayList;

public class QuestionAnswer {
    private String question;
    private ArrayList<String> possibleAnswers;

    public QuestionAnswer(String question) {
        this.question = question;
        possibleAnswers = new ArrayList<String>();
    }

    public void addAnswer(String answer) {
        possibleAnswers.add(answer);
    }

    public String deleteAnswer(int index) {
        return possibleAnswers.remove(index);
    }

    public String getQuestion() {
        return question;
    }

    public boolean checkAnswer(String answer) {
        for (String ans: possibleAnswers) {
            if (answer.equals(ans)) {
                return true;
            }
        }
        return false;
    }

    public String getAnswers() {
        String temp = "";
        for (int i = 0; i < possibleAnswers.size(); i++) {
            temp = temp + (i + 1) + ". " + possibleAnswers.get(i) + "\n";
        }
        return temp;
    }

    public String getAnswers(int... ansIndexes) {
        String temp = "";
        for (int i: ansIndexes) {
            temp = temp + (i + 1) + ". " + possibleAnswers.get(i) + "\n";
        }
        return temp;
    }

}
