package igye.springhibernate.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
@AttributeOverrides({
        @AttributeOverride(name = "name",
                column = @Column(name = "WEIGHT_NAME")),
        @AttributeOverride(name = "symbol",
                column = @Column(name = "WEIGHT_SYMBOL"))
})
public class Weight extends Measurement {
    @Column(name = "WEIGHT")
    protected BigDecimal value;

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}