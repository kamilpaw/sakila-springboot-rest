package com.kpaw.sakilaspringbootrest.domain.movie;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "category")
@Entity
public class Category {

    @Id
    @Column(name = "category_id")
    private Byte category_id;

    @Column(name = "name")
    private String name;

    public Category() {
    }

    public Category(Byte category_id, String name) {
        this.category_id = category_id;
        this.name = name;
    }

    public Byte getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Byte category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
