package year2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Day02
{
    public static void main(String[] args) throws IOException
    {
        List<String> inputLines = Files.readAllLines(Path.of("input.txt"));
        
        partOne(inputLines);
    }
   
    
    public static void partOne(List<String> inputLines)
    {
        int totalScore = 0;
        
        HashMap<Character, Choice> opponentsPossibleChoices = getOpponentsPossibleChoices();
        HashMap<Character, Choice> ourPossibleChoices = getOurPossibleChoices();
        
        for (String line : inputLines)
        {
            List<Character> moves = Arrays.stream((line.split(" ")))
                    .map(s -> s.charAt(0))
                    .toList();
            
            Choice opponentsChoice = opponentsPossibleChoices.get(moves.getFirst());
            Choice ourChoice = ourPossibleChoices.get(moves.getLast());
            
            Outcome outcome = determineWinner(opponentsChoice, ourChoice);
            
            totalScore += outcome.score + ourChoice.score;
        }
        
        System.out.println(totalScore);
    }
    
    
    public static Outcome determineWinner(Choice opponentsChoice, Choice ourChoice)
    {
        Outcome outcome;
        
        if (opponentsChoice == Choice.ROCK && ourChoice == Choice.PAPER
                || opponentsChoice == Choice.PAPER && ourChoice == Choice.SCISSORS
                || opponentsChoice == Choice.SCISSORS && ourChoice == Choice.ROCK)
        {
            outcome = Outcome.WIN;
        }
        else if (opponentsChoice == ourChoice)
        {
            outcome = Outcome.DRAW;
        }
        else
        {
            outcome = Outcome.LOSS;
        }
        
        return outcome;
    }
    
    
    private static HashMap<Character, Choice> getOpponentsPossibleChoices()
    {
        HashMap<Character, Choice> ourPossibleChoices = new HashMap<>();
        
        ourPossibleChoices.put('A', Choice.ROCK);
        ourPossibleChoices.put('B', Choice.PAPER);
        ourPossibleChoices.put('C', Choice.SCISSORS);
        
        return ourPossibleChoices;
    }
    
    
    private static HashMap<Character, Choice> getOurPossibleChoices()
    {
        HashMap<Character, Choice> ourPossibleChoices = new HashMap<>();
        
        ourPossibleChoices.put('X', Choice.ROCK);
        ourPossibleChoices.put('Y', Choice.PAPER);
        ourPossibleChoices.put('Z', Choice.SCISSORS);
        
        return ourPossibleChoices;
    }
    
    
    public enum Choice
    {
        ROCK (1),
        PAPER (2),
        SCISSORS (3);
        
        final int score;
        
        Choice (int score) {
            this.score = score;
        }
    }
    
    
    public enum Outcome
    {
        WIN (6), 
        LOSS (0), 
        DRAW (3);
        
        final int score;
        
        Outcome (int score) {
            this.score = score;
        }
    }
}
