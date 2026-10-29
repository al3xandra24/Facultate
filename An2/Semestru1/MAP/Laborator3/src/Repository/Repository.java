package Repository;
import Domain.Entity;
import Exceptions.ValidationException;
import Exceptions.RepositoryExceptions;
public interface Repository<ID, E extends Entity<ID>> {
    /**
     * Saves a new entity
     * @param entity the entity to save
     * @return the saved entity
     * @throws IllegalArgumentException if the entity is null
     * @throws ValidationException if the entity is not valid
     * @throws RepositoryExceptions if an entity with the same ID already exists or the data cannot be stored
     */
    E save (E entity);

    /**
     * Finds the entity with the given ID.
     * @param id the ID of the entity
     * @return the entity with the given ID, or null if it does not exist
     * @throws IllegalArgumentException if the ID is null
     * @throws RepositoryExceptions if the data cannot be read
     */
    E find (ID id);

    /**
     * Replaces an existing entity with the given one (the one with the same ID).
     * @param entity the entity with the new data
     * @return the updated entity
     * @throws IllegalArgumentException if the entity is null
     * @throws ValidationException if the entity is not valid
     * @throws RepositoryExceptions if no entity with this ID exists or the data cannot be stored
     */
    E update (E entity);

    /**
     * Deletes the entity with the given ID.
     * @param id the ID of the entity to delete
     * @return the deleted entity
     * @throws IllegalArgumentException if the ID is null
     * @throws RepositoryExceptions if no entity with this ID exists  or the data cannot be stored
     */
    E delete (ID id);

    /**
     * Returns all the entities.
     * @return all the entities in the repository (empty if there are none)
     * @throws RepositoryExceptions if the data cannot be read
     */
    Iterable<E> findAll();

}
