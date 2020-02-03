package org.com.sda.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = "getContinents", query = "select c from Continent c where continent_name = :continentName")
})
@Entity
@Table(name = "continents")
public class Continent {
    @Id
    private int id;
    @Column(name = "continent_name")
    private String continent_name;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "continent")
    private List<Country> countrySet;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Continent continent = (Continent) o;
        return id == continent.id &&
                Objects.equals(continent_name, continent.continent_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, continent_name);
    }

    @Override
    public String toString() {
        return "Continent{" +
                "id=" + id +
                ", continent_name='" + continent_name + '\'' +
                 +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContinent_name() {
        return continent_name;
    }

    public void setContinent_name(String continent_name) {
        this.continent_name = continent_name;
    }


}
