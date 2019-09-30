package dose;

import dose.util.cli.CliDose;

public interface Dose {

    /**
     * Runs Dose.
     */
    void run();

    /**
     * Creates and runs a new instance of Dose.
     * Invoked when Dose is run from the CLI.
     * @param args Arguments supplied by the user.
     */
    static void main(String[] args) {
        // running Dose from the CLI should instantiate CliDose, as usual
        Dose dose = new CliDose();
        dose.run();
    }

    void initializeStorage();
}
