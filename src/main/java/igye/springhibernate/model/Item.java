package igye.springhibernate.model;

import com.sun.istack.internal.NotNull;

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
}
