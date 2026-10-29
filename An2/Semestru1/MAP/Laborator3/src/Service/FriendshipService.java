package Service;

import Domain.Friendship;
import Domain.Tuple;
import Domain.User;
import Exceptions.ServiceException;
import Repository.Repository;
import Validator.Validator;
import Exceptions.RepositoryExceptions;
import Exceptions.ValidationException;

import java.time.LocalDateTime;

public class FriendshipService {
    private Repository<Tuple<Long, Long>, Friendship> friendshipRepository;
    private Repository<Long, User> userRepository;

    /**
     * Creates the service.
     * @param friendshipRepository_ the repository for friendships
     * @param userRepository_ the repository for users
     */
    public FriendshipService(Repository<Tuple<Long, Long>, Friendship> friendshipRepository_,
                             Repository<Long, User> userRepository_) {
        friendshipRepository = friendshipRepository_;
        userRepository = userRepository_;
    }
    /**
     * Adds a friendship between two existing users.
     * @param id1 the ID of the first user
     * @param id2 the ID of the second user
     * @param date the date and time when the friendship started
     * @throws IllegalArgumentException if one of the IDs is null
     * @throws ServiceException if one of the users does not exist or the friendship already exists
     * @throws ValidationException if the friendship is not valid (e.g. a user with himself)
     * @throws RepositoryExceptions if the friendship cannot be saved
     */
    public void addFriendship(Long id1, Long id2, LocalDateTime date) {
        if (userRepository.find(id1)==null)
            throw new ServiceException("User with id "+id1+" not found");
        if (userRepository.find(id2)==null)
            throw new ServiceException("User with id "+id2+" not found");
        Friendship newFriendship = new Friendship(id1, id2, date);
        for (Friendship f: friendshipRepository.findAll()){
            if (f.equals(newFriendship))
                throw new ServiceException("Friendship already exists");
        }
        friendshipRepository.save(newFriendship);
    }
    /**
     * Removes the friendship between two users; the order of the IDs does not matter.
     * @param id1 the ID of the first user
     * @param id2 the ID of the second user
     * @throws IllegalArgumentException if one of the IDs is null
     * @throws ServiceException if one of the users or the friendship does not exist
     * @throws RepositoryExceptions if the data cannot be saved
     */
    public void removeFriendship(Long id1, Long id2) {
        if (userRepository.find(id1)==null)
            throw new ServiceException("User with id "+id1+" not found");
        if (userRepository.find(id2)==null)
            throw new ServiceException("User with id "+id2+" not found");
        Tuple<Long, Long> id = Friendship.idOf(id1, id2);
        if (friendshipRepository.find(id) == null)
            throw new ServiceException("Friendship between " + id1 + " and " + id2 + " does not exist");
        friendshipRepository.delete(id);
    }
    /**
     * Returns all the friendships.
     * @return all the friendships
     * @throws RepositoryExceptions if the data cannot be read
     */
    public Iterable<Friendship> getAllFriendships() {
        return friendshipRepository.findAll();
    }
}
