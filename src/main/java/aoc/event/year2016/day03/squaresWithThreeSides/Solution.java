package aoc.event.year2016.day03.squaresWithThreeSides;

import aoc.Runner;
import aoc.Solver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution implements Solver {
    
    private final List<List<Integer>> numberLists;
    
    public Solution(String input) {
        numberLists = parse(input);
    }
    
    private static List<List<Integer>> parse(String input) {
        final var numberLists = new ArrayList<List<Integer>>();
        
        for (final var line : input.lines().toList()) {
            final var numbers = Arrays.stream(line.trim().split("\\s+"))
                .map(Integer::parseInt)
                .toList();
            
            numberLists.add(numbers);
        }
        
        return numberLists;
    }
    
    /// Determines whether a triangle could be formed with the given `sideLengths` or not. A triangle is
    /// considered possible when the sum of any two sides are larger than the remaining side.
    ///
    /// @param sideLengths the side lengths of a triangle.
    /// @return true if the side lengths are possible, or false otherwise.
    private static boolean possibleTriangle(List<Integer> sideLengths) {
        if (sideLengths.size() != 3) {
            throw new IllegalArgumentException("There must be exactly 3 sides to form a triangle.");
        }
        
        int side1 = sideLengths.get(0);
        int side2 = sideLengths.get(1);
        int side3 = sideLengths.get(2);
        
        return (side1 + side2 > side3) &&
            (side1 + side3 > side2) &&
            (side2 + side3 > side1);
    }

    /// @return the number of possible triangles.
    @Override
    public Object partOne() {
        int possibleTriangles = 0;
        
        for (final var list : numberLists) {
            if (possibleTriangle(list)) {
                possibleTriangles++;
            }
        }
        
        return possibleTriangles;
    }

    /// @return the number of possible triangles in the vertical groups.
    @Override
    public Object partTwo() {
        int possibleTriangles = 0;
        
        for (int row = 0; row < numberLists.size(); row += 3) {
            for (int column = 0; column < numberLists.getFirst().size(); column++) {
                List<Integer> triangle = Arrays.asList(
                    numberLists.get(row).get(column),
                    numberLists.get(row + 1).get(column),
                    numberLists.get(row + 2).get(column)
                );
                
                if (possibleTriangle(triangle)) {
                    possibleTriangles++;
                }
            }
        }
        
        return possibleTriangles;
    }
    
    public static void main(String[] args) {
        Runner.runAndPrint(2016, 3);
    }
}
