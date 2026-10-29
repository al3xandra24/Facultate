package Repository;

import Domain.User;
import Exceptions.RepositoryExceptions;
import Validator.Validator;

import java.util.List;

public class UserFileRepository extends FileAbstractRepository<Long, User>{
    /**
     * Creates the repository and loads the users from the file
     * @param fileName the path of the file
     * @param validator_ the validator for users
     * @throws RepositoryExceptions if the file exists but cannot be read
     */
    public UserFileRepository(String fileName, Validator<User> validator_) {
        super(fileName, validator_);
    }

    /**
     * Builds a user from a line idUser;firstName;lastName
     * @param attributes the fields of the line in the order they appear in the file
     *@throws NumberFormatException if the ID is not a number
     *@throws IndexOutOfBoundsException if the line has fewer than three fields
     * @return the user described by the line
     */
    @Override
    protected User extractEntity(List<String> attributes) {
        Long id = Long.parseLong(attributes.get(0));
        String name = attributes.get(1);
        String email = attributes.get(2);
        User user = new User(name, email);
        user.setID(id);
        return user;
    }
    /**
     * Converts a user to a line idUser;firstName;lastName.
     * @param entity the user to convert
     * @return the line for the user
     */
    @Override
    protected String createEntityasString(User entity) {
        return entity.getID()+";"+entity.getFirstName()+";"+entity.getLastName();
    }
}
