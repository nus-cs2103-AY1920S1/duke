package notes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDateTime;

public class Note {
    private Integer index;
    private SimpleStringProperty source;
    private SimpleStringProperty category;
    private SimpleStringProperty description;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm", timezone = "UTC")
    private SimpleObjectProperty<LocalDateTime> dateCreated;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm", timezone = "UTC")
    private SimpleObjectProperty<LocalDateTime> date;

    /**
     * Constructor with some Json tags to allow reading from file.
     * @param category contains type of category the note belongs too
     * @param description contains actual note
     * @param date optional date that can be included
     */
    @JsonCreator
    public Note(@JsonProperty("index") int index,
                @JsonProperty("source") String source,
                @JsonProperty("category") String category,
                @JsonProperty("description") String description,
                @JsonProperty("date") LocalDateTime date) {
        this.index = index;
        this.source = new SimpleStringProperty(source);
        this.category = new SimpleStringProperty(category);
        this.description = new SimpleStringProperty(description);
        this.dateCreated = new SimpleObjectProperty<>(LocalDateTime.now());
        this.date = new SimpleObjectProperty<>(date);
    }

    public String getCategory() {
        return category.get();
    }

    public SimpleStringProperty categoryProperty() {
        return category;
    }

    public void setCategory(String category) {
        this.category.set(category);
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

    public LocalDateTime getDateCreated() {
        return dateCreated.get();
    }

    public SimpleObjectProperty<LocalDateTime> dateCreatedProperty() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated.set(dateCreated);
    }

    public LocalDateTime getDate() {
        return date.get();
    }

    public SimpleObjectProperty<LocalDateTime> dateProperty() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date.set(date);
    }

    public String getSource() {
        return source.get();
    }

    public SimpleStringProperty sourceProperty() {
        return source;
    }

    public void setSource(String source) {
        this.source.set(source);
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}
