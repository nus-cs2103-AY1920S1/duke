package duke.Flashcard;

import java.util.ArrayList;

public class Flashcard {
    ArrayList<Card> cardList;
    int no_of_cards;
    String name;

    public Flashcard(String Name){
        ArrayList<Card> t = new ArrayList<>();
        this.cardList = t;
        this.no_of_cards = t.size();
        this.name = Name;
    }

    public Flashcard() {
    }

    public void add(Card t){
        cardList.add(t);
        no_of_cards++;
    }
    public void remove(int index){
        cardList.remove(index - 1);
        no_of_cards--;
    }
    public String get_Name(){return name;}
    public int get_NoOfCards(){
        return no_of_cards;
    }
    public ArrayList<Card> get_CardList(){ return cardList; }
}
