package duke.Flashcard;

import java.util.ArrayList;

public class FlashcardList {
    ArrayList<Flashcard> flashcardList;
    int no_of_flashcards;

    public FlashcardList(){
        ArrayList<Flashcard> t = new ArrayList<>();
        this.flashcardList = t ;
        this.no_of_flashcards = t.size();
    }


    public void add(Flashcard t){
        flashcardList.add(t);
        no_of_flashcards++;
    }
    public void remove(int index){
        flashcardList.remove(index - 1);
        no_of_flashcards--;
    }

    public int get_NoOfFlashcards(){
        return no_of_flashcards;
    }

    public Flashcard get_Flashcard(String name){
        Flashcard flashcard = new Flashcard();

        for(int i=0; i<this.flashcardList.size(); i++){
            if(this.flashcardList.get(i).get_Name().equals(name)){       //if provided flashcard name matches
                flashcard = this.flashcardList.get(i);
            }
        }
        return flashcard;
    }
}
