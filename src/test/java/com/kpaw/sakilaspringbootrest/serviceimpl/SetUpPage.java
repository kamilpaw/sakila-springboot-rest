package com.kpaw.sakilaspringbootrest.serviceimpl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public class SetUpPage<T> implements Page<T> {
    List<T> listOfObjects;

    public SetUpPage(List<T> listOfObjects) {
        this.listOfObjects = listOfObjects;
    }

    @Override
    public int getTotalPages() {
        return 1;
    }

    @Override
    public long getTotalElements() {
        return 2;
    }

    @Override
    public int getNumber() {
        return 1;
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public int getNumberOfElements() {
        return 2;
    }

    @Override
    public List<T> getContent() {
        return this.listOfObjects;
    }

    @Override
    public boolean hasContent() {
        return true;
    }

    @Override
    public Sort getSort() {
        return Sort.unsorted();
    }

    @Override
    public boolean isFirst() {
        return true;
    }

    @Override
    public boolean isLast() {
        return true;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }

    @Override
    public Pageable nextPageable() {
        return null;
    }

    @Override
    public Pageable previousPageable() {
        return null;
    }

    @Override
    public <U> Page<U> map(Function<? super T, ? extends U> converter) {
        return null;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
