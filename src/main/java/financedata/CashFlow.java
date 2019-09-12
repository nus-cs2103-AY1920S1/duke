package financedata;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.time.LocalDateTime;

public class CashFlow {
    private String sourceName;
    private SimpleDoubleProperty value;
    private SimpleObjectProperty<LocalDateTime> dateCreated;
    private SimpleObjectProperty<LocalDateTime> dateDue;

    public CashFlow(String sourceName, Double value, LocalDateTime dateDue){
        this.sourceName = sourceName;
        this.value = new SimpleDoubleProperty(value);
        dateCreated = new SimpleObjectProperty<>(LocalDateTime.now());
        this.dateCreated = new SimpleObjectProperty<>(dateDue);
    }

    public String getSourceName() {
        return sourceName;
    }

    public double getValue() {
        return value.get();
    }

    public SimpleDoubleProperty valueProperty() {
        return value;
    }

    public void setValue(double value) {
        this.value.set(value);
    }

    public LocalDateTime getDateCreated() {
        return dateCreated.get();
    }

    public SimpleObjectProperty<LocalDateTime> dateCreatedProperty() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated.set(dateCreated);
    }

    public LocalDateTime getDateDue() {
        return dateDue.get();
    }

    public SimpleObjectProperty<LocalDateTime> dateDueProperty() {
        return dateDue;
    }

    public void setDateDue(LocalDateTime dateDue) {
        this.dateDue.set(dateDue);
    }
}
