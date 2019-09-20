package seedu.duke;

import javafx.scene.image.Image;

/**
 * Class combining chat text and display picture as an object.
 */
public class ChatDisplay {
    private static Image displayPicture;
    private static String text;

    public ChatDisplay(String str, Expression e) {
        text = str;
        displayPicture = retrieveExpression(e);
    }

    public Image getDisplayPicture() {
        return displayPicture;
    }

    public String getText() {
        return text;
    }

    public enum Expression {
        NEUTRAL, CONFUSED, SAD, CONCERNED, SPARKLY, SHOCKED, STRESSED, SINGING;
    }

    public Image retrieveExpression(Expression e) {
        Image result;
        switch (e) {
            case NEUTRAL:
                result = new Image(this.getClass().getResourceAsStream("/images/isabelle_neutral.png"));
                break;
            case SAD:
                result = new Image(this.getClass().getResourceAsStream("/images/isabelle_sad.png"));
                break;
            case SHOCKED:
                result = new Image(this.getClass().getResourceAsStream("/images/isabelle_shocked.png"));
                break;
            case SPARKLY:
                result = new Image(this.getClass().getResourceAsStream("/images/isabelle_sparkly.png"));
                break;
            case CONFUSED:
                result = new Image(this.getClass().getResourceAsStream("/images/isabelle_confused.png"));
                break;
            case STRESSED:
                result = new Image(this.getClass().getResourceAsStream("/images/isabelle_stressed.png"));
                break;
            case CONCERNED:
                result = new Image(this.getClass().getResourceAsStream("/images/isabelle_concerned.png"));
                break;
            case SINGING:
                result = new Image(this.getClass().getResourceAsStream("/images/isabelle_singing.png"));
                break;
            default:
                result = new Image(this.getClass().getResourceAsStream("/images/isabelle_neutral.png"));
                break;
        }
        return result;
    }
}
