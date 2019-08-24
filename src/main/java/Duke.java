public class Duke {
    public static void main(String[] args) {
        /* TODO: init friday and model */

        //TaskModelInterface model = new BasicTaskModel();
        //StorageInterface storage = new BasicStorage(model);
        //ControllerInterface friday = new FridayController(model);

        TaskModelInterface model = new TaskList();
        StorageInterface storage = new Storage(model);
        ControllerInterface friday = new Parser(model);


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
