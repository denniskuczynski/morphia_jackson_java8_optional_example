package morphia_jackson_java8_optional.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.bson.types.ObjectId;

import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Property;

import java.util.Optional;

@Entity(value="books", noClassnameStored=true)
public class Book {
    @Id
    private ObjectId _id;

    @Property("title")
    @JsonProperty("title")
    private String _title;

    @Property("subtitle")
    @JsonProperty("subtitle")
    private Optional<String> _subtitle = Optional.empty();

    protected Book() {
        // default constructor
    }

    public Book(String pTitle) {
        _title = pTitle;
    }

    public Book(String pTitle, String pSubtitle) {
        _title = pTitle;
        _subtitle = Optional.of(pSubtitle);
    }

    public ObjectId getId() {
        return _id;
    }

    @Override
    public String toString() {
        return "Book[title=\""+_title+"\", subtitle=\""+_subtitle.orElse("N/A")+"\"]";
    }
}
