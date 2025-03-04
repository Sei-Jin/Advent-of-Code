package aoc.event.year2015.day02.iWasToldThereWouldBeNoMath;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SolutionTests
{
    @Test
    void increasingDimensions()
    {
        List<String> inputLines = new ArrayList<>();
        inputLines.add("2x3x4");
        Assertions.assertEquals(58, new Solution().partOne(inputLines));
        Assertions.assertEquals(34, new Solution().partTwo(inputLines));
    }
    
    @Test
    void smallSmallestSide()
    {
        List<String> inputLines = new ArrayList<>();
        inputLines.add("1x1x10");
        Assertions.assertEquals(43, new Solution().partOne(inputLines));
        Assertions.assertEquals(14, new Solution().partTwo(inputLines));
    }
    
    @Test
    void evenDimensions()
    {
        List<String> inputLines = new ArrayList<>();
        inputLines.add("5x5x5");
        Assertions.assertEquals(175, new Solution().partOne(inputLines));
        Assertions.assertEquals(145, new Solution().partTwo(inputLines));
    }
}
