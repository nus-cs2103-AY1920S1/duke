import expenses.Expenses;
import expenses.Item;
import expenses.ItemInfo;
import expenses.TrackingService;
import commands.DukeException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import rxjava.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class ExpensesWindow extends BorderPane {

    @FXML
    private Label error;
    @FXML
    private Label amount;
    @FXML
    private TextField title;
    @FXML
    private TextField amt;
    @FXML
    private Button credit;
    @FXML
    private Button debit;
    @FXML
    private VBox creditColumn;
    @FXML
    private VBox debitColumn;
    @FXML
    private HBox fields;

    private Expenses expenses;
    private Button saveButton = new Button();
    private TrackingService service = TrackingService.getTrackingService();
    private Subscription<ItemInfo> deletionSubscription = TrackingService
            .getDeleteItemSubject().subscribe(this::delete);
    private Subscription<ItemInfo> editSubscription = TrackingService.getEditItemSubject().subscribe(this::edit);

    void setError(String message) {
        error.setText(message);
    }

    void delete(ItemInfo item) {
        try {
            if (item.isCredit) {
                expenses.remove(item.index, true);
            } else {
                expenses.remove(item.index, false);
            }
            save();
            render();
        } catch (DukeException e) {
            setError(e.getMessage());
        }
    }

    void edit(ItemInfo item) {
        ArrayList<Item> ledger = item.isCredit ? expenses.credit : expenses.debit;
        title.setText(ledger.get(item.index).title);
        amt.setText(Double.toString(ledger.get(item.index).amount));


        saveButton.setText("Save");
        fields.getChildren().remove(saveButton);
        fields.getChildren().add(saveButton);
        saveButton.setOnAction(e -> {
            //error handling
            String text = title.getText();
            double amount;
            if (text.isBlank() || amt.getText().isBlank()) {
                setError("Both fields must be non-empty!");
                return;
            }
            try {
                amount = Double.parseDouble(amt.getText());
                if (amount <= 0) {
                    setError("Amount should be positive.");
                    return;
                }
            } catch (NumberFormatException exception) {
                setError("Amount must be a real number!");
                return;
            }
            if (item.isCredit) {
                assert expenses.credit.size() >= item.index;
                updateLedger(item, true, text, amount);
            } else {
                assert expenses.debit.size() >= item.index;
                updateLedger(item, false, text, amount);
            }
        });

    }

    void updateLedger(ItemInfo item, boolean isCredit, String newTitle, double newAmount) {
        ArrayList<Item> arr = isCredit ? expenses.credit : expenses.debit;
        Item toChange = arr.get(item.index);
        double initial = toChange.amount;
        toChange.title = newTitle;
        toChange.amount = newAmount;
        expenses.balance += isCredit ? newAmount - initial : initial - newAmount;
        arr.set(item.index, toChange);
        render();
        fields.getChildren().remove(saveButton);
        save();
    }

    void setAmount() {
        double amt = expenses.balance;
        amount.setText(Double.toString(amt));
        if (amt < 0) {
            setError("Your balance is negative!");
        }
    }

    /**
     * Clears the whole window and repopulates it with credit items, debit items and balance.
     */
    void render() {
        creditColumn.getChildren().clear();
        debitColumn.getChildren().clear();
        clearError();
        AtomicInteger index = new AtomicInteger(0);
        List<ColumnItem> creditItems = expenses.credit
                .stream()
                .map(i -> new ColumnItem(i.title, Double.toString(i.amount), true, index.getAndIncrement()))
                .collect(Collectors.toList());
        index.set(0);
        List<ColumnItem> debitItems = expenses.debit
                .stream()
                .map(i -> new ColumnItem(i.title, Double.toString(i.amount), false, index.getAndIncrement()))
                .collect(Collectors.toList());
        creditColumn.getChildren().addAll(creditItems);
        debitColumn.getChildren().addAll(debitItems);
        setAmount();
    }

    void clearError() {
        error.setText("");
    }

    void setExpensesObject(Expenses e) {
        expenses = e;
    }

    void setListeners() {
        credit.setOnAction(e -> handleInput(true));
        debit.setOnAction(e -> handleInput(false));
    }

    @FXML
    private void handleInput(boolean isCredit) {
        clearError();
        String desc = title.getText();
        String amount = amt.getText();
        double a;
        if (desc.isBlank() || amount.isBlank()) {
            setError("Both fields must be non-empty!");
            return;
        }
        try {
            a = Double.parseDouble(amount);
            if (a <= 0) {
                setError("Amount must be positive");
                return;
            }
        } catch (NumberFormatException e) {
            setError("Your amount must be a real number!");
            return;
        }
        if (isCredit) {
            expenses.add(desc, a, true);
        } else {
            expenses.add(desc, a, false);
        }
        setAmount();
        save();
        render();
    }

    void save() {
        try {
            expenses.save();
        } catch (DukeException e) {
            setError("I could not save your changes. Try again later!");
        }
    }
}
