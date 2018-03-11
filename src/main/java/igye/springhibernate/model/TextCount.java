package igye.springhibernate.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@org.hibernate.annotations.Immutable
@org.hibernate.annotations.Subselect(
        value = "select m.author, " +
                "count(m.ID) as NUMBEROFMESSAGES " +
                "from Message m " +
                "group by m.author"
)
@org.hibernate.annotations.Synchronize({"Message"})
public class TextCount {
    @Id
    private String author;

    private Long numberOfMessages;

    public String getAuthor() {
        return author;
    }

    public Long getNumberOfMessages() {
        return numberOfMessages;
    }
}