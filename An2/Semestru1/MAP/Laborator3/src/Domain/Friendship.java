package Domain;

import java.util.Objects;

public class Friendship extends Entity<Long> {
    private User firstfriend;
    private User secondfriend;

    /**
     * Constructor for a new Friendship between two users
     * @param fistfriend_ the first user
     * @param secondfriend_ the second user
     */
    public Friendship(User fistfriend_, User secondfriend_) {
        this.firstfriend = fistfriend_;
        this.secondfriend = secondfriend_;
    }

    /**
     * Retrieves the first user of the friendship.
     * @return the first user.
     */
    public User getFirstfriend() {
        return firstfriend;
    }

    /**
     * Retrieves the second user of the friendship.
     * @return the second user.
     */
    public User getSecondfriend() {
        return secondfriend;
    }

    /**
     * Sets the first user of the friendship.
     * @param fistfriend_ the new first user
     */
    public void setFirstfriend(User fistfriend_) {
        this.firstfriend = fistfriend_;
    }

    /**
     * Sets the second user of the friendship
     * @param secondfriend_
     */
    public void setSecondfriend(User secondfriend_) {
        this.secondfriend = secondfriend_;
    }

    /**
     * Compares this friendship to the specified object.
     * @param o   the reference object with which to compare.
     * @return true if the objects represents the same friendship; false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Friendship friendship = (Friendship) o;
        return (Objects.equals(firstfriend, friendship.firstfriend) && Objects.equals(secondfriend, friendship.secondfriend)) ||
                (Objects.equals(firstfriend, friendship.secondfriend) && Objects.equals(secondfriend, friendship.firstfriend));
    }

    /**
     * Returns a hash code value for the friendship.
     * @return the hash code value for this friendship
     */
    @Override
    public int hashCode() {
        return firstfriend.hashCode() + secondfriend.hashCode();
    }
}
