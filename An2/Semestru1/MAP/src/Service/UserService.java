package Service;

import Domain.Friendship;
import Domain.User;
import Exceptions.ServiceException;
import Repository.Repository;
import Validator.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserService {
    private Repository<Long, User> userRepository;
    private Repository<Long, Friendship>  friendshipRepository;
    public UserService(Repository<Long, User> userRepository_,  Repository<Long, Friendship> friendshipRepository_) {
        userRepository = userRepository_;
        friendshipRepository = friendshipRepository_;
    }
    public void addUser(String name, String email){
        User user = new User(name, email);
        user.setID(getNextID());
        userRepository.save(user);
    }
    public void deleteUser(Long id){
        User userToDelete= userRepository.find(id);
        if (userToDelete == null){
            throw new ServiceException("User not found");
        }
        List<Friendship> friendshipToDelete=new ArrayList<>();
        for (Friendship friendship:friendshipRepository.findAll()){
            if (Objects.equals(friendship.getFirstfriend().getID(), userToDelete.getID()) || friendship.getSecondfriend().getID() == userToDelete.getID()){
                friendshipToDelete.add(friendship);
            }
        }
        for (Friendship friendship:friendshipToDelete){
            friendshipRepository.delete(friendship.getID());
        }
        userRepository.delete(id);
    }
    public Iterable<User> getAllUser(){
        return userRepository.findAll();
    }
    public void updateUser(Long id, String name, String email){
        User user = new User(name, email);
        user.setID(id);
        userRepository.update(user);
    }
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
