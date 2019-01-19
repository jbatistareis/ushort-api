package com.jbatista.ushort.api.entities;

import java.util.List;
import org.springframework.data.domain.Page;

public class AddressPage {

    private final List<Address> content;
    private final int totalElements;
    private final int totalPages;
    private final int size;
    private final int number;
    private final boolean first;
    private final boolean last;
    private final boolean empty;

    public AddressPage(Page<Address> page) {
        this.content = page.getContent();
        this.empty = page.isEmpty();
        this.first = page.isFirst();
        this.last = page.isLast();
        this.number = page.getNumber();
        this.size = page.getSize();
        this.totalElements = page.getNumberOfElements();
        this.totalPages = page.getTotalPages();
    }

    public AddressPage(List<Address> page) {
        this.content = page;
        this.empty = page.isEmpty();
        this.first = true;
        this.last = true;
        this.number = 0;
        this.size = page.size();
        this.totalElements = page.size();
        this.totalPages = 1;
    }

    public List<Address> getContent() {
        return content;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getSize() {
        return size;
    }

    public int getNumber() {
        return number;
    }

    public boolean isFirst() {
        return first;
    }

    public boolean isLast() {
        return last;
    }

    public boolean isEmpty() {
        return empty;
    }

}
