package duke.Flashcard;

public class Card {
    String qn;
    String ans;

    public Card(String question, String answer) {
        this.qn = question;
        this.ans = answer;
    }

    public String get_Qn(){
        return qn;
    }

    public String get_Ans(){
        return ans;
    }

}
