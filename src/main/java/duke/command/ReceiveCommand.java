package duke.command;

import duke.DukeException;
import duke.ListStoreModel;
import duke.Message;
import duke.Storage;
import duke.Ui;
import duke.expense.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a receive Command. A <code>ReceiveCommand</code> reduces an existing loan for the
 * specified person in the list of people.
 */
public class ReceiveCommand extends Command {
    private String name;
    private double amount;

    public ReceiveCommand(String name, double amount) {
        this.name = name;
        this.amount = amount;
    }

    /**
     * Reduces an existing loan for a specified person in the list of people.
     * @param lists taskList and peopleList given.
     * @param ui ui object given.
     * @param storage storage given.
     * @throws DukeException if specified person does not exist or user is trying to
     *              receive more than the lent amount.
     */
    @Override
    public void execute(ListStoreModel lists, Ui ui, Storage storage) throws DukeException {
        List<Person> people = lists.peopleList;
        int index = people.indexOf(new Person(this.name));
        if (index == -1) {
            throw new DukeException(String.format("%s has not borrowed money from you before!", this.name));
        } else {
            Person person = people.get(index);
            if (person.lentYouMoney()) {
                throw new DukeException("You can't receive money from someone you borrowed money from!");
            } else if (this.amount > person.getAmountLent()) {
                throw new DukeException(String.format("You only lent %s $%.2f!", person.getName(),
                                                                    person.getAmountLent()));
            } else {
                person.receive(this.amount);
                if (person.hasNoMoreTransactions()) {
                    people.remove(person);
                }

                List<String> list = new ArrayList<>();
                list.add("Alright, I've added this transaction:");
                list.add(String.format("    received $%.2f from %s", this.amount, this.name));
                ui.print(new Message(list));
            }
        }
    }
}
