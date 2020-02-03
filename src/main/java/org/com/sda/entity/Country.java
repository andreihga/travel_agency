package org.com.sda.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = "getCountries", query = "select c from Country c"),
        @NamedQuery(name = "getCountryByName", query = "select c from Country c where countryName = :countryName"),
        @NamedQuery(name = "getContinentByCountry", query = "select c from Country c where continent = :continent")
})
@Entity
@Table(name = "countries")
public class Country {
    @Id
    private int id;
    @Column(name = "country_name")
    private String countryName;

    @ManyToOne
    @JoinColumn(name = "continent_id")
    private Continent continent;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy ="country")
    private Set<City> cityList;


    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", countryName='" + countryName + '\'' +
                ", continent=" + continent +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int countryId) {
        this.id = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }
}
