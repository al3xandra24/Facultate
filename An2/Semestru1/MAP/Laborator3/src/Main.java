import Domain.Friendship;
import Domain.Tuple;
import Domain.User;
import Exceptions.RepositoryExceptions;
import Exceptions.ValidationException;
import Repository.Repository;
import Service.CommunityService;
import Service.FriendshipService;
import Service.UserService;
import UI.UI;
import Validator.Validator;
import Validator.UserValidator;
import Validator.FriendshipValidator;
import Repository.RepositoryFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Validator<User> userValidator = new UserValidator();
        Validator<Friendship> friendshipValidator = new FriendshipValidator();
        Properties properties=new Properties();
        try (BufferedReader reader = Files.newBufferedReader(Path.of("resources/config.properties"))) {
            properties.load(reader);
        } catch (IOException e) {
            System.out.println("Could not read config.properties: " + e.getMessage());
            return;
        }

        Repository<Long, User> userRepository;
        Repository<Tuple<Long, Long>, Friendship> friendshipRepository;
        try{
            RepositoryFactory repositoryFactory = new RepositoryFactory(properties);
            userRepository=repositoryFactory.createUserRepository(userValidator);
            friendshipRepository=repositoryFactory.createFriendshipRepository(friendshipValidator);
        } catch(IllegalArgumentException e){
            System.out.println("Could not create repository: " + e.getMessage());
            return;
        } catch (RepositoryExceptions | ValidationException e){
            System.out.println("Input data error: "+  e.getMessage());
            return;
        }
        UserService userService = new UserService(userRepository, friendshipRepository);
        FriendshipService friendshipService = new FriendshipService(friendshipRepository, userRepository);
        CommunityService communityService = new CommunityService(userRepository, friendshipRepository);

        UI console = new UI(userService, friendshipService, communityService);
        console.run();
    }
}
