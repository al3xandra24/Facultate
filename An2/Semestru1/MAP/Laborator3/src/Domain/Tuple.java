package Domain;

import java.util.Objects;

public class Tuple<E1, E2> {
    private final E1 left;
    private final E2 right;

    /**
     * Creates a new immutable pair of two values
     * @param left_ the first value
     * @param right_ the second value
     */
    public Tuple(E1 left_, E2 right_) {
        this.left = left_;
        this.right = right_;
    }

    /**
     * Retrieves the first value of the pair
     * @return the first value
     */
    public E1 getLeft() { return left; }

    /**
     * Retrieves the second value of the pair
     * @return the second value
     */
    public E2 getRight() { return right; }

    /**
     * Returns a text representation of the pair
     * @return the pair in form (left, right)
     */
    @Override
    public String toString() {
        return "(" + left + ", " + right + ")";
    }

    /**
     * Compares this tuple to the specified object.
     * @param other   the reference object with which to compare.
     * @return true if other is a Tuple with equal first values and equal second values; false otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (this ==other ) return true;
        if (other == null || getClass()!= other.getClass()) return false;
        Tuple<?, ?> tuple = (Tuple<?, ?>) other;
        return Objects.equals(left, tuple.left) && Objects.equals(right, tuple.right);
    }

    /**
     * Returns a hash code computed from both values, consistent with equals.
     * @return the hash code of this tuple
     */
    @Override
    public int hashCode() { return Objects.hash(left, right); }
}
