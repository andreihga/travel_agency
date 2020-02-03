package org.com.sda.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = "findCityByNameAndCountry", query = "select c from City c where cityName=:cityName and c.country = :country"),
})
@Entity
@Table(name = "cities")
public class City {
    @Id
    private int city_id;
    @Column(name = "city_name")
    private String cityName;

    @ManyToOne
    @JoinColumn(name = "country_id" )
    private Country country;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "city")
    private Set<Airport> airportSet;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "city")
    private Set<Hotel> hotelSet;

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

//    public List<Airport> getAirportList() {
//        return airportList;
//    }
//
//    public void setAirportList(List<Airport> airportList) {
//        this.airportList = airportList;
//    }


//    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "city_id")
//    private List<Airport> airportList;


    public int getCityId() {
        return city_id;
    }

    public void setCityId(int cityId) {
        this.city_id = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }


}
