package Repository;

import Domain.Entity;

import java.util.HashMap;
import java.util.Map;
import Exceptions.RepositoryExceptions;
import Validator.Validator;

public class InMemoryRepository<ID, E extends Entity<ID>> implements Repository<ID, E> {
    private Map<ID, E> entities;
    private Validator<E> validator;
    public InMemoryRepository(Validator<E> validator_)
    {
        this.validator = validator_;
        this.entities = new HashMap<>();
    }
    @Override
    public E save(E entity) {
        if (entity == null)
            throw new RepositoryExceptions("Cannot save null entity!");
        validator.validate(entity);
        if (entities.containsKey(entity.getID())) {
            throw new RepositoryExceptions("Cannot save entity with id " + entity.getID() + " already exists!");
        }
        else  {
            entities.put(entity.getID(), entity);
            return entity;
        }
    }

    @Override
    public E find(ID id) {
        if  (id == null)
            throw new RepositoryExceptions("Cannot find null id!");
        return entities.get(id);
    }

    @Override
    public E update(E entity) {
        if (entity == null)
            throw new RepositoryExceptions("Cannot update null entity!");
        validator.validate(entity);
        if  (!entities.containsKey(entity.getID())) {
            throw new RepositoryExceptions("Entity does not exist!");
        }
        entities.put(entity.getID(), entity);
        return null;
    }

    @Override
    public E delete(ID id) {
        if (id == null)
            throw new RepositoryExceptions("Cannot delete null id!");
        if   (!entities.containsKey(id)) {
            throw new RepositoryExceptions("Entity does not exist!");
        }
        return entities.remove(id);
    }

    @Override
    public Iterable<E> findAll() {
        return entities.values();
    }
}
