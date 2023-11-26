import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.example.StringPair;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringPairTest {
    private static StringPair one;
    private static StringPair oneA;
    private static StringPair two;
    private static StringPair twoA;
    
    @BeforeEach
    void setUp()
    {
        one = new StringPair("One", "Two");
        oneA = new StringPair("One", "Two");
        two = new StringPair("Three", "Four");
        twoA = new StringPair("Three", "Four");
    }

    /**
     * Test equals.
     */
    @Test
    public void testEquals() {
        assertEquals(one, oneA);
    }

    /**
     * Test constructor 
     */
    @Test
    public void testConstructor() {
        StringPair localSubject = new StringPair("One", "Two");
        assertNotNull(localSubject);
        assertTrue(null != localSubject.getRight());
        assertTrue(null != localSubject.getLeft());
    }

    /**
     * Test setting right value 
     *
     */
    @Test
    public void testRightValue() {
        one.setRight("Three");
        assertNotNull(one.getRight());
        assertEquals(one.getRight(), "Three");
    }

    /**
     * Test setting left value
     */
    @Test
    public void testLeftValue() {
        one.setLeft("Four");
        assertNotNull(one.getLeft());
        assertEquals(one.getLeft(), "Four");
    }

    /**
     * Test not equals.
     */
    @Test
    public void testNotEquals() throws Exception {
        assertNotEquals(one, two);
    }

    /**
     * Test hashCode.
     */
    @Test
    public void testHashCode() {
        one.hashCode();
        two.hashCode();
        int hash = one.hashCode();
        assertTrue(one.hashCode() == hash);
    }

    /**
     * Test setting the values.
     */
    @Test
    public void testSetValues() {
        one.setRight("ROne");
        one.setLeft("LOne");
        assertEquals("ROne", one.getRight());
        assertEquals("LOne", one.getLeft());
    }

    /**
     * Should throw an exception.
     */
    @Test
    public void testNullPointerProtection() {
        // since this will throw an exception the test will fail
        StringPair busted = new StringPair(null, "Four");
    }
}