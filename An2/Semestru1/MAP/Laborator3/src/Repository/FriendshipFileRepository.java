package Repository;

import Domain.Friendship;
import Domain.Tuple;
import Domain.User;
import Validator.Validator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import Exceptions.RepositoryExceptions;

public class FriendshipFileRepository extends FileAbstractRepository<Tuple<Long, Long>, Friendship> {
    /**
     * Creates the repository and loads the friendships from the file.
     * @param fileName the path of the file
     * @param validator_ the validator for friendships
     * @throws RepositoryExceptions if the file exists but cannot be read
     */
    public FriendshipFileRepository(String fileName, Validator<Friendship> validator_) {
        super(fileName, validator_);
    }
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    /**
     * Builds a friendship from a line idUser1;idUser2;friendshipDate.
     * @param attributes the three fields of the line
     * @return the friendship described by the line
     * @throws NumberFormatException if a user ID is not a number
     * @throws IndexOutOfBoundsException if the line has fewer than three fields
     */
    @Override
    protected Friendship extractEntity(List<String> attributes) {
        Long idUser1 = Long.parseLong(attributes.get(0).trim());
        Long idUser2 = Long.parseLong(attributes.get(1).trim());
        LocalDateTime date = LocalDateTime.parse(attributes.get(2).trim(), DATE_FORMAT);
        Friendship friendship = new Friendship(idUser1, idUser2, date);
        return friendship;
    }

    /**
     * Converts a friendship to a line idUser1;idUser2;friendshipDate.
     * @param entity the friendship to convert
     * @return the line for the friendship
     */
    @Override
    protected String createEntityasString(Friendship entity) {
        return entity.getFirstfriend() + ";" + entity.getSecondfriend()+
                ";"+entity.getFriendshipDate().format(DATE_FORMAT);
    }
}
