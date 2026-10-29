package Service;

import Domain.Friendship;
import Domain.Tuple;
import Domain.User;
import Exceptions.ServiceException;
import Repository.Repository;
import Validator.Validator;
import Exceptions.RepositoryExceptions;
import Exceptions.ValidationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserService {
    private Repository<Long, User> userRepository;
    private Repository<Tuple<Long, Long>, Friendship>  friendshipRepository;
    /**
     * Creates the service.
     * @param userRepository_ the repository for users
     * @param friendshipRepository_ the repository for friendships
     */
    public UserService(Repository<Long, User> userRepository_,  Repository<Tuple<Long, Long>, Friendship> friendshipRepository_) {
        userRepository = userRepository_;
        friendshipRepository = friendshipRepository_;
        removeInvalidFriendships();
    }
    /**
     * Adds a new user; the ID is generated automatically.
     * @param firstName the first name of the user
     * @param lastName the last name of the user
     * @throws ValidationException if the data is not valid (e.g. an empty name)
     * @throws RepositoryExceptions if the user cannot be saved
     */
    public void addUser(String firstName, String lastName){
        User user = new User(firstName, lastName);
        user.setID(getNextID());
        userRepository.save(user);
    }
    /**
     * Deletes a user together with all their friendships.
     * @param id the ID of the user
     * @throws IllegalArgumentException if the ID is null
     * @throws ServiceException if no user with this ID exists
     * @throws RepositoryExceptions if the data cannot be saved
     */
    public void deleteUser(Long id){
        User userToDelete= userRepository.find(id);
        if (userToDelete == null){
            throw new ServiceException("User not found");
        }
        List<Friendship> friendshipToDelete=new ArrayList<>();
        for (Friendship friendship:friendshipRepository.findAll()){
            if (Objects.equals(friendship.getFirstfriend(), userToDelete.getID()) || Objects.equals(friendship.getSecondfriend(), userToDelete.getID())){
                friendshipToDelete.add(friendship);
            }
        }
        for (Friendship friendship:friendshipToDelete){
            friendshipRepository.delete(friendship.getID());
        }
        userRepository.delete(id);
    }
    private void removeInvalidFriendships() {
        List<Friendship> toDelete = new ArrayList<>();
        for (Friendship f : friendshipRepository.findAll()) {
            if (userRepository.find(f.getFirstfriend()) == null || userRepository.find(f.getSecondfriend()) == null) {
                System.err.println("Warning: friendship " + f.getFirstfriend() + ";" + f.getSecondfriend()
                        + " refers to a missing user and was removed.");
                toDelete.add(f);
            }
        }
        for (Friendship f : toDelete) {
            friendshipRepository.delete(f.getID());
        }
    }
    /**
     * Returns all the users.
     * @return all the users
     * @throws RepositoryExceptions if the data cannot be read
     */
    public Iterable<User> getAllUser(){
        return userRepository.findAll();
    }
    public void updateUser(Long id, String name, String email){
        User user = new User(name, email);
        user.setID(id);
        userRepository.update(user);
    }
    /**
     * Computes the next free user ID.
     * @return the biggest existing ID + 1 (1 if there are no users)
     */
    private Long getNextID(){
        Long maxID=0L;
        for (User user : userRepository.findAll()) {
            if (user.getID()>maxID){
                maxID=user.getID();
            }
        }
        return maxID+1;
    }
}
