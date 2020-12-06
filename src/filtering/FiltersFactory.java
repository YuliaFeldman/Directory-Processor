package filtering;

import java.util.LinkedList;

/**
 * This class represents the Filters factory.
 */
public class FiltersFactory {

    private static final String MORE_THAN_TWO_FILTERS_INPUTS = "a filter cannot get more than two arguments";
    private static final String MORE_THAN_ONE_FILTERS_INPUT = "this filter cannot get more than argument";
    private static final String MORE_THAN_ZERO_FILTERS_INPUTS = "this filter cannot get arguments at all";
    private static final String TWO_ARGS_FOR_BETWEEN_FILTER = "the 'between' filter must get exactly two " +
            "arguments";
    private static final String INVALID_NAME_OF_FILTER = "this filter does not exist";
    private static final String BETWEEN_FILTER_NAME = "between";
    private static final String ALL_FILTER_NAME = "all";
    private static final String CONTAINS_FILTER_NAME = "contains";
    private static final String SMALLER_THAN_FILTER_NAME = "smaller_than";
    private static final String GREATER_THAN_FILTER_NAME = "greater_than";
    private static final String EXECUTABLE_FILTER_NAME = "executable";
    private static final String HIDDEN_FILTER_NAME = "hidden";
    private static final String FILE_FILTER_NAME = "file";
    private static final String PREFIX_FILTER_NAME = "prefix";
    private static final String SUFFIX_FILTER_NAME = "suffix";
    private static final String WRITABLE_FILTER_NAME = "writable";
    private static final int MAX_INPUTS_FOR_FILTER = 2;
    private static final int INPUTS_NUM_FOR_BETWEEN_FILTER = 2;
    private static final int INPUTS_NUM_FOR_ALL_FILTER = 0;
    private static final int INPUTS_NUM_FOR_OTHER_FILTERS = 1;

    /**
     * This method is the factory of the filters. it returns a filter object.
     * @param notOperator an indicator if a reversed filter is needed or not.
     * @param nameOfFilter the name of the input filter.
     * @param filterInputs the data of the filter.
     * @return the suitable filter object.
     * @throws FiltersManagerException if an error occurred during the process of creating a new filter.
     */
    public FiltersManager filtersBigFactory(boolean notOperator, String nameOfFilter,
                                            LinkedList<String> filterInputs) throws FiltersManagerException{

        if(filterInputs.size() > MAX_INPUTS_FOR_FILTER)
            throw new FiltersManagerException(MORE_THAN_TWO_FILTERS_INPUTS);
        else if(nameOfFilter.equals(BETWEEN_FILTER_NAME)){
            if(filterInputs.size() == INPUTS_NUM_FOR_BETWEEN_FILTER){
                return new Between(Double.parseDouble(filterInputs.get(0)),
                        Double.parseDouble(filterInputs.get(1)), notOperator);
            } else {
                throw new FiltersManagerException(TWO_ARGS_FOR_BETWEEN_FILTER);
            }

        } else if(nameOfFilter.equals(ALL_FILTER_NAME)){
            if(filterInputs.size() == INPUTS_NUM_FOR_ALL_FILTER)
                return new All(notOperator);
            else
                throw new FiltersManagerException(MORE_THAN_ZERO_FILTERS_INPUTS);

        } else if(filterInputs.size() != INPUTS_NUM_FOR_OTHER_FILTERS)
            throw new FiltersManagerException(MORE_THAN_ONE_FILTERS_INPUT);

        else if(nameOfFilter.equals(SMALLER_THAN_FILTER_NAME)){
            return new SmallerThan(notOperator, Double.parseDouble(filterInputs.get(0)));
        }

        else if(nameOfFilter.equals(GREATER_THAN_FILTER_NAME))
            return new GreaterThan(notOperator, Double.parseDouble(filterInputs.get(0)));


        else if(nameOfFilter.equals(CONTAINS_FILTER_NAME))
            return new Contains(notOperator, filterInputs.get(0));

        else if(nameOfFilter.equals(FILE_FILTER_NAME))
            return new Name(notOperator, filterInputs.get(0));

        else if(nameOfFilter.equals(PREFIX_FILTER_NAME))
            return new Prefix(notOperator, filterInputs.get(0));

        else if(nameOfFilter.equals(SUFFIX_FILTER_NAME))
            return new Suffix(notOperator, filterInputs.get(0));

        else if(nameOfFilter.equals(EXECUTABLE_FILTER_NAME))
            return new Executable(notOperator, filterInputs.get(0));

        else if(nameOfFilter.equals(HIDDEN_FILTER_NAME))
            return new Hidden(notOperator, filterInputs.get(0));

        else if(nameOfFilter.equals(WRITABLE_FILTER_NAME))
            return new Writable(notOperator, filterInputs.get(0));

        else
            throw new FiltersManagerException(INVALID_NAME_OF_FILTER);

    }

}
