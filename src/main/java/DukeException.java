class DukeException extends Exception{
    DukeException() { }

    void errorAction() {
        System.out.println("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    void incompleteAction(String s) {
        System.out.println("\t☹ OOPS!!! The description of a " + s + " cannot be empty.");
    }
}
