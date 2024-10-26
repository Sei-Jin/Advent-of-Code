package advent_of_code;

import java.util.HashMap;
import java.util.Map;

public class Puzzle
{
    private final int day;
    private final int year;
    private static final Map<Integer, Map<Integer, PuzzleSolver>> puzzleSolverMap = new HashMap<>();
    
    public Puzzle(int year, int day)
    {
        this.year = year;
        this.day = day;
    }
    
    public PuzzleSolver getPuzzleSolver()
    {
        Map<Integer, PuzzleSolver> yearMap = puzzleSolverMap.get(this.year);
        if (yearMap == null)
        {
            throw new IllegalArgumentException("No puzzles found for year: " + this.year);
        }
        
        PuzzleSolver solver = yearMap.get(this.day);
        if (solver == null)
        {
            throw new IllegalArgumentException("No puzzle found for day: " + this.day + " in year: " + this.year);
        }
        
        return solver;
    }
    
    static
    {
        Map<Integer, PuzzleSolver> year2015 = new HashMap<>();
        year2015.put(1, new advent_of_code.year_2015.__01__not_quite_lisp.Solution());
        year2015.put(2, new advent_of_code.year_2015.__02__i_was_told_there_would_be_no_math.Solution());
        year2015.put(3, new advent_of_code.year_2015.__03__perfectly_spherical_houses_in_a_vacuum.Solution());
        year2015.put(5, new advent_of_code.year_2015.__05__doesnt_he_have_intern_elves_for_this.Solution());
        year2015.put(6, new advent_of_code.year_2015.__06__probably_a_fire_hazard.Solution());
        year2015.put(7, new advent_of_code.year_2015.__07__some_assembly_required.Solution());
        
        puzzleSolverMap.put(2015, year2015);
    }
}