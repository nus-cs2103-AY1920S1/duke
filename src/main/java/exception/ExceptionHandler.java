package exception;

import misc.Ui;

public class ExceptionHandler {
    public void showParseDateTimeError() {
        String errorMessage = Ui.spaces(5) + "Unable to parse Date & Time of task!\n"
                + Ui.spaces(5) + "Please follow the following format for Date & Time:\n\n"
                + Ui.spaces(7) + "- Date: 'dd/mm/yy' e.g. 10/02/19 (10 Feb 2019)\n"
                + Ui.spaces(7) + "- Time: 'hhmm' (24-hr format) e.g. 0730 (07:30AM)";

        System.out.println(errorMessage);
    }

    public void showDukeCommandEvaluationError(IncorrectDukeCommand e) {
        String errorMessage = String.format("%sLooks like the above Duke Command has failed. That's because...\n\n%s",
                Ui.spaces(5), Ui.spaces(5) + e.getMessage());

        System.out.println(errorMessage);
    }

    public void showUnknownDukeCommandError() {
        String errorMessage = Ui.spaces(5) + "I'm sorry, but I don't know what that means... \u2639";

        System.out.println(errorMessage);
    }

    public void showVoidDukeCommandError() {
        String errorMessage = Ui.spaces(5) + "I can't do anything if you don't tell me what to do... \ud83d\ude2d";

        System.out.println(errorMessage);
    }

    public void showDukeIoError() {
        String errorMessage = Ui.spaces(5) + "File could not be read/saved.";

        System.out.println(errorMessage);
    }

    public void showDukeError(DukeException e) {
        System.out.println(e.getMessage());
    }
}
