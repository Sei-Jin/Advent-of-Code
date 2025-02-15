package aoc.event.year2022.day06.tuningTrouble;

import aoc.Runner;
import aoc.DeprecatedSolver;

import java.util.List;

public class Solution implements DeprecatedSolver
{
    public static void main(String[] args)
    {
        Runner.runAndPrint(2022, 6);
    }
    
    /// Finds the position of the first marker.
    ///
    /// The position of the first marker is calculated by finding the first substring of distinct
    /// characters with length `windowSize`.
    ///
    /// @param line finds the position of the first marker.
    /// @param windowSize the length of distinct characters.
    /// @return the position of the first marker.
    private static int findFirstMarker(String line, int windowSize)
    {
        for (int i = 0; i < line.length(); i++)
        {
            String substring = line.substring(i, i + windowSize);
            boolean containsDuplicate = containsDuplicate(substring);
            
            if (!containsDuplicate)
            {
                return i + windowSize;
            }
        }
        
        throw new IllegalStateException("No markers were detected.");
    }
    
    /// Determines if the string contains a duplicate character.
    ///
    /// @param string a string.
    /// @return true if the string contains a duplicate character, or false otherwise.
    private static boolean containsDuplicate(String string)
    {
        for (int i = 0; i < string.length() - 1; i++)
        {
            for (int j = i + 1; j < string.length(); j++)
            {
                if (string.charAt(i) == string.charAt(j))
                {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    /// Calculates the position of the first start-of-packet marker.
    ///
    /// The start-of-packet marker starts after the first substring of length 4 with all distinct
    /// characters.
    ///
    /// @param puzzleInput the puzzle input. The puzzle input should contain a single string.
    /// @return the position of the first start-of-packet marker.
    @Override
    public Object partOne(List<String> puzzleInput)
    {
        String line = puzzleInput.getFirst();
        return findFirstMarker(line, 4);
    }
    
    /// Calculates the position of the first start-of-message marker.
    ///
    /// The start-of-message marker starts after the first substring of length 14 with all distinct
    /// characters.
    ///
    /// @param puzzleInput the puzzle input. The puzzle input should contain a single string.
    /// @return the position of the first start-of-message marker.
    @Override
    public Object partTwo(List<String> puzzleInput)
    {
        String line = puzzleInput.getFirst();
        return findFirstMarker(line, 14);
    }
}
