package duke.command;

import duke.ListStoreModel;
import duke.Message;
import duke.Storage;
import duke.Ui;
import duke.expense.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a lend Command. A <code>LendCommand</code> adds a loan for the specified
 * person to the list of people.
 */
public class LendCommand extends Command {
    private String name;
    private double amount;

    public LendCommand(String name, double amount) {
        this.name = name;
        this.amount = amount;
    }

    /**
     * Adds a loan to the specified person into the list of people.
     * @param lists taskList and peopleList given.
     * @param ui ui object given.
     * @param storage storage given.
     */
    @Override
    public void execute(ListStoreModel lists, Ui ui, Storage storage) {
        List<Person> people = lists.peopleList;
        int index = people.indexOf(new Person(this.name));
        if (index == -1) {
            people.add(new Person(this.name, this.amount * -1));
        } else {
            Person person = people.get(index);
            person.pay(this.amount);
            if (person.hasNoMoreTransactions()) {
                people.remove(person);
            }
        }

        List<String> list = new ArrayList<>();
        list.add("Alright, I've added this transaction:");
        list.add(String.format("  lent $%.2f to %s", this.amount, this.name));
        ui.print(new Message(list));
    }
}
