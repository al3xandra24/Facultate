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
     * Retrieves the first name of the user.
     * @return the user's first name
     */
    public String getFirstName() {return firstName;}

    /**
     * Retrieves the last name of the user
     * @return the user's last name
     */
    public String getLastName() {return lastName;}

    /**
     * Change the first name of the user.
     * @param firstName_ the new first name.
     */
    public void setFirstName(String firstName_) {this.firstName = firstName_;}

    /**
     * Change the last name of the user.
     * @param lastName_ the new last name.
     */
    public void setLastName(String lastName_) {this.lastName = lastName_;}
}
