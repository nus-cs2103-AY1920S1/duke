package duke;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class VocabularyList {
    private HashMap<String, String> dictionary;

    public VocabularyList(HashMap<String, String> dictionary) {
        this.dictionary = dictionary;
    }

    /**
     * Formats the raw user input before parsing into the Parser.
     *
     * @param input User input.
     * @return Formatted input according to Duke's vocabulary.
     */
    public String format(String input) {
        String formattedInput = input;

        Pattern p = Pattern.compile("\\[(.*?)\\]");   // the pattern to search for
        Matcher m = p.matcher(formattedInput);
        while (m.find()) {
            formattedInput = formattedInput.replaceAll(String.format("\\[%s\\]", m.group(1)),
                    dictionary.get(m.group(1)));
            m = p.matcher(formattedInput);
        }
        return formattedInput;
    }

    /**
     * Memorises the given vocabulary and its content mapping.
     *
     * @param vocabulary Vocabulary to memorise.
     * @param content Word that the vocabulary maps to.
     */
    public void memorise(String vocabulary, String content) {
        dictionary.put(vocabulary, content);
    }

    /**
     * Gets a stream of all vocabularies that duke recognizes.
     *
     * @return A String Stream containing all vocabulary-content pair.
     */
    public Stream<String> getAllVocabulary() {
        return dictionary
                .entrySet()
                .stream()
                .map(e -> String.format("%s: %s", e.getKey(), e.getValue()));
    }

    /**
     * Gets a formatted String array of vocabulary-content pair for the purpose of writing into file.
     *
     * @return String array containing vocabulary and content as element.
     */
    public String[] getList() {
        return dictionary
                .entrySet()
                .stream()
                .map(e -> String.format("%s %s", e.getKey(), e.getValue()))
                .toArray(String[]::new);
    }

    /**
     * Erases the given vocabulary from Duke's memory.
     *
     * @param vocabulary Given vocabulary
     * @throws DukeException If vocabulary is not found in Duke.
     */
    public void eraseWord(String vocabulary) throws DukeException {
        if (!dictionary.containsKey(vocabulary)) {
            throw new DukeException("Vocabulary does not exist in Duke");
        }
        dictionary.remove(vocabulary);
    }

    public void reset() {
        dictionary.clear();
    }

}
