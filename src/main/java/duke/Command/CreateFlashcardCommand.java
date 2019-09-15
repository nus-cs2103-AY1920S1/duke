package duke.Command;

import duke.Flashcard.Flashcard;
import duke.Flashcard.FlashcardList;
import duke.Ui;

public class CreateFlashcardCommand {
    public CreateFlashcardCommand(){
    }
    public String CreateFlashcard(Ui ui, FlashcardList flashcardList, String name){
        Flashcard t = new Flashcard(name);
        flashcardList.add(t);
        String output = ui.print_CreateFlashCard(name);
        return output;
    }
}
