package org.com.sda.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
@NamedQueries({
        @NamedQuery(name = "getRoomAvailability", query = "select r from RoomAvailability r where r.fromDate=:fromDate and r.toDate=:toDate and r.hotel=:hotel")
})
@Entity
@Table(name = "rooms")
public class RoomAvailability {
    @Id
    private int room_id;
    @Column(name = "from_date")
    private Date fromDate;
    @Column(name = "to_date")
    private Date toDate;
    @Column(name = "nr_available_single_rooms")
    private int numberSingleRoomsAvailable;
    @Column(name = "nr_available_double_rooms")
    private int numberDoubleRoomsAvailable;
    @Column(name = "nr_available_extra_beds")
    private int numberOfExtraBedsAvailable;
    @Column(name = "price_single_room")
    private double priceSingleRoom;
    @Column(name = "price_double_room")
    private double priceDoubleRoom;
    @Column(name = "price_extra_bed")
    private double priceExtraBed;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public int getNumberSingleRoomsAvailable() {
        return numberSingleRoomsAvailable;
    }

    public void setNumberSingleRoomsAvailable(int numberSingleRoomsAvailable) {
        this.numberSingleRoomsAvailable = numberSingleRoomsAvailable;
    }

    public int getNumberDoubleRoomsAvailable() {
        return numberDoubleRoomsAvailable;
    }

    public void setNumberDoubleRoomsAvailable(int numberDoubleRoomsAvailable) {
        this.numberDoubleRoomsAvailable = numberDoubleRoomsAvailable;
    }

    public int getNumberOfExtraBedsAvailable() {
        return numberOfExtraBedsAvailable;
    }

    public void setNumberOfExtraBedsAvailable(int numberOfExtraBedsAvailable) {
        this.numberOfExtraBedsAvailable = numberOfExtraBedsAvailable;
    }

    public double getPriceSingleRoom() {
        return priceSingleRoom;
    }

    public void setPriceSingleRoom(double priceSingleRoom) {
        this.priceSingleRoom = priceSingleRoom;
    }

    public double getPriceDoubleRoom() {
        return priceDoubleRoom;
    }

    public void setPriceDoubleRoom(double priceDoubleRoom) {
        this.priceDoubleRoom = priceDoubleRoom;
    }

    public double getPriceExtraBed() {
        return priceExtraBed;
    }

    public void setPriceExtraBed(double priceExtraBed) {
        this.priceExtraBed = priceExtraBed;
    }


}
