package ui;

/**
 * Interface for UI drivers.
 */
public interface UiActivity {
    public void onInputReceived(String input);

    public void stopActivity();
}
