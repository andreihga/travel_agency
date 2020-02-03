package org.com.sda.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = "getAirports", query = "select a from Airport a"),
        @NamedQuery(name = "getAirportByNameAndCity", query = "select a from Airport a where a.airportName = :airportName and a.city = :city")
})
@Entity
@Table(name = "airports")
public class Airport {
    @Id
    private int airport_id;
    @Column(name = "airport_name")
    private String airportName;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "airport")
    private Set<Flight> flightSet;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }


}
