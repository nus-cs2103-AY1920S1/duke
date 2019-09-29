package command;


import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.VocabularyList;

import java.util.stream.Stream;

public class TriviaCommand extends Command {
    private String command;
    private String vocabulary;
    private String content;

    /**
     * If no commands are passed in, Duke shows user guide for Trivia.
     */
    public TriviaCommand() {
        this.command = "query";
    }

    /**
     * Constructor class for TriviaCommand if input is specified.
     *
     * @param input User input for TriviaCommand
     * @throws DukeException If user input is invalid.
     */
    public TriviaCommand(String input) throws DukeException {
        String[] args = input.split(" ", 3);

        this.command = args[0];
        switch (command) {
        case "memorise":
            DukeException.checkValidity(args.length < 3,
                    "Provide a vocabulary to memorise and its content.");
            this.vocabulary = args[1];
            this.content = args[2];
            break;
        case "erase":
            DukeException.checkValidity(args.length < 2,
                    "Provide a vocabulary to erase");
            this.vocabulary = args[1];
            break;
        default:
            break;
        }
    }

    @Override
    public String getResponse(TaskList tasklist, Ui ui,
                              Storage storage, VocabularyList vocabularyList) throws DukeException {
        String response;
        switch (command) {
        case "query":
            response = ui.generateResponse("Here are the possible commands for trivia:",
                    "- memorise newVocabulary actualContent",
                    "- list",
                    "- erase",
                    "- reset");
            break;
        case "memorise":
            assert !vocabulary.isEmpty() : "Vocabulary should not be empty";
            assert !content.isEmpty() : "Content should not be empty";
            vocabularyList.memorise(vocabulary, content);
            storage.updateVocabulary(vocabularyList);
            response = ui.generateResponse("Duke has memorised " + vocabulary);
            break;
        case "list":
            response = ui.generateResponse(Stream
                    .concat(Stream.of("Here is the list of Duke's vocabulary:"), vocabularyList.getAllVocabulary())
                    .toArray(String[]::new));
            break;
        case "erase":
            assert !vocabulary.isEmpty() : "Vocabulary should not be empty";
            vocabularyList.eraseWord(vocabulary);
            storage.updateVocabulary(vocabularyList);
            response = ui.generateResponse(String.format("%s has been removed from Duke's memory",
                    vocabulary));
            break;
        case "reset":
            vocabularyList.reset();
            storage.updateVocabulary(vocabularyList);
            response = ui.generateResponse("Vocabulary bank has been reset.");
            break;
        default:
            throw new DukeException("Unrecognized Command for Trivia.");
        }
        return response;
    }

}
