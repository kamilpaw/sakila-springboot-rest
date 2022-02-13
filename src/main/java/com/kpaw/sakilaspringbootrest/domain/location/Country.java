package com.kpaw.sakilaspringbootrest.domain.location;

import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "country")
public class Country {

    @Id
    @Column(name = "country_id")
    private Short country_id;

    @Column(name = "country")
    private String country;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_update")
    private Date lastUpdate;

    public Country(){
    }

    public Country(Short country_id, String country) {
        this.country_id = country_id;
        this.country = country;
    }

    public Country(Short country_id, String country, Date lastUpdate) {
        this.country_id = country_id;
        this.country = country;
        this.lastUpdate = lastUpdate;
    }

    public Short getCountry_id() {
        return country_id;
    }

    public void setCountry_id(Short country_id) {
        this.country_id = country_id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

}
