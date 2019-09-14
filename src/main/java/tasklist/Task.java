package tasklist;

import financedata.CashFlow;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ui.TextUi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Abstract class for all tasks.
 * gurantees that all important methods are implemented
 */
public abstract class Task {

    protected SimpleStringProperty taskType;
    protected SimpleStringProperty description;
    protected SimpleBooleanProperty isDone;
    protected ObjectProperty<LocalDateTime> dateDue;
    protected ObservableList<CashFlow> cashFlows = FXCollections.observableArrayList();
    protected TextUi ui = new TextUi();
    protected static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("dd MMMM hhmm a");

    /**
     * First of two constructors for deadline and event tasks.
     */
    public Task(String description, boolean completionStatus, LocalDateTime date) {
        this.description = new SimpleStringProperty(description);
        this.isDone = new SimpleBooleanProperty(completionStatus);
        this.dateDue = new SimpleObjectProperty<>(date);
    }

    /**
     * Second of two constructors for todo tasks.
     */
    public Task(String description, boolean completionStatus) {
        this.description = new SimpleStringProperty(description);
        this.isDone = new SimpleBooleanProperty(completionStatus);
    }

    /**
     * Returns the current status of the task.
     * @return returns either a tick or cross according to the completion status
     */
    public String getCurrentStatus() {
        return ((isDone.getValue() ? "[✓] " : "[✗] ")); //return tick or X symbols
    }

    /**
     * overall status of task that includes name,completion status, description and date(if applicable).
     * to be implemented according to task type.
     * @return the overall status in a assignment correct format
     */
    public abstract String getOverallStatus();

    /**
     * changes task status to done.
     */
    public void completeTask() {
        isDone.setValue(true);
    }

    /**
     * prints the the full task details in to a format for easy storage and loading.
     * to be implemented according to task type.
     * @return a string that contains the details of the task in a format the parser can read
     */
    public abstract String encodeForStorage();

    public void addCashFlow(String sourceDescription, Double value,LocalDateTime dateDue){
        cashFlows.add(new CashFlow(sourceDescription, value, dateDue));
        System.out.println(cashFlows.size());
    }

    public void removeCashFlow(String cashFlowToRemove){
        if (cashFlowToRemove.contains("all")){
            cashFlows.clear();
            ui.printRemovedTask("All entries", cashFlows.size()+1);
        }else {
            int cashFlowTodDelete = Integer.parseInt(cashFlowToRemove);
            //ui.printRemovedTask(cashFlows.get(CashFlowTodDelete - 1).getOverallStatus(), tasks.size());
            cashFlows.remove(cashFlowTodDelete - 1);
        }
    }

    public String getTaskType() {
        return taskType.get();
    }

    public SimpleStringProperty taskTypeProperty() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType.set(taskType);
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public boolean isIsDone() {
        return isDone.get();
    }

    public SimpleBooleanProperty isDoneProperty() {
        return isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone.set(isDone);
    }

    public LocalDateTime getDateDue() {
        return dateDue.get();
    }

    public ObjectProperty<LocalDateTime> dateDueProperty() {
        return dateDue;
    }

    public void setDateDue(LocalDateTime dateDue) {
        this.dateDue.set(dateDue);
    }

    public ObservableList<CashFlow> getCashFlows() {
        return cashFlows;
    }

    public void setCashFlows(ObservableList<CashFlow> cashFlows) {
        this.cashFlows = cashFlows;
    }
}