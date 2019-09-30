package dose.util.gui;

public enum ColourScheme {
    MINT("#b2eee6", "#8ad6cc", "#66beb2",
            "#f99192", "#f97171", "#333333"),
    GREY("#f4f6f9", "#e5e8ec", "#cbd0d8",
            "#a9b1bc", "#646c77", "#424953");

    private final String backgroundColour;
    private final String dukeMessageBoxColour;
    private final String dukeShadowColour; // also serves as the colour of the taskMessageBox
    private final String userMessageBoxColour;
    private final String userShadowColour; // also serves as the colour of the exceptionMessageBox
    private final String textColour;

    public String getBackgroundColour() {
        return backgroundColour;
    }

    public String getDukeMessageBoxColour() {
        return dukeMessageBoxColour;
    }

    public String getDukeShadowColour() {
        return dukeShadowColour;
    }

    public String getUserMessageBoxColour() {
        return userMessageBoxColour;
    }

    public String getUserShadowColour() {
        return userShadowColour;
    }

    public String getTextColour() {
        return textColour;
    }

    ColourScheme(String backgroundColour, String dukeMessageBoxColour, String dukeShadowColour,
            String userMessageBoxColour, String userShadowColour, String textColour) {
        this.backgroundColour = backgroundColour;
        this.dukeMessageBoxColour = dukeMessageBoxColour;
        this.dukeShadowColour = dukeShadowColour;
        this.userMessageBoxColour = userMessageBoxColour;
        this.userShadowColour = userShadowColour;
        this.textColour = textColour;
    }
}
