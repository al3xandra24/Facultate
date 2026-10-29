package UI;
import Domain.Friendship;
import Domain.User;
import Exceptions.RepositoryExceptions;
import Exceptions.ServiceException;
import Exceptions.ValidationException;
import Service.CommunityService;
import Service.FriendshipService;
import Service.UserService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class UI {
    private final UserService userService;
    private final FriendshipService friendshipService;
    private final CommunityService communityService;
    private final Scanner scanner;

    public UI(UserService userService, FriendshipService friendshipService, CommunityService communityService) {
        this.userService = userService;
        this.friendshipService = friendshipService;
        this.communityService = communityService;
        this.scanner=new Scanner(System.in);
    }

    private void printMenu()
    {
        System.out.println("MENU");
        System.out.println("Select an option:");
        System.out.println("1 -> Add User");
        System.out.println("2 -> Remove User");
        System.out.println("3 -> Add Friend");
        System.out.println("4 -> Remove Friend");
        System.out.println("5 -> Display number of communities");
        System.out.println("6 -> Display the most sociable community");
        System.out.println("7 -> Display all the users");
        System.out.println("8 -> Display all the friendships");
        System.out.println("9 -> Exit");
    }
    private void addUser() {
        System.out.println("Enter first name: ");
        String name=scanner.nextLine();
        System.out.println("Enter last name: ");
        String email=scanner.nextLine();
        try{
            userService.addUser(name,email);
            System.out.println("User successfully added!");
        } catch  (RepositoryExceptions ex) {
            System.out.println("Repository error: "+ex.getMessage());
        } catch (ServiceException ex) {
            System.out.println("Service error: "+ex.getMessage());
        }catch (ValidationException ex){
            System.out.println("Validation error: "+ex.getMessage());
        }catch (UnsupportedOperationException ex){
            System.out.println("UnsupportedOperation error: "+ex.getMessage());
        }
    }
    private void removeUser() {
        System.out.println("Enter ID:");
        try {
            Long id = Long.parseLong(scanner.nextLine());
            try{
                userService.deleteUser(id);
                System.out.println("User successfully deleted!");
            } catch  (RepositoryExceptions ex) {
                System.out.println("Repository error: "+ex.getMessage());
            } catch (ServiceException ex) {
                System.out.println("Service error: "+ex.getMessage());}
        } catch (NumberFormatException ex) {
            System.out.println("UI error: You must enter only digits!");
        }catch (UnsupportedOperationException ex){
            System.out.println("UnsupportedOperation error: "+ex.getMessage());
        }
    }
    private void addFriend() {
        try {
            System.out.println("Enter the ID of the first user:");
            Long id1 = Long.parseLong(scanner.nextLine());
            System.out.println("Enter the ID of the second user:");
            Long id2 = Long.parseLong(scanner.nextLine());
            System.out.println("Enter the date the friendship began:");
            LocalDateTime date = LocalDateTime.parse(scanner.nextLine().trim(),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
            friendshipService.addFriendship(id1, id2, date);
            System.out.println("Friendship successfully added!");
        } catch  (RepositoryExceptions ex) {
            System.out.println("Repository error: "+ex.getMessage());
        } catch (ServiceException ex) {
            System.out.println("Service error: "+ex.getMessage());
        } catch(ValidationException ex){
            System.out.println("Validation error: "+ex.getMessage());
        }
        catch  (NumberFormatException ex) {
            System.out.println("UI error: You must enter only digits!");
        }catch(DateTimeParseException ex){
            System.out.println("UI error: Invalid date format!Use the format YYYY-MM-DDTHH:mm");
        }catch (UnsupportedOperationException ex){
            System.out.println("UnsupportedOperation error: "+ex.getMessage());
        }
    }
    private void removeFriend() {
        try {
            System.out.println("Enter the ID of the first friend:");
            Long id1 = Long.parseLong(scanner.nextLine());
            System.out.println("Enter the ID of the second friend:");
            Long id2 = Long.parseLong(scanner.nextLine());
            friendshipService.removeFriendship(id1, id2);
            System.out.println("Friendship successfully removed!");
        } catch (RepositoryExceptions ex) {
            System.out.println("Repository error: "+ex.getMessage());
        }  catch (ServiceException ex) {
            System.out.println("Service error: "+ex.getMessage());
        } catch (NumberFormatException ex) {
            System.out.println("UI error: You must enter only digits!");
        }catch (UnsupportedOperationException ex){
            System.out.println("UnsupportedOperation error: "+ex.getMessage());
        }
    }
    private void displayAllUsers(){
        try{
        Iterable<User> users=userService.getAllUser();
        boolean isEmpty=true;
        for(User user:users){
            System.out.println("ID: "+user.getID()+"| First Name: "+user.getFirstName()+"| Last Name: "+user.getLastName());
            isEmpty=false;
        }
        if(isEmpty) {
            System.out.println("No users found!");
        }
        }catch (UnsupportedOperationException ex){
            System.out.println("UnsupportedOperation error: "+ex.getMessage());
        }
    }
    private void displayAllFriendships() {
        try{
        Iterable<Friendship> friendships=friendshipService.getAllFriendships();
        boolean isEmpty=true;
        for(Friendship friendship:friendships){
            System.out.println("ID: "+friendship.getID()+"| ID friend1:" + friendship.getFirstfriend()+"| ID friend2:" + friendship.getSecondfriend()+"| Friendship date:"+
                    friendship.getFriendshipDate());
            isEmpty=false;
        }
        if(isEmpty){
            System.out.println("No friendships found!");
        }}catch (UnsupportedOperationException ex){
            System.out.println("UnsupportedOperation error: "+ex.getMessage());
        }
    }
    private void numberOfCommunities(){
        System.out.println(communityService.getNumberOfCommunities());
    }
    private void sociableCommunity(){
        System.out.println(communityService.getBiggestCommunity());
    }
    public void run(){
        System.out.println("Hello!");
        while(true){
            printMenu();
            System.out.println("Select an option:");
            try{
                int choice= Integer.parseInt(scanner.nextLine());
                switch(choice){
                    case 1: addUser(); break;
                    case 2: removeUser(); break;
                    case 3: addFriend(); break;
                    case 4: removeFriend(); break;
                    case 5: numberOfCommunities(); break;
                    case 6: sociableCommunity(); break;
                    case 7: displayAllUsers(); break;
                    case 8: displayAllFriendships(); break;
                    case 9: System.exit(0); break;
                    default: System.out.println("Invalid choice!"); break;
                }
            } catch(NumberFormatException ex) {
                System.out.println("UI error: Your option must be a digit!");
            }
        }
    }
}
