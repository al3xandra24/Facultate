package Service;

import Domain.Friendship;
import Domain.User;
import Exceptions.ServiceException;
import Repository.Repository;
import Validator.Validator;

public class FriendshipService {
    private Repository<Long, Friendship> friendshipRepository;
    private Repository<Long, User> userRepository;
    public FriendshipService(Repository<Long, Friendship> friendshipRepository_,
                             Repository<Long, User> userRepository_) {
        friendshipRepository = friendshipRepository_;
        userRepository = userRepository_;
    }
    public void addFriendship(Long id1, Long id2) {
        if (userRepository.find(id1)==null)
            throw new ServiceException("User with id "+id1+" not found");
        if (userRepository.find(id2)==null)
            throw new ServiceException("User with id "+id2+" not found");
        User user1 = userRepository.find(id1);
        User user2 = userRepository.find(id2);
        Friendship newFriendship = new Friendship(user1, user2);
        for (Friendship f: friendshipRepository.findAll()){
            if (f.equals(newFriendship))
                throw new ServiceException("Friendship already exists");
        }
        newFriendship.setID(nextID());
        friendshipRepository.save(newFriendship);
    }
    public void removeFriendship(Long id1, Long id2) {
        if (userRepository.find(id1)==null)
            throw new ServiceException("User with id "+id1+" not found");
        if (userRepository.find(id2)==null)
            throw new ServiceException("User with id "+id2+" not found");
        User user1 = userRepository.find(id1);
        User user2 = userRepository.find(id2);
    }
    public Iterable<Friendship> getAllFriendships() {
        return friendshipRepository.findAll();
    }
    public Long nextID(){
        Long maxID=0L;
        for (Friendship friendship : friendshipRepository.findAll()) {
            if (friendship.getID()>maxID){
                maxID=friendship.getID();
            }
        }
        return maxID+1;
    }
}
