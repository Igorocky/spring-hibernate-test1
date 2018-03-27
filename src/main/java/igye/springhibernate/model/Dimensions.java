package igye.springhibernate.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
@AttributeOverrides({
        @AttributeOverride(name = "name", column = @Column(name = "DIMENSIONS_NAME")),
        @AttributeOverride(name = "symbol", column = @Column(name = "DIMENSIONS_SYMBOL"))
})
public class Dimensions extends Measurement {
    protected BigDecimal depth;
    protected BigDecimal height;
    protected BigDecimal width;

    public BigDecimal getDepth() {
        return depth;
    }

    public void setDepth(BigDecimal depth) {
        this.depth = depth;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }
}