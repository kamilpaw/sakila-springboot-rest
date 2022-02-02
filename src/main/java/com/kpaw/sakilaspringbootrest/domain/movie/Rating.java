package com.kpaw.sakilaspringbootrest.domain.movie;

public enum Rating {
    G("G"),
    PG("PG"),
    PG13("PG-13"),
    R("R"),
    NC17("NC-17");

    private String name;

    Rating(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public static Rating getByName(String name){
        for(Rating rating : values()){
            if(rating.getName().equals(name)){
                return rating;
            }
        }
        throw new IllegalArgumentException(name + " is not a valid PropName");
    }
}
