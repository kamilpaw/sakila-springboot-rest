package com.kpaw.sakilaspringbootrest.domain.movie;


import javax.persistence.*;

@Table(name = "category")
@Entity
public class Category {

    @Id
    @Column(name = "category_id")
    private Integer category_id;

    @Column(name = "name")
    private String name;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}