package duke.Command;

import duke.Flashcard.Card;
import duke.Flashcard.Flashcard;
import duke.Flashcard.FlashcardList;
import duke.Ui;

import java.util.ArrayList;

public class ListAnsCommand {
    public ListAnsCommand(){ }

    public String ListAns(Ui ui, FlashcardList list, String msg){
        StringBuilder builder = new StringBuilder();

        //get the flashcard, get card list, get all ans, pass in to ui method for format, use string builder to append
        Flashcard flashcard = list.get_Flashcard(msg);
        ArrayList<Card> cardList = flashcard.get_CardList();
        for(int i=0; i<flashcard.get_NoOfCards(); i++){
            builder.append((i+1) + ". " + cardList.get(i).get_Ans() + "\n");
        }
        return builder.toString();
    }
}
