package aoc.event.year2016.day06.signalsAndNoise;

import aoc.Runner;
import aoc.Solver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution implements Solver {
    
    private final List<String> lines;
    
    public Solution(String input) {
        lines = input.lines().toList();
    }
    
    private static Map<Character, Integer> calculateCharacterCounts(
        List<String> lines,
        int index
    ) {
        Map<Character, Integer> characterCounts = new HashMap<>();
        
        for (String line : lines) {
            char character = line.charAt(index);
            int count = characterCounts.getOrDefault(character, 0) + 1;
            
            characterCounts.put(character, count);
        }
        
        return characterCounts;
    }
    
    @Override
    public Object partOne() {
        StringBuilder messageBuilder = new StringBuilder();
        
        for (int index = 0; index < lines.getFirst().length(); index++) {
            Map<Character, Integer> characterCounts = calculateCharacterCounts(lines, index);
            char mostFrequentCharacter = calculateMostFrequentCharacter(characterCounts);
            
            messageBuilder.append(mostFrequentCharacter);
        }
        
        return messageBuilder.toString();
    }
    
    private static char calculateMostFrequentCharacter(Map<Character, Integer> characterCounts) {
        char mostFrequentCharacter = 0;
        int maxCount = 0;
        
        for (char character : characterCounts.keySet()) {
            int count = characterCounts.get(character);
            
            if (count > maxCount) {
                mostFrequentCharacter = character;
                maxCount = count;
            }
        }
        
        return mostFrequentCharacter;
    }
    
    @Override
    public Object partTwo() {
        StringBuilder messageBuilder = new StringBuilder();
        
        for (int index = 0; index < lines.getFirst().length(); index++) {
            Map<Character, Integer> characterCounts = calculateCharacterCounts(lines, index);
            char leastFrequentCharacter = calculateLeastFrequentCharacter(characterCounts);
            
            messageBuilder.append(leastFrequentCharacter);
        }
        
        return messageBuilder.toString();
    }
    
    private char calculateLeastFrequentCharacter(Map<Character, Integer> characterCounts) {
        char leastFrequentCharacter = 0;
        int minCount = Integer.MAX_VALUE;
        
        for (char character : characterCounts.keySet()) {
            int count = characterCounts.get(character);
            
            if (count < minCount) {
                leastFrequentCharacter = character;
                minCount = count;
            }
        }
        
        return leastFrequentCharacter;
    }
    
    public static void main(String[] args) {
        Runner.runAndPrint(2016, 6);
    }
}
