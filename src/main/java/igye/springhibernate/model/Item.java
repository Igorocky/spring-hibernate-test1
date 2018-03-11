package igye.springhibernate.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import static javax.persistence.GenerationType.*;

@Entity
public class Item {
    @Id
    @GeneratedValue
    protected Long id;

    public Long getId() {
        return id;
    }
}
