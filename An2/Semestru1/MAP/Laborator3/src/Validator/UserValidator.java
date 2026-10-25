package Validator;

import Domain.User;
import Exceptions.ValidationException;

public class UserValidator implements Validator<User> {
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
