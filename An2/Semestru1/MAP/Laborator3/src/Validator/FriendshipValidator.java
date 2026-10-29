package Validator;

import Domain.Friendship;
import Exceptions.ValidationException;

import java.util.Objects;

public class FriendshipValidator implements Validator<Friendship> {
    /**
     * Checks if the friendship is valid.
     * @param entity the friendship to validate
     * @throws ValidationException if the two users are the same user or the date is missing;
     * the message contains all the errors
     */
    @Override
    public void validate(Friendship entity) throws ValidationException {
        if (Objects.equals(entity.getFirstfriend(), entity.getSecondfriend()))
            throw new ValidationException("A user cannot be friend with himself!");
    }
}
