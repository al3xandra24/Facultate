import Domain.Friendship;
import Domain.User;
import Repository.Repository;
import Service.CommunityService;
import Service.FriendshipService;
import Service.UserService;
import UI.UI;
import Validator.Validator;
import Validator.UserValidator;
import Validator.FriendshipValidator;
import Repository.UserFileRepository;
import Repository.FriendshipFileRepository;
import Repository.InMemoryRepository;

public class Main {
    public static void main(String[] args) {
        Validator<User> userValidator = new UserValidator();
        Validator<Friendship> friendshipValidator = new FriendshipValidator();
        Repository<Long, User> userRepository;
        Repository<Long, Friendship> friendshipRepository;
        String storageType="file";
        if (storageType.equals("file")){
            userRepository = new UserFileRepository("data/users.txt", userValidator);
            friendshipRepository = new FriendshipFileRepository("data/friendships.txt", friendshipValidator);
        }
        else{
            userRepository=new InMemoryRepository<Long, User>(userValidator);
            friendshipRepository=new InMemoryRepository<Long, Friendship>(friendshipValidator);
        }
        UserService userService = new UserService(userRepository, friendshipRepository);
        FriendshipService friendshipService = new FriendshipService(friendshipRepository, userRepository);
        CommunityService communityService = new CommunityService(userService, friendshipService);

        UI console = new UI(userService, friendshipService, communityService);
        console.run();
    }
}
