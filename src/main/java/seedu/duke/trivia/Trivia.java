package seedu.duke.trivia;

import java.util.ArrayList;

public class Trivia {
    private ArrayList<QuestionAnswer> questionBank;

    public Trivia() {
        this.questionBank = new ArrayList<>();
    }

    public String viewQuestion(int qnIndex) {
        return questionBank.get(qnIndex).getQuestion();
    }

    public ArrayList<QuestionAnswer> getQuestionBank() {
        return questionBank;
    }

    public void addQuestion(String question) {
        QuestionAnswer toAdd = new QuestionAnswer(question);
        questionBank.add(toAdd);
    }

    public void addAnswer(String answer, int questionIndex) {
        questionBank.get(questionIndex).addAnswer(answer);
    }

}
