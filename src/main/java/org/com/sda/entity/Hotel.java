package org.com.sda.entity;

import javax.persistence.*;
import java.util.Set;
@NamedQueries({
        @NamedQuery(name = "getHotelByNameAndCity", query = "select h from Hotel h where h.hotelName=:hotelName and h.city=:city"),
        @NamedQuery(name = "getHotelByCity",query = "select h from Hotel h where h.city = :city")
})
@Entity
@Table(name = "hotels")
public class Hotel {
    @Id
    private int hotel_id;
    @Column(name = "hotel_name")
    private String hotelName;
    @Column(name = "hotel_description")
    private String hotelDescription;
    @Column(name = "standard")
    private int standard;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,mappedBy = "hotel")
    private Set<RoomAvailability> roomSet;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,mappedBy = "hotelTrip")
    private Set<Trip> tripSet;

    public Set<RoomAvailability> getRoomSet() {
        return roomSet;
    }

    public void setRoomSet(Set<RoomAvailability> roomSet) {
        this.roomSet = roomSet;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelDescription() {
        return hotelDescription;
    }

    public void setHotelDescription(String hotelDescription) {
        this.hotelDescription = hotelDescription;
    }

    public int getStandard() {
        return standard;
    }

    public void setStandard(int standard) {
        this.standard = standard;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "hotel_id=" + hotel_id +
                ", hotelName='" + hotelName + '\'' +
                ", hotelDescription='" + hotelDescription + '\'' +
                ", standard=" + standard +
                ", city=" + city +
                ", roomSet=" + roomSet +
                ", tripSet=" + tripSet +
                '}';
    }
}
