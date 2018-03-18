package igye.springhibernate.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class MonetaryAmountConverter implements AttributeConverter<MonetaryAmount, String> {
    @Override
    public String convertToDatabaseColumn(MonetaryAmount monetaryAmount) {
        return monetaryAmount == null ? null : monetaryAmount.toString();
    }
    @Override
    public MonetaryAmount convertToEntityAttribute(String s) {
        return MonetaryAmount.fromString(s);
    }
}