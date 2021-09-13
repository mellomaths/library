package com.mellomaths.library.domain.core;

import java.text.ParseException;
import java.util.Objects;

public class Entity {

    protected final Identifier id;
    protected final CustomDate creationDate;

    public Entity() {
        this.id = new Identifier();
        this.creationDate = new CustomDate();
    }

    public Entity(Identifier id, CustomDate creationDate) {
        this.id = id;
        this.creationDate = creationDate;
    }

    protected Entity(String id, String creationDate) {
        this.id = Identifier.of(id);
        this.creationDate = CustomDate.of(creationDate);
    }

    public Identifier getId() {
        return id;
    }

    public CustomDate getCreationDate() {
        return creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Entity)) {
            return false;
        }
        Entity entity = (Entity) o;
        return Objects.equals(id, entity.id) && Objects.equals(creationDate, entity.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, creationDate);
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId().toString() + "'" +
                ", creationDate='" + getCreationDate().toString() + "'" +
                "}";
    }


}