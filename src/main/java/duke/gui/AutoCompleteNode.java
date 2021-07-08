package duke.gui;

public class AutoCompleteNode {
    private String actualValue;
    private String displayValue;

    public AutoCompleteNode(String actualValue, String displayValue) {
        this.actualValue = actualValue;
        this.displayValue = displayValue;
    }

    public String getActualValue() {
        return actualValue;
    }

    public void setActualValue(String actualValue) {
        this.actualValue = actualValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }

    public void setDisplayValue(String displayValue) {
        this.displayValue = displayValue;
    }
}
