package seedu.duke;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class TriviaCommand extends Command {
    protected static String url = "https://trivia.fyi/random-trivia-questions/";

    @Override
    public ChatDisplay execute(TaskList t, Ui u, Storage s) {
        String result = "";
        try {
            Document doc = Jsoup.connect(url).timeout(0).get();
            Elements elements = doc.select("h2.query-title");
            if (elements != null) {
                Element e = elements.get(0);
                String question = e.text();
                Elements el = doc.select("div.query-content.post-content");
                Element element = el.get(0);
                String[] text = element.text().split("View Answer");
                String answer = text[1].trim();
                Ui.setAnswer(answer);
                result = question;
            } else {
                String error = "Oh my! I think something has gone wrong. I'll see what I can do.";
                result = error;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return u.questionMessage(result);
    }
}
