package com.mellomaths.library.domain.core;

import com.mellomaths.library.domain.exception.ParseCustomDateException;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class CustomDate implements Serializable {

    private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final SimpleDateFormat formatter = new SimpleDateFormat(DATE_PATTERN);

    private static final long serialVersionUID = 7495691006149214808L;

    private final Date value;

    public CustomDate() {
        this.value = new Date();
    }

    public CustomDate(Date value) {
        this.value = value;
    }

    public static CustomDate of(String value) {
        try {
            return new CustomDate(formatter.parse(value));
        } catch (ParseException ex) {
            throw new ParseCustomDateException(ex.getMessage());
        }
    }

    public Date getValue() {
        return value;
    }

    @Override
    public String toString() {
        return formatter.format(value);
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