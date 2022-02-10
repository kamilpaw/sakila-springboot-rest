package com.kpaw.sakilaspringbootrest.exception;

public class EntityNotFoundExc extends RuntimeException {

    public EntityNotFoundExc(String entiy, int theId){
        super(entiy + " with id " + theId + " not found");
    }
}
