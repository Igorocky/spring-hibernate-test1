package igye.springhibernate.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class AuctionTypeConverter implements AttributeConverter<AuctionType, String> {

    @Override
    public String convertToDatabaseColumn(AuctionType attribute) {
        return attribute.name().substring(0, 1);
    }

    @Override
    public AuctionType convertToEntityAttribute(String dbData) {
        if ("H".equals(dbData)) {
            return AuctionType.HIGHEST_BID;
        } else if ("L".equals(dbData)) {
            return AuctionType.LOWEST_BID;
        } else {
            return AuctionType.FIXED_PRICE;
        }
    }
}
