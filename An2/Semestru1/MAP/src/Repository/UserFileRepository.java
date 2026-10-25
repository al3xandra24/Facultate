package Repository;

import Domain.User;
import Validator.Validator;

import java.util.List;

public class UserFileRepository extends FileAbstractRepository<Long, User>{
    public UserFileRepository(String fileName, Validator<User> validator_) {
        super(fileName, validator_);
    }

    @Override
    protected User extractEntity(List<String> attributes) {
        Long id = Long.parseLong(attributes.get(0));
        String name = attributes.get(1);
        String email = attributes.get(2);
        User user = new User(name, email);
        user.setID(id);
        return user;
    }

    @Override
    protected String createEntityasString(User entity) {
        return entity.getID()+";"+entity.getFirstName()+";"+entity.getLastName();
    }
}
