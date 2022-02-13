package com.kpaw.sakilaspringbootrest.web.model.pages;

public class PageSizeAndNumber {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 5;


    public static int pageSize(Integer pageSize) {
        if (pageSize == null || pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        return pageSize;
    }

    public static int pageNumber(Integer pageNumber) {
        if (pageNumber == null || pageNumber < 0) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }
        return pageNumber;
    }
}
