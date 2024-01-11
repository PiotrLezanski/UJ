package org.example;

import java.util.HashMap;

public class AnagramChecker
{
    public static boolean areAnagrams(String s, String t)
    {
        if(s == null || t == null)
            return false;
        
        HashMap<Character, Integer> charCount = new HashMap<>();
        for(char c : s.toCharArray())
        {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }

        for(char c : t.toCharArray())
        {
            charCount.put(c, charCount.getOrDefault(c, 0) - 1);
        }
        
        for(int val : charCount.values())
        {
            if(val != 0)
                return false;
        }
        return true;
    }
}
