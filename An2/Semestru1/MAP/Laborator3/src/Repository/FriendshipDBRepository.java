package Repository;

import Domain.Friendship;
import Domain.Tuple;
import Validator.Validator;

public class FriendshipDBRepository implements Repository<Tuple<Long, Long>, Friendship>{
    private final Validator<Friendship> validator;
    private final String url;
    private final String username;
    private final String password;

    public FriendshipDBRepository(Validator<Friendship> validator, String url, String username, String password) {
        this.validator = validator;
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public Friendship save(Friendship entity) {
        throw new UnsupportedOperationException("Save not implemented yet.");
    }

    @Override
    public Friendship find(Tuple<Long, Long> aLong) {
        throw new UnsupportedOperationException("Find not implemented yet.");
    }

    @Override
    public Friendship update(Friendship entity) {
        throw new UnsupportedOperationException("Update not implemented yet.");
    }

    @Override
    public Friendship delete(Tuple<Long, Long> aLong) {
        throw new UnsupportedOperationException("Delete not implemented yet.");
    }

    @Override
    public Iterable<Friendship> findAll() {
        throw new UnsupportedOperationException("Find all not implemented yet.");
    }
}
