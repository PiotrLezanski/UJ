import org.example.AnagramChecker;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AnagramCheckerTest
{
    @Test
    void areAnagrams1()
    {
        assertTrue(AnagramChecker.areAnagrams("anagram", "nagaram"));
    }

    @Test
    void areAnagrams2()
    {
        assertTrue(AnagramChecker.areAnagrams("bicycle", "celciby"));
    }

    @Test
    void areAnagrams3_capital()
    {
        assertTrue(AnagramChecker.areAnagrams("LisTEn", "LnEisT"));
    }

    @Test
    void notAnagrams1()
    {
        assertFalse(AnagramChecker.areAnagrams("rat", "car"));
    }

    @Test
    void notAnagrams2_capital()
    {
        assertFalse(AnagramChecker.areAnagrams("LisTEn", "LIsTEn"));
    }

    @Test
    void checkOneNull()
    {
        assertFalse(AnagramChecker.areAnagrams(null, "nagaram"));
    }

    @Test
    void checkOneEmpty()
    {
        assertFalse(AnagramChecker.areAnagrams("", "nagaram"));
    }

    @Test
    void checkBothEmpty()
    {
        assertTrue(AnagramChecker.areAnagrams("", ""));
    }
}
