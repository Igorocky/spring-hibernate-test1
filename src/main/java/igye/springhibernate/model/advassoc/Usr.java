package igye.springhibernate.model.advassoc;

import javax.persistence.*;

@Entity
public class Usr {
    @Id
    private Long id;

    @OneToOne(
            fetch = FetchType.LAZY, optional = false
    )
    @PrimaryKeyJoinColumn
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
