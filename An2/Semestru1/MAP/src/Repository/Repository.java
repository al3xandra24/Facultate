package Repository;
import Domain.Entity;
public interface Repository<ID, E extends Entity<ID>> {
    E save (E entity);
    E find (ID id);
    E update (E entity);
    E delete (ID id);
    Iterable<E> findAll();

}
