package duke.util.gui;

public enum ColourScheme {
    MINT("#b2eee6", "#8ad6cc", "#66beb2",
            "#f99192", "#f97171", "#333333"),
    GREY("#f4f6f9", "#e5e8ec", "#cbd0d8",
            "#a9b1bc", "#646c77", "#424953");

    private final String backgroundColour;
    private final String dukeMediumColour;
    private final String dukeDarkColour;
    private final String userLightColour;
    private final String userDarkColour;
    private final String textColour;

    public String getBackgroundColour() {
        return backgroundColour;
    }

    public String getDukeMediumColour() {
        return dukeMediumColour;
    }

    public String getDukeDarkColour() {
        return dukeDarkColour;
    }

    public String getUserLightColour() {
        return userLightColour;
    }

    public String getUserDarkColour() {
        return userDarkColour;
    }

    public String getTextColour() {
        return textColour;
    }

    ColourScheme(String backgroundColour, String dukeMediumColour, String dukeDarkColour,
            String userLightColour, String userDarkColour, String textColour) {
        this.backgroundColour = backgroundColour;
        this.dukeMediumColour = dukeMediumColour;
        this.dukeDarkColour = dukeDarkColour;
        this.userLightColour = userLightColour;
        this.userDarkColour = userDarkColour;
        this.textColour = textColour;
    }
}
