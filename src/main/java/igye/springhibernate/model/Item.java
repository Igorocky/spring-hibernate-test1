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
    @org.hibernate.annotations.Type(
            type = "monetary_amount_usd"
    )
    @org.hibernate.annotations.Columns(columns = {
            @Column(name = "BUYNOWPRICE_AMOUNT"),
            @Column(name = "BUYNOWPRICE_CURRENCY", length = 3)
    })
    protected MonetaryAmount buyNowPrice;

    @NotNull
    @org.hibernate.annotations.Type(
            type = "monetary_amount_eur"
    )
    @org.hibernate.annotations.Columns(columns = {
            @Column(name = "INITIALPRICE_AMOUNT"),
            @Column(name = "INITIALPRICE_CURRENCY", length = 3)
    })
    protected MonetaryAmount initialPrice;

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

    public MonetaryAmount getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(MonetaryAmount initialPrice) {
        this.initialPrice = initialPrice;
    }
}
