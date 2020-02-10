package duke.Command;

import duke.Flashcard.Flashcard;
import duke.Flashcard.FlashcardList;
import duke.Ui;

import java.util.ArrayList;

public class CreateFlashcardCommand {
    public CreateFlashcardCommand(){
    }
    public String CreateFlashcard(Ui ui, FlashcardList flashcardList, String name){
        Flashcard t = new Flashcard(name);
        flashcardList.addFlashcard(t);

        String output = ui.print_CreateFlashCard(name);
        return output;
    }
}
