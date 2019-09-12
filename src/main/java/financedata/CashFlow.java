package financedata;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.time.LocalDateTime;

public class CashFlow {
    private SimpleDoubleProperty value;
    private SimpleObjectProperty<LocalDateTime> dateCreated;
    private SimpleObjectProperty<LocalDateTime> dateDue;

    public CashFlow(double value, LocalDateTime dateDue){
        this.value = new SimpleDoubleProperty(value);
        dateCreated = new SimpleObjectProperty<>(LocalDateTime.now());
        this.dateCreated = new SimpleObjectProperty<>(dateDue);
    }



}
