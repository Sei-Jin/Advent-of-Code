package aoc.event.year2020.day05.binaryBoarding;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTests {
    
    @Test
    void exampleDefault() {
        final var input = "FBFBBFFRLR";
        final var solution = new Solution(input);
        assertEquals(357, solution.partOne());
    }
    
    @Test
    void example1() {
        final var input = "BFFFBBFRRR";
        final var solution = new Solution(input);
        assertEquals(567, solution.partOne());
    }
    
    @Test
    void example2() {
        final var input = "FFFBBBFRRR";
        final var solution = new Solution(input);
        assertEquals(119, solution.partOne());
    }
    
    @Test
    void example3() {
        final var input = "BBFFBBFRLL";
        final var solution = new Solution(input);
        assertEquals(820, solution.partOne());
    }
}
