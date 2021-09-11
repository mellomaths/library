package com.mellomaths.library.domain.core;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class CustomDate implements Serializable {

    private static final long serialVersionUID = 7495691006149214808L;

    private final LocalDateTime value;

    public CustomDate() {
        this.value = LocalDateTime.now();
    }

    public CustomDate(LocalDateTime value) {
        this.value = value;
    }

    public static CustomDate of(String value) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime datetime = LocalDateTime.parse(value, formatter);
        return new CustomDate(datetime);
    }

    public LocalDateTime getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CustomDate)) {
            return false;
        }
        CustomDate date = (CustomDate) o;
        return Objects.equals(value, date.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }


}