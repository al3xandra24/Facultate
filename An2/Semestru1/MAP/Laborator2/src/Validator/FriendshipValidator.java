package Validator;

import Domain.Friendship;
import Exceptions.ValidationException;

import java.util.Objects;

public class FriendshipValidator implements Validator<Friendship> {
    @Override
    public void validate(Friendship entity) throws ValidationException {
        if (Objects.equals(entity.getFirstfriend().getID(), entity.getSecondfriend().getID()))
            throw new ValidationException("A user cannot be friend with himself!");
    }
}
