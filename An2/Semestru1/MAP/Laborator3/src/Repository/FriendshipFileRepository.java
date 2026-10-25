package Repository;

import Domain.Friendship;
import Domain.User;
import Validator.Validator;

import java.util.List;

public class FriendshipFileRepository extends FileAbstractRepository<Long, Friendship> {
    public FriendshipFileRepository(String fileName, Validator<Friendship> validator_) {
        super(fileName, validator_);
    }

    @Override
    protected Friendship extractEntity(List<String> attributes) {
        Long friendshipID = Long.valueOf(attributes.get(0));
        Long idUser1 = Long.parseLong(attributes.get(1));
        Long idUser2 = Long.parseLong(attributes.get(2));
        User user1 = new User("", "");
        user1.setID(idUser1);
        User user2 = new User("", "");
        user2.setID(idUser2);
        Friendship friendship = new Friendship(user1, user2);
        friendship.setID(friendshipID);
        return friendship;
    }

    @Override
    protected String createEntityasString(Friendship entity) {
        return entity.getID() + ";" + entity.getFirstfriend().getID() + ";" + entity.getSecondfriend().getID();
    }
}
