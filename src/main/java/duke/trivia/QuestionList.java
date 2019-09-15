package duke.trivia;

import java.util.ArrayList;

public class QuestionList{
    public ArrayList<TriviaQuestion> questions = new ArrayList<>();
    public int current = 0;
    public boolean isAsking = false;
    public TriviaQuestion getCurrentQuestion(){
        if(questions.size() == 0){
            return new TriviaQuestion("", "");
        }
        return questions.get(current);
    }
    public void advance(){
        current++;
        if(current >= questions.size()){
            current = 0;
        }
        if (questions.size() == 0){
            current = -1;
        }
    }
    public String toString(){
        String res = "";
        for (int i = 0; i < questions.size(); i++){
            res += (i+1)+". " + questions.get(i) + "\n";
        }
        return res;
    }
}