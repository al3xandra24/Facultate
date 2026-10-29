package Repository;

import Domain.User;
import Validator.Validator;

public class UserDBRepository implements Repository<Long, User>{
    private final Validator<User> validator;
    private final String url;
    private final String username;
    private final String password;
    public UserDBRepository(Validator<User> validator, String url, String username, String password) {
        this.validator = validator;
        this.url = url;
        this.username = username;
        this.password = password;
    }
    @Override
    public User save(User entity) {
        throw new UnsupportedOperationException("Save not implemented yet.");
    }

    @Override
    public User find(Long aLong) {
        throw new UnsupportedOperationException("Find not implemented yet.");
    }

    @Override
    public User update(User entity) {
        throw new UnsupportedOperationException("Update not implemented yet.");
    }

    @Override
    public User delete(Long aLong) {
        throw new UnsupportedOperationException("Delete not implemented yet.");
    }

    @Override
    public Iterable<User> findAll() {
        throw new UnsupportedOperationException("Find all not implemented yet.");
    }
}
