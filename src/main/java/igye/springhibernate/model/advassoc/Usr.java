package igye.springhibernate.model.advassoc;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
public class Usr {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JoinColumn
    protected Addr shippingAddress;

    private String name;

    public Long getId() {
        return id;
    }

    public Addr getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Addr shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
