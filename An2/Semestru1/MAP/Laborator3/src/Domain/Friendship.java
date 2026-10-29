package Domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Friendship extends Entity<Tuple<Long, Long>> {
    private LocalDateTime friendshipDate;

    /**
     * Creates a friendship between two users
     * @param firstfriend_ - the ID of the first user
     * @param secondfriend_ - the ID of the second user
     * @param friendshipDate_ - the date and time when the friendship started
     */
    public Friendship(Long firstfriend_, Long secondfriend_, LocalDateTime friendshipDate_) {
        setID(idOf(firstfriend_, secondfriend_));
        this.friendshipDate = friendshipDate_;
    }

    /**
     * Builds the ID of the friendship between two users, with the smaller ID first
     * @param id1 - the ID of the first user
     * @param id2 - the ID of the second user
     * @return the tuple (min(id1, id2), max(id1, id2))
     * @throws IllegalArgumentException if one of the IDs is null
     */
    public static Tuple<Long, Long> idOf(Long id1, Long id2) {
        if (id1 == null || id2 == null)
            throw new IllegalArgumentException("User IDs must not be null");
        return id1 <= id2 ? new Tuple<>(id1, id2) : new Tuple<>(id2, id1);
    }

    /**
     * Retrieves the ID of the first user of friendship.
     * @return the smaller of the two user IDs
     */
    public Long getFirstfriend() {return getID().getLeft();
    }

    /**
     * Retrieves the ID of the second user of friendship.
     * @return the bigger of the two user IDs
     */
    public Long getSecondfriend() {
        return getID().getRight();}

    /**
     * Retrieves the date and time when the friendship started.
     * @return the date of the friendship
     */
    public LocalDateTime getFriendshipDate() {return friendshipDate;}
}
