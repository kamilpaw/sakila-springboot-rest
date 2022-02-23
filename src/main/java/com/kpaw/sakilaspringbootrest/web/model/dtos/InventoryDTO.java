package com.kpaw.sakilaspringbootrest.web.model.dtos;

import java.util.Date;

public class InventoryDTO {


    private Integer inventoryId;

    private Integer filmId;

    private Byte storeId;

    private Date lastUpdate;

    public InventoryDTO(){

    }

    public InventoryDTO(Integer inventoryId, Integer filmId, Byte storeId, Date lastUpdate) {
        this.inventoryId = inventoryId;
        this.filmId = filmId;
        this.storeId = storeId;
        this.lastUpdate = lastUpdate;
    }

    public Integer getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    public Byte getStoreId() {
        return storeId;
    }

    public void setStoreId(Byte storeId) {
        this.storeId = storeId;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "InventoryDTO{" +
                "inventoryId=" + inventoryId +
                ", filmId=" + filmId +
                ", storeId=" + storeId +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}
