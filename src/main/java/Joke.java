import java.util.concurrent.ThreadLocalRandom;

public class Joke {
    static String[] jokes = {"There are 2 types of people in this world...\n"
            + "Those who understand binary, those who don't, and those  who weren't expecting a base 3 joke."};

    public static String getJoke() {
        int randomIndex = ThreadLocalRandom.current().nextInt(0, jokes.length);
        return jokes[randomIndex];
    }
}
