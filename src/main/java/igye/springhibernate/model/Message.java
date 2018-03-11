package igye.springhibernate.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@org.hibernate.annotations.DynamicUpdate
public class Message {
    @Id
    @GeneratedValue
    private Long id;

    private String text;

    private String author;

    @org.hibernate.annotations.Formula(
            "(text || '###')"
    )
    private String derivedText;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    @org.hibernate.annotations.CreationTimestamp
    protected Date createdOn;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(insertable = false, updatable = false)
    @org.hibernate.annotations.Generated(
            org.hibernate.annotations.GenerationTime.ALWAYS
    )
    protected Date lastModified;

    public Message() {
    }

    public Message(String text, String author) {
        this.text = text;
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDerivedText() {
        return derivedText;
    }

    public void setDerivedText(String derivedText) {
        this.derivedText = derivedText;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }
}