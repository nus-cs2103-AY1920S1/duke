package duke.command;

import duke.ListStoreModel;
import duke.Message;
import duke.Storage;
import duke.Ui;
import duke.expense.Person;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Represents a summary Command. A <code>SummaryCommand</code> shows all the debts and
 * loans for all persons in the list of people.
 */
public class SummaryCommand extends Command {
    public SummaryCommand() {
    }

    /**
     * Prints out all the debts and loans of people in the list of people.
     * @param lists taskList and peopleList given.
     * @param ui ui object given.
     * @param storage storage given.
     */
    @Override
    public void execute(ListStoreModel lists, Ui ui, Storage storage) {
        List<Person> people = lists.peopleList;
        if (people.isEmpty()) {
            ui.print("You have no loans or debts currently. :)");
            return;
        }

        people.sort(Comparator.comparingDouble(Person::getAmountOwed));

        List<String> list = new ArrayList<>();
        list.add("Here is a summary of your loans and debts.");
        boolean reachedLoans = false;
        boolean reachedDebts = false;
        int index = 1;
        for (Person person : people) {
            if (person.owesYouMoney()) {
                if (!reachedLoans) {
                    reachedLoans = true;
                    list.add("    Loans:");
                }
                list.add(String.format("      %d. %s: $%.2f", index, person.getName(), person.getAmountLent()));
                index++;
            } else {
                if (!reachedDebts) {
                    reachedDebts = true;
                    list.add("    Debts:");
                    index = 1;
                }
                list.add(String.format("      %d. %s: $%.2f", index, person.getName(), person.getAmountOwed()));
                index++;
            }
        }
        ui.print(new Message(list));
    }
}
