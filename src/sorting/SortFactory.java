package sorting;

import filesprocessing.ReaderSortEx;
import java.util.Objects;

/**
 * This class represents a factory of SorterManager class objects
 */
public class SortFactory {

    //String representations of all sort types
    private static final String sortByName = "abs";
    private static final String sortBySize = "size";
    private static final String sortByType = "type";

    /**
     * This methos creates a SorterManager class object according to given values
     * @param sortType type of order
     * @param reverseOrder an indicator which indicates if a reversed order is needed or not
     * @return SorterManager class object
     * @throws ReaderSortEx when this process fails
     */
    public SorterManager sorterFactory(String sortType, boolean reverseOrder) throws ReaderSortEx{
        if(Objects.equals(sortType,sortByName)){
            NameCompare nameComparator = new NameCompare(reverseOrder);
            return new SorterManager(nameComparator);
        }
        if(Objects.equals(sortType,sortBySize)){
            SizeCompare sizeComparator = new SizeCompare(reverseOrder);
            return new SorterManager(sizeComparator);
        }
        if(Objects.equals(sortType,sortByType)){
            TypeCompare typeComparator = new TypeCompare(reverseOrder);
            return new SorterManager(typeComparator);
        }

        //if sort type is illegal than throw exception
        throw new ReaderSortEx();
    }
}
