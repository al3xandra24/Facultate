package Repository;

import Domain.Entity;

import java.util.HashMap;
import java.util.Map;
import Exceptions.RepositoryExceptions;
import Exceptions.ValidationException;
import Validator.Validator;

public class InMemoryRepository<ID, E extends Entity<ID>> implements Repository<ID, E> {
    private Map<ID, E> entities;
    private Validator<E> validator;

    /**
     * Creates an empty repository
     * @param validator_ the validator used before every save and update
     */
    public InMemoryRepository(Validator<E> validator_)
    {
        this.validator = validator_;
        this.entities = new HashMap<>();
    }

    /**
     * Saves a new entity
     * @param entity the entity to save
     * @return the saved entity
     * @throws IllegalArgumentException if the entity is null
     * @throws ValidationException if the entity is not valid
     * @throws RepositoryExceptions if an entity with the same ID already exists
     */
    @Override
    public E save(E entity) {
        if (entity == null)
            throw new IllegalArgumentException("Cannot save null entity!");
        validator.validate(entity);
        if (entities.containsKey(entity.getID())) {
            throw new RepositoryExceptions("Cannot save entity with id " + entity.getID() + " already exists!");
        }
        else  {
            entities.put(entity.getID(), entity);
            return entity;
        }
    }

    /**
     * Finds the entity of the given ID
     * @param id the ID of the entity
     * @return the entity with the given ID, or null if it does not exist
     * @throws IllegalArgumentException if the ID is null
     */
    @Override
    public E find(ID id) {
        if  (id == null)
            throw new IllegalArgumentException("Cannot find null id!");
        return entities.get(id);
    }

    /**
     * Replaces an existing entity with the given one (the one with the same ID).
     * @param entity the entity with the new data
     * @return the updated entity
     * @throws IllegalArgumentException if the entity is null
     * @throws ValidationException if the entity is not valid
     * @throws RepositoryExceptions if no entity with this ID exists or the data cannot be stored
     */
    @Override
    public E update(E entity) {
        if (entity == null)
            throw new IllegalArgumentException("Cannot update null entity!");
        validator.validate(entity);
        if  (!entities.containsKey(entity.getID())) {
            throw new RepositoryExceptions("Entity does not exist!");
        }
        entities.put(entity.getID(), entity);
        return entity;
    }

    /**
     * Deletes the entity with the given ID.
     * @param id the ID of the entity to delete
     * @return the deleted entity
     * @throws IllegalArgumentException if the ID is null
     * @throws RepositoryExceptions if no entity with this ID exists  or the data cannot be stored
     */
    @Override
    public E delete(ID id) {
        if (id == null)
            throw new IllegalArgumentException("Cannot delete null id!");
        if   (!entities.containsKey(id)) {
            throw new RepositoryExceptions("Entity does not exist!");
        }
        return entities.remove(id);
    }

    /**
     * Returns all the entities.
     * @return all the entities in the repository (empty if there are none)
     * @throws RepositoryExceptions if the data cannot be read
     */
    @Override
    public Iterable<E> findAll() {
        return entities.values();
    }
}
