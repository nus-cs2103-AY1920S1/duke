package duke;

import java.util.List;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Map;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.File;
import duke.error.DukeException;

/**
 * Stores abbreviations for commands available.
 * Acts as a Syntax Library
 */
public class Alias{
    protected static HashMap<String, Integer> types = new HashMap<>() {{
        put("LIST", 0);
        put("EXIT", 1);
        put("FIND", 2);
        put("DONE", 3);
        put("DELETE", 4);
        put("TODO", 5);
        put("DEADLINE", 6);
        put("EVENT", 7);
        put ("LISTALIAS", 8);
        put ("ADDALIAS", 9);
    }};
    private static File file;
    private static File dir;
    private static String filePath = "data/alias.txt";
    protected static TreeMap<String, String> aliases = new TreeMap<>();
    protected static TreeMap<String, String> aliases_types = new TreeMap<>();

    /**
     * Creates a instance of Storage object
     *
     * @param filepath String of the file location
     */
    public Alias() {
        String[] arr = filePath.split("/");
        dir = new File(arr[0]);
        dir.mkdirs();

        this.dir = dir;
        this.file = new File(dir, arr[1]);
    }

    public static void load() throws IOException {
        try {
            if (!file.exists()) {
                file.createNewFile();
                createNewAliases();
            }
            loadAliases();
        } catch (IOException e) {}
    }

    public static void createNewAliases() throws IOException {
        try {
            FileWriter writer = new FileWriter(file, true); //initialize file writer
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            for (Map.Entry<String, Integer> entry : types.entrySet()) {
                bufferedWriter.write(entry.getKey() + " | " + entry.getKey().toLowerCase());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadAliases() throws IOException {
        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] arr = line.split(" \\| ");
                aliases.put(arr[1], arr[0]);
                aliases_types.put(arr[0], arr[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateAliases(String type, String alias) throws IOException, DukeException{
        if (!types.containsKey(type)) {
            throw new DukeException("Invalid type for alias format: addalias <type> <alias>\n");
        } else if (aliases.containsKey(alias)) {
            throw new DukeException("Duplicate Alias! Try something else!\n");
        }
        int index = types.get(type);
        Path path = Paths.get(filePath);
        List<String> aliasList = Files.readAllLines(path, StandardCharsets.UTF_8);
        aliasList.set(index, type + " | " + alias);
        Files.write(path, aliasList, StandardCharsets.UTF_8);

        String old_alias = aliases_types.get(type);
        aliases.remove(old_alias);
        aliases.put(alias, type);
        aliases_types.put(type, alias);
    }

    public static String showAliases() {
        StringBuilder sb = new StringBuilder("Here are your aliases :) \n\n");

        for (Map.Entry<String, String> entry : aliases.entrySet()) {
            sb.append(entry.getValue() + " : " + entry.getKey() + "\n");
        }

        return sb.toString();
    }
}
