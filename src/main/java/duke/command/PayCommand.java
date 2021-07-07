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
 * Represents a pay Command. A <code>PayCommand</code> reduces an existing debt for the
 * specified person in the list of people.
 */
public class PayCommand extends Command {
    private String name;
    private double amount;

    public PayCommand(String name, double amount) {
        this.name = name;
        this.amount = amount;
    }

    /**
     * Reduces an existing debt from a specified person in the list of people.
     * @param lists taskList and peopleList given.
     * @param ui ui object given.
     * @param storage storage given.
     * @throws DukeException if specified person does not exist or user is trying to
     *              pay more than the owed amount.
     */
    @Override
    public void execute(ListStoreModel lists, Ui ui, Storage storage) throws DukeException {
        List<Person> people = lists.peopleList;
        int index = people.indexOf(new Person(this.name));
        if (index == -1) {
            throw new DukeException(String.format("You have not borrowed money from %s before!", this.name));
        } else {
            Person person = people.get(index);
            if (person.owesYouMoney()) {
                throw new DukeException("You cannot pay somebody that owes you money!");
            } else if (this.amount > person.getAmountOwed()) {
                throw new DukeException(String.format("You only owe %s $%.2f!", person.getName(),
                                                                    person.getAmountOwed()));
            } else {
                person.pay(this.amount);
                if (person.hasNoMoreTransactions()) {
                    people.remove(person);
                }

                List<String> list = new ArrayList<>();
                list.add("Alright, I've added this transaction:");
                list.add(String.format("    repaid $%.2f to %s", this.amount, this.name));
                ui.print(new Message(list));
            }
        }
    }
}
