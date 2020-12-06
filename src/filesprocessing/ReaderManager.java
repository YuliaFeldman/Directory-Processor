package filesprocessing;

import java.util.LinkedList;
import sorting.*;
import filtering.*;
import java.util.Arrays;

/**
 * This class represents the process of receiving input from user and parsing it.
 */
public class ReaderManager {

    private static int readLine;
    private static LinkedList<String> log = new LinkedList<>();
    private static final int FIRST_LINE = 1;
    private static final int FILTER_TITLE_LINE_IF_SORTER_TYPE_MISSING = 3;
    private static final int REGULAR_SIZE_OF_ONE_COMMAND = 4;
    private static final int MINIMUM_SIZE_OF_ONE_COMMAND = 3;
    private static final int SIZE_OF_FILTER_DATA_LIST = 4;
    private static final int FILTER_INPUTS_SIZE = 2;
    private static final int SORTER_LINE = 2;
    private static final int SORTER_DATA_LINE = 3;
    private static final String FILTER_TITLE = "FILTER";
    private static final String SORTER_TITLE = "ORDER";
    private static final String REVERSE = "REVERSE";
    private static final String INVALID_FILTER_TITLE = "ERROR: <Filter's title is invalid> \n";
    private static final String INVALID_SORTER_TITLE = "ERROR: <Order's title is invalid> \n";
    private static final String NOT_OPERATOR = "NOT";
    private static final String WARNING_IN_LINE = "Warning in line ";
    private static final String DATA_SPLITTER = "#";

    /**
     * Default constructor.
     */
    public ReaderManager(){
        readLine = FIRST_LINE;
    }

    /**
     * This method gets the sorter command and checks its correctness.
     * @param sorterData the input sorter data.
     * @return the relevant sorter object.
     */
    private SorterManager newSorter(LinkedList<String> sorterData){
        SorterManager newSort;
        SortFactory sortFactory = new SortFactory();
        if(sorterData.size() < REGULAR_SIZE_OF_ONE_COMMAND ||
                sorterData.get(FILTER_TITLE_LINE_IF_SORTER_TYPE_MISSING).equals(FILTER_TITLE))
            return new SorterManager(new NameCompare(false));
        try{
            String[] filterInputs = sorterData.get(SORTER_DATA_LINE).split(DATA_SPLITTER, FILTER_INPUTS_SIZE);
            if(filterInputs.length == 1)
                newSort = sortFactory.sorterFactory(filterInputs[0], false);
            else if(filterInputs[1].equals(REVERSE))
                newSort = sortFactory.sorterFactory(filterInputs[0], true);
            else
                throw new ReaderSortEx(WARNING_IN_LINE + readLine);
        }
        catch (NullPointerException | ReaderSortEx ex) {
            log.add(WARNING_IN_LINE + readLine);
            newSort = new SorterManager(new NameCompare(false));
        }
        ++readLine;
        return newSort;

    }

    /**
     * This method gets the filter command and checks its correctness.
     * @param filterData the input sorter data.
     * @return the relevant filter object.
     */
    private FiltersManager newFilter(LinkedList<String> filterData){

        FiltersFactory filtersFactory = new FiltersFactory();
        FiltersManager filter;
        LinkedList<String> filterArgs = new LinkedList<>(Arrays.asList(filterData.get(1).
                split(DATA_SPLITTER, SIZE_OF_FILTER_DATA_LIST)));

        try{

            boolean notOperator;
            String notSymbol;

            if(filterArgs.size() == 0)
                notOperator = false;
            else {
                notSymbol = filterArgs.get(filterArgs.size()-1);
                if(notSymbol.equals(NOT_OPERATOR)){
                    filterArgs.remove(filterArgs.size()-1);
                    notOperator = true;
                }
                else
                    notOperator = false;
            }

            String filterType = filterArgs.get(0);
            filterArgs.remove(0);

            filter = filtersFactory.filtersBigFactory(notOperator, filterType, filterArgs);
        }

        catch (IndexOutOfBoundsException | FiltersManagerException ex){
            log.add(WARNING_IN_LINE + readLine);
            filter = new All(false);
        }
        ++readLine;
        return filter;
    }

    /**
     * This method reads the command file received from user, translates it and creates Cmd file.
     * @param cmdLines a linked list of the lines in the command file.
     * @return a list contains commands after being parsed.
     * @throws ReaderManagerException if reading did not succeed.
     */
    public LinkedList<Cmd> readFile(LinkedList<String> cmdLines) throws ReaderManagerException{

        LinkedList<Cmd> newCommands = new LinkedList<>();
        while(cmdLines.size() > 0){
            Cmd cmd = new Cmd();

            if(!cmdLines.get(0).equals(FILTER_TITLE))
                throw new ReaderManagerException(INVALID_FILTER_TITLE);
            else
                ++readLine;

            cmd.setTypeOfFilter(newFilter(cmdLines));

            if(cmdLines.size() < MINIMUM_SIZE_OF_ONE_COMMAND ||
                    !cmdLines.get(SORTER_LINE).equals(SORTER_TITLE))
                throw new ReaderManagerException(INVALID_SORTER_TITLE);
            else
                ++readLine;

            cmd.setTypeOfSorter(newSorter(cmdLines));

            for(String msg : log)
                cmd.addMessageForUser(msg);

            newCommands.add(cmd);

            for(int line = 0; line < MINIMUM_SIZE_OF_ONE_COMMAND; line++)
                cmdLines.remove(0);

            if(cmdLines.size() > 0 && !cmdLines.get(0).equals(FILTER_TITLE))
                cmdLines.remove(0);

            log = new LinkedList<>();

        }
        return newCommands;
    }

}
