package org.example;

/**
 * A class to provide a key into a map.
 */
public class StringPair {
    /**
     * <code>hashCode</code> is used to cache the hash code for this class
     */
    private int hashCode = -1;

    /**
     * The right-hand side string.
     */
    private String right;

    /**
     * The left-hand side string.
     */
    private String left;

    /**
     * A simple constructor taking the right and left side strings.
     *
     * @param right
     * @param left
     * @throws IllegalArgumentException
     *             if either <code>right</code> or <code>left</code> are
     *             null.
     */
    public StringPair(String right, String left) {
        if (null == right || null == left) {
            throw new IllegalArgumentException("Should not pass null "
                    + "right = " + right + " left = " + left);
        }
        this.right = right;
        this.left = left;
    }

    /**
     * Get the right side string.
     *
     * @return String
     */
    public String getRight() {
        return right;
    }

    /**
     * Sets the right-hand side string, reinitializes the hashCode so that it is
     * recalculated the next time hashCode is called.
     *
     * @param right
     */
    public void setRight(String right) {
        hashCode = -1;
        this.right = right;
    }

    /**
     * Returns the left-hand side string.
     *
     * @return String
     */
    public String getLeft() {
        return left;
    }

    /**
     * Sets the left-hand side string, reinitializes the hashCode so that it is
     * recalculated the next time hashCode is called.
     *
     * @param left
     */
    public void setLeft(String left) {
        hashCode = -1;
        this.left = left;
    }

    /**
     * Calculates the hashCode in a way consistent with equals.
     *
     * @see java.lang.Object#hashCode()
     * @see java.util.Map
     */
    public int hashCode() {
        if (-1 == hashCode) {
            hashCode = right.hashCode() ^ left.hashCode();
        }
        return hashCode;
    }

    /**
     * Implements the equals method consistent with the hashCode method.
     *
     * @see java.lang.Object#equals(Object)
     * @see java.util.Map
     */
    @Override
    public boolean equals(Object o) {
        boolean flag = false;
        if (o instanceof StringPair) {
            StringPair other = (StringPair) o;
            if (other.hashCode() == hashCode()) {
                if (right.equals(other.getRight())
                        && left.equals(other.getLeft())) {
                    flag = true;
                }
            }
        }
        return flag;
    }
}