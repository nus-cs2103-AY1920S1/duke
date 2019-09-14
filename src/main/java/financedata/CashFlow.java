package financedata;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDateTime;

public class CashFlow {
    private SimpleStringProperty sourceDescription;
    private SimpleDoubleProperty value;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm", timezone = "UTC")
    private SimpleObjectProperty<LocalDateTime> dateCreated;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm", timezone = "UTC")
    private SimpleObjectProperty<LocalDateTime> dateDue;

/*    public CashFlow(String sourceDescription, Double value, LocalDateTime dateDue){
        this.sourceDescription = new SimpleStringProperty(sourceDescription);
        this.value = new SimpleDoubleProperty(value);
        this.dateCreated = new SimpleObjectProperty<>(dateDue);
        this.dateDue = new SimpleObjectProperty<>(dateDue);
    }*/

    @JsonCreator
    public CashFlow(@JsonProperty("sourceDescription") String sourceDescription, @JsonProperty("value") Double value, @JsonProperty("dateDue") LocalDateTime dateDue){
        this.sourceDescription = new SimpleStringProperty(sourceDescription);
        this.value = new SimpleDoubleProperty(value);
        this.dateCreated = new SimpleObjectProperty<>(LocalDateTime.now());
        this.dateDue = new SimpleObjectProperty<>(dateDue);
    }

    public String getSourceDescription() {
        return sourceDescription.get();
    }

    public SimpleStringProperty sourceDescriptionProperty() {
        return sourceDescription;
    }

    public void setSourceDescription(String sourceDescription) {
        this.sourceDescription.set(sourceDescription);
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
