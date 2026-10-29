package Repository;

import Domain.Friendship;
import Domain.Tuple;
import Domain.User;
import Validator.Validator;
import Exceptions.RepositoryExceptions;
import java.util.Properties;

public class RepositoryFactory {
    private final Properties properties;
    /**
     * Creates a factory that uses the given configuration.
     * @param properties_ the configuration read from config.properties
     */
    public RepositoryFactory(Properties properties_) {
        this.properties = properties_;
    }
    public RepositoryType getType() {
        String value = properties.getProperty("repository.type");
        if (value == null) {
            throw new IllegalArgumentException("The key repository.type is missing from the properties file");
        }
        return RepositoryType.valueOf(value.trim().toUpperCase());
    }
    private String getRequired(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new IllegalArgumentException("The key " + key + " is missing from the properties file");
        }
        return value.trim();
    }
    /**
     * Creates the user repository for the configured persistence type.
     * @param validator the validator for users
     * @return the user repository
     * @throws IllegalArgumentException if repository.type or a key needed for this type is missing or invalid
     * @throws RepositoryExceptions if the users file exists but cannot be read
     */
    public Repository<Long, User> createUserRepository(Validator<User> validator) {
        switch (getType()) {
            case MEMORY:
                return new InMemoryRepository<Long, User>(validator);
            case FILE:
                return new UserFileRepository(getRequired("file.users"), validator);
            case DATABASE:
                return new UserDBRepository(validator, getRequired("db.url"),  getRequired("db.user"), getRequired("db.password"));
            default:
                throw new IllegalArgumentException("The type " + getType() + " is not supported");
        }
    }
    /**
     * Creates the friendship repository for the configured persistence type.
     * @param validator the validator for friendships
     * @return the friendship repository
     * @throws IllegalArgumentException if repository.type or a key needed for this type is missing or invalid
     * @throws RepositoryExceptions if the friendships file exists but cannot be read
     */
    public Repository<Tuple<Long, Long>, Friendship>  createFriendshipRepository(Validator<Friendship> validator) {
        switch (getType()) {
            case MEMORY:
                return new InMemoryRepository<Tuple<Long, Long>, Friendship>(validator);
            case FILE:
                return new FriendshipFileRepository(getRequired("file.friendships"), validator);
            case DATABASE:
                return new FriendshipDBRepository(validator, getRequired("db.url"),  getRequired("db.user"), getRequired("db.password"));
            default:
                throw new IllegalArgumentException("The type " + getType() + " is not supported");
        }
    }
}
