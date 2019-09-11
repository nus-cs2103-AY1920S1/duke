public class TriviaQuestion{
    public String question, answer;
    public TriviaQuestion(String question, String answer){
        this.question = question;
        this.answer = answer;
    }
    public boolean isCorrect(String otherAnswer){
        return answer.equals(otherAnswer);
    }
    public String toString(){
        return "\tQ: " + question + "\n\tA: "+answer;
    }
    public String toFile(){
        return question+","+answer;
    }
}