public class Ui {

    public Ui(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String underscore = "    ____________________________________________________________" + "\n" ;
        String intro = underscore +
                "      Hello! I'm Duke " + "\n" +
                "      What can I do for you?" + "\n" +
                underscore ;
        System.out.println(logo + intro);
    }
}
