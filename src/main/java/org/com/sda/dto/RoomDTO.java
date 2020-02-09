package org.com.sda.dto;

import java.util.Date;

public class RoomDTO {
    private Date fromDate;
    private Date toDate;

    private int nrOfAvailableSingleRooms;
    private int nrOfAvailableDoubleRooms;
    private int numberOfExtraBedsAvailable;
    private double priceSingleRoom;
    private double priceDoubleRoom;
    private double priceExtraBed;
    private HotelDTO hotelDTO;

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

    public int getNrOfAvailableSingleRooms() {
        return nrOfAvailableSingleRooms;
    }

    public void setNrOfAvailableSingleRooms(int nrOfAvailableSingleRooms) {
        this.nrOfAvailableSingleRooms = nrOfAvailableSingleRooms;
    }

    public int getNrOfAvailableDoubleRooms() {
        return nrOfAvailableDoubleRooms;
    }

    public void setNrOfAvailableDoubleRooms(int nrOfAvailableDoubleRooms) {
        this.nrOfAvailableDoubleRooms = nrOfAvailableDoubleRooms;
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

    public HotelDTO getHotelDTO() {
        return hotelDTO;
    }

    public void setHotelDTO(HotelDTO hotelDTO) {
        this.hotelDTO = hotelDTO;
    }
}
