package igye.springhibernate.model;



import javax.validation.constraints.NotNull;

import javax.persistence.*;

@Entity
public class Item {
    @Id
    @GeneratedValue
    protected Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    protected AuctionType auctionTypeEnumerated = AuctionType.HIGHEST_BID;

    @NotNull
    @Convert(converter = AuctionTypeConverter.class)
    protected AuctionType auctionTypeConverted = AuctionType.HIGHEST_BID;

    @NotNull
    @Convert(
            converter = MonetaryAmountConverter.class,
            disableConversion = false)
    @Column(name = "PRICE", length = 63)
    protected MonetaryAmount buyNowPrice;

    public Long getId() {
        return id;
    }

    public AuctionType getAuctionTypeEnumerated() {
        return auctionTypeEnumerated;
    }

    public void setAuctionTypeEnumerated(AuctionType auctionTypeEnumerated) {
        this.auctionTypeEnumerated = auctionTypeEnumerated;
    }

    public AuctionType getAuctionTypeConverted() {
        return auctionTypeConverted;
    }

    public void setAuctionTypeConverted(AuctionType auctionTypeConverted) {
        this.auctionTypeConverted = auctionTypeConverted;
    }

    public MonetaryAmount getBuyNowPrice() {
        return buyNowPrice;
    }

    public void setBuyNowPrice(MonetaryAmount buyNowPrice) {
        this.buyNowPrice = buyNowPrice;
    }
}
