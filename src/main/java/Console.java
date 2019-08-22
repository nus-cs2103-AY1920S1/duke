public class Console {
    public static void print(String ...strArr) {
        for (String s : strArr) {
            System.out.println("> " + s);
        }
    }
}
