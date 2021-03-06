package igye.springhibernate.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "USERS")
public class User implements Serializable {
    @Id
    @GeneratedValue
    protected Long id;
    @Convert(
            converter = ZipcodeConverter.class,
            attributeName = "city.zipcode"
    )
    protected Address homeAddress;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street",
                    column = @Column(name = "BILLING_STREET")),
            @AttributeOverride(name = "city.zipcode",
                    column = @Column(name = "BILLING_ZIPCODE", length = 5)),
            @AttributeOverride(name = "city.name",
                    column = @Column(name = "BILLING_CITY")),
            @AttributeOverride(name = "city.country",
                    column = @Column(name = "BILLING_COUNTRY"))
    })
    @Convert(
            converter = ZipcodeConverter.class,
            attributeName = "city.zipcode"
    )
    protected Address billingAddress;

    public Long getId() {
        return id;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }
}
