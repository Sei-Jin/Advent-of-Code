package aoc.event.year2016.day01.noTimeForATaxicab;

import aoc.DeprecatedSolver;

import java.awt.*;
import java.util.HashSet;
import java.util.List;

/**
 * --- Day 1: No Time for a Taxicab ---
 */
public class Solution implements DeprecatedSolver
{
    /**
     * @param inputLines the puzzle input.
     * @return the number of blocks from the starting position to the Easter Bunny HQ.
     */
    @Override
    public Object partOne(List<String> inputLines)
    {
        String inputLine = inputLines.getFirst();
        
        Point point = new Point();
        Direction currentDirection = Direction.NORTH;
        
        String[] moveSequence = inputLine.split(", ");
        
        for (String move : moveSequence)
        {
            char turningDirection = move.charAt(0);
            int distance = Integer.parseInt(move.substring(1));
            
            currentDirection = changeDirection(currentDirection, turningDirection);
            
            switch (currentDirection)
            {
                case NORTH -> point.y += distance;
                case EAST -> point.x += distance;
                case SOUTH -> point.y -= distance;
                case WEST -> point.x -= distance;
            }
        }
        
        return Math.abs(point.x) + Math.abs(point.y);
    }
    
    
    /**
     * @param inputLines the puzzle input.
     * @return the distance from the starting location to the first location visited twice or {@code -1} if there
     *         were no locations visited twice.
     */
    @Override
    public Object partTwo(List<String> inputLines)
    {
        String inputLine = inputLines.getFirst();
        
        Point point = new Point();
        Direction currentDirection = Direction.NORTH;
        
        HashSet<String> pointsVisited = new HashSet<>();
        pointsVisited.add(point.toString());
        
        String[] moveSequence = inputLine.split(", ");
        
        for (String move : moveSequence)
        {
            char turningDirection = move.charAt(0);
            int distance = Integer.parseInt(move.substring(1));
            
            currentDirection = changeDirection(currentDirection, turningDirection);
            
            for (int blocksTravelled = 0; blocksTravelled < distance; blocksTravelled++)
            {
                switch (currentDirection)
                {
                    case NORTH -> point.y++;
                    case EAST -> point.x++;
                    case SOUTH -> point.y--;
                    case WEST -> point.x--;
                }
                
                if (!pointsVisited.contains(point.toString()))
                {
                    pointsVisited.add(point.toString());
                }
                else
                {
                    return Math.abs(point.x) + Math.abs(point.y);
                }
            }
        }
        
        // There were no locations visited twice
        return -1;
    }
    
    
    private static Direction changeDirection(Direction currentDirection, char turningDirection)
    {
        if (turningDirection == 'R')
        {
            currentDirection = switch (currentDirection)
            {
                case NORTH -> Direction.EAST;
                case EAST -> Direction.SOUTH;
                case SOUTH -> Direction.WEST;
                case WEST -> Direction.NORTH;
            };
        }
        
        if (turningDirection == 'L')
        {
            currentDirection = switch (currentDirection)
            {
                case NORTH -> Direction.WEST;
                case WEST -> Direction.SOUTH;
                case SOUTH -> Direction.EAST;
                case EAST -> Direction.NORTH;
            };
        }
        
        return currentDirection;
    }
    
    
    private enum Direction
    {
        NORTH, EAST, SOUTH, WEST;
    }
}
