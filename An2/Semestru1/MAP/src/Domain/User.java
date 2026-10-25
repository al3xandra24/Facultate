package Domain;

public class User extends Entity<Long> {
    private int ID;
    private String firstName;
    private String lastName;

    /**
     * Constructor for a new User
     * @param firstName_ the first name of the user
     * @param lastName_ the email of the user
     */
    public User (String firstName_, String lastName_) {
        this.firstName = firstName_;
        this.lastName = lastName_;
    }

    /**
     * Retrieves the name of the user.
     * @return the user's name
     */
    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public void setFirstName(String firstName_) {this.firstName = firstName_;}
    public void setLastName(String lastName_) {this.lastName = lastName_;}
}
