package com.kpaw.sakilaspringbootrest.domain.movie;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "language")
public class Language {

    @Id
    @Column(name = "language_id")
    private Integer languageId;

    @Column(name = "name")
    private String name;

    public Language() {

    }

    public Language(Integer languageId, String name) {
        this.languageId = languageId;
        this.name = name;
    }

    public Integer getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
