package aoc.event.year2016.day01.noTimeForATaxicab;

import aoc.Runner;
import aoc.Solver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

/// --- Day 1: No Time for a Taxicab ---
public class Solution implements Solver {
    
    private static final Pattern INSTRUCTION_PATTERN = Pattern.compile("(\\w)(\\d+)");
    
    private final List<Instruction> instructions;
    
    public Solution(String input) {
        instructions = parse(input);
    }
    
    private static List<Instruction> parse(String input) {
        final var instructions = new ArrayList<Instruction>();
        final var matcher = INSTRUCTION_PATTERN.matcher(input);
        
        while (matcher.find()) {
            final var turn = Turn.of(matcher.group(1).charAt(0));
            final var distance = Integer.parseInt(matcher.group(2));
            
            instructions.add(new Instruction(turn, distance));
        }
        
        return instructions;
    }
    
    /// @return the number of blocks from the starting position to the Easter Bunny HQ.
    @Override
    public Integer partOne() {
        final var current = new Point();
        var direction = Direction.NORTH;
        
        for (final var instruction : instructions) {
            direction = direction.turn(instruction.turn);
            
            switch (direction) {
                case NORTH -> current.y += instruction.distance;
                case EAST -> current.x += instruction.distance;
                case SOUTH -> current.y -= instruction.distance;
                case WEST -> current.x -= instruction.distance;
            }
        }
        
        return Math.abs(current.x) + Math.abs(current.y);
    }
    
    /// @return the distance from the starting location to the first location visited twice or `-1` if there
    ///         were no locations visited twice.
    @Override
    public Integer partTwo() {
        final var current = new Point();
        var direction = Direction.NORTH;
        
        final var points = new HashSet<Point>();
        points.add(current);
        
        for (final var instruction : instructions) {
            direction = direction.turn(instruction.turn);
            
            for (var i = 0; i < instruction.distance; i++) {
                switch (direction) {
                    case NORTH -> current.y++;
                    case EAST -> current.x++;
                    case SOUTH -> current.y--;
                    case WEST -> current.x--;
                }
                
                if (!points.contains(current)) {
                    points.add(new Point(current.x, current.y));
                } else {
                    return Math.abs(current.x) + Math.abs(current.y);
                }
            }
        }
        
        return -1;
    }
    
    private enum Turn {
        RIGHT,
        LEFT;
        
        private static Turn of(char character) {
            return switch (character) {
                case 'R' -> RIGHT;
                case 'L' -> LEFT;
                default -> throw new IllegalStateException("Unexpected value: " + character);
            };
        }
    }
    
    private enum Direction {
        NORTH,
        EAST,
        SOUTH,
        WEST;
        
        private Direction turn(Turn turn) {
            if (turn == Turn.RIGHT) {
                return switch (this) {
                    case NORTH -> Direction.EAST;
                    case EAST -> Direction.SOUTH;
                    case SOUTH -> Direction.WEST;
                    case WEST -> Direction.NORTH;
                };
            } else if (turn == Turn.LEFT) {
                return switch (this) {
                    case NORTH -> Direction.WEST;
                    case WEST -> Direction.SOUTH;
                    case SOUTH -> Direction.EAST;
                    case EAST -> Direction.NORTH;
                };
            }
            
            return this;
        }
    }
    
    private record Instruction(Turn turn, int distance) {}
    
    private class Point {
        int x;
        int y;
        
        public Point() {
            this.x = 0;
            this.y = 0;
        }
        
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Point point)) return false;
            return x == point.x && y == point.y;
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
    
    public static void main(String[] args) {
        Runner.runAndPrint(2016, 1);
    }
}
