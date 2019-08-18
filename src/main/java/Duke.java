public class Duke {
    public static void main(String[] args) {
        /* TODO: init friday and model */

        TaskModelInterface model = new BasicTaskModel();
        ControllerInterface friday = new FridayController(model);

        String logo2 = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String logo = "        \u2606                     \u273f\n"
            + "                                    \u2606 \u273f\n"
            + "         \u273f                 \u273f \u2606\n"
            + "  \u2606                \u2606\n"
            + "          \u2727   \u2606      \u273f\n"
            + "                \u2727     \u2606\n"
            + "         #####                  #####\n"
            + "        #     #                #     #\n"
            + "       #       #              #       #\n"
            + "       #       #  \\\\  /\\  //  #       #\n"
            + "        #     #    \\\\/  \\//    #     #\n"
            + "         #####      \\_/\\_/      #####\n"
            + "  \u2727\n"
            + "                    \u2606            \u2606\n"
            + " \u273f         \u2606\n"
            + "        \u2606       \u2606       \u2606\n"
            + "\n"
            + "              \u2606       \u273f\n";

        System.out.println("Hewwo fwom\n" + logo);

        friday.start();
    }
}

/*
 *
        String logo = "        \u2606                     \u273f\n"
            + "                                    \u2606 \u273f\n"
            + "         \u273f                 \u273f \u2606\n"
            + "  \u2606                \u2606\n"
            + "          \u2727   \u2606      \u273f\n"
            + "                \u2727     \u2606\n"
            + "${c1}         #####                  #####\n"
            + "        #     #                #     #\n"
            + "       #       #              #       #\n"
            + "       #       #${c2}  \\\\  /\\  // ${c1} #       #\n"
            + "        #     #${c2}    \\\\/  \\//    ${c1}#     #\n"
            + "         #####${c2}      \\_/\\_/     ${c1} #####\n"
            + "${c2}  \u2727\n"
            + "                    \u2606            \u2606\n"
            + " \u273f         \u2606\n"
            + "        \u2606       \u2606       \u2606\n"
            + "\n"
            + "              \u2606       \u273f\n";

*/
