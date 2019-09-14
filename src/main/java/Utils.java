import java.util.Arrays;

public class Utils {
    /**
     * Takes in an array of strings and returns a copy of that array with each
     * element string trimmed.
     * 
     * @param array
     * @return
     */
    public static String[] trimAll(String[] array) {
        String[] arrayCopy = Arrays.copyOf(array, array.length);
        for (int i = 0; i < arrayCopy.length; i++) {
            arrayCopy[i] = arrayCopy[i].trim();
        }
        return arrayCopy;
    }
}
