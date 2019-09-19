package duke.Command;

import duke.Flashcard.Card;
import duke.Flashcard.Flashcard;
import duke.Flashcard.FlashcardList;
import duke.Ui;

import java.util.ArrayList;

public class CreateCardCommand {
    public CreateCardCommand(){
    }

    public String CreateCard(Ui ui, FlashcardList flashcardList, String msg){

        String[] arr = msg.split("/", 3);
        Card t = new Card(arr[1], arr[2]);
        System.out.println(arr[0]);
        Flashcard c = flashcardList.get_Flashcard(arr[0]);
        //ArrayList<Card> card_list = c.get_CardList();
        //card_list.add(t);
        c.addCard(t);
        String output = ui.print_CreateCard(arr[0]);
        return output;
    }
}
