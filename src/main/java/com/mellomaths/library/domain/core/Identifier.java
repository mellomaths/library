package com.mellomaths.library.domain.core;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class Identifier implements Serializable {

    private static final long serialVersionUID = -491158481360204961L;

    private final UUID value;

    public Identifier() {
        this.value = UUID.randomUUID();
    }

    public Identifier(UUID value) {
        this.value = value;
    }

    public static Identifier of(String value) {
        return new Identifier(UUID.fromString(value));
    }

    public UUID getValue() {
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
        if (!(o instanceof Identifier)) {
            return false;
        }
        Identifier identifier = (Identifier) o;
        return Objects.equals(value, identifier.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

}