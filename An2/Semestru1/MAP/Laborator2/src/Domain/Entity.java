package Domain;

import java.util.Objects;

public class Entity<ID> {
    private ID id;

    /**
     * Retrieves the unique identifier of the entity.
     * @return the ID of the entity (Long)
     */
    public ID getID() {return id;}

    /**
     * Sets the unique identifier of the entity.
     * @param id_ id the new ID to be set (Long)
     */
    public void setID(ID id_) {this.id = id_;}

    /**
     * Compares this entity to the specified object. Two entities are considered equal if and only if they have the same ID.
     * @param o   the reference object with which to compare.
     * @return true if the objects are equal (have the same ID); false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity<?> entity = (Entity<?>) o;
        return Objects.equals(id, entity.id);
    }

    /**
     * Returns a hash code for the entity
     * @return the hash code value for this entity
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
