package com.kpaw.sakilaspringbootrest.exception;

public class EntityNotFoundExc extends RuntimeException {

    public EntityNotFoundExc(String entity, int theId){
        super(entity + " with id " + theId + " not found");
    }
}
