package sorting;

import java.util.Comparator;
import java.io.File;

public class NameCompare implements Comparator<File>{

    private static final int REVERSE_PARAM = -1;
    private boolean reverseOrder;

    /**
     * Constructor
     * @param reverseOrder An indicator which indicates if a reversed order is necessary or not
     */
    public NameCompare(boolean reverseOrder){
        this.reverseOrder = reverseOrder;
    }

    /**
     * Compare two files by name
     * @param file1 first file to compare
     * @param file2 second file to compare
     * @return a negative integer if name of file1 is "smaller" than name of file2, or a positive integer
     * if name of file1 is "bigger" than name of file2
     *
     */
    public int compare(File file1,  File file2){
        String file1AbsolutePath = file1.getAbsolutePath();
        String file2AbsolutePath = file2.getAbsolutePath();
        int comparisonResult = file1AbsolutePath.compareTo(file2AbsolutePath);
        if(reverseOrder)
            return comparisonResult*(REVERSE_PARAM);
        return comparisonResult;
    }
}