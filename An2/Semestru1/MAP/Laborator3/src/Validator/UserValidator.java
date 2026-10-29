package Validator;

import Domain.User;
import Exceptions.ValidationException;

public class UserValidator implements Validator<User> {
    /**
     * Checks if the user is valid.
     * @param entity the user to validate
     * @throws ValidationException if the ID is missing or a name is null, empty or only spaces;
     * the message contains all the errors
     */
    @Override
    public void validate(User entity) throws ValidationException {
        String errors="";
        if (entity.getFirstName()==null)
            errors+="First Name cannot be empty!\n";
        if (entity.getLastName()==null)
            errors+="Last Name cannot be empty!\n";
        if (!errors.isEmpty())
            throw new ValidationException(errors);
    }
}
