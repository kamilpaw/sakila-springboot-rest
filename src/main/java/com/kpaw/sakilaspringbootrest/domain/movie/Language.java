package com.kpaw.sakilaspringbootrest.domain.movie;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "language")
public class Language {

    @Id
    @Column(name = "language_id")
    private Byte languageId;

    @Column(name = "name")
    private String name;

    public Language() {

    }

    public Language(Byte languageId, String name, Date lastUpdate) {
        this.languageId = languageId;
        this.name = name;
    }

    public Byte getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Byte languageId) {
        this.languageId = languageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Language{" +
                "languageId=" + languageId +
                ", name='" + name + '\'' +
                '}';
    }
}
