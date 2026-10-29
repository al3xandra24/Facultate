package Validator;

import Exceptions.ValidationException;

public interface Validator<T> {
    /**
     * Checks if the entity is valid.
     * @param entity the entity to validate
     * @throws ValidationException if the entity is not valid; the message contains all the errors
     */
    void validate(T entity) throws ValidationException;
}
