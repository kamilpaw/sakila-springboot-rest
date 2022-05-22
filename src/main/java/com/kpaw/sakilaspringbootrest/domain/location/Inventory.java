package com.kpaw.sakilaspringbootrest.domain.location;

import com.kpaw.sakilaspringbootrest.domain.movie.Film;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "inventory")
public class Inventory {

    @Id
    @Column(name = "inventory_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer inventoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "film_id")
    private Film film;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_update")
    private Date lastUpdate;

    public Inventory(){

    }

    public Inventory(Integer inventoryId, Film film, Store store) {
        this.inventoryId = inventoryId;
        this.film = film;
        this.store = store;
    }

    public Inventory(Integer inventoryId, Film film, Store store, Date lastUpdate) {
        this.inventoryId = inventoryId;
        this.film = film;
        this.store = store;
        this.lastUpdate = lastUpdate;
    }

    public Integer getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film filmId) {
        this.film = filmId;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store storeId) {
        this.store = storeId;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "inventoryId=" + inventoryId +
                ", filmId=" + film +
                ", storeId=" + store +
                '}';
    }
}
