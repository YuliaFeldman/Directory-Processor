package sorting;

import java.util.Comparator;
import java.io.File;

public class SizeCompare implements Comparator<File>{

    private static final int REVERSE_PARAM = -1;
    private static final int NOT_REVERSED_PARAM = 1;
    private boolean reverseOrder;

    /**
     * Constructor
     * @param reverseOrder An indicator which indicates if a reversed order is necessary or not
     */
    public SizeCompare(boolean reverseOrder){
        this.reverseOrder = reverseOrder;
    }

    /**
     * compare two files by size
     * @param file1 first file to compare
     * @param file2 second file to compare
     * @return a negative integer if size of file1 is smaller than size of file2, or a positive integer
     * if size of file1 is bigger than size of file2
     */
    public int compare(File file1, File file2){
        int comparisonResult;
        long file1Size = file1.length();
        long file2Size = file2.length();

        if(file1Size == file2Size){
            //if sizes are equal, then compare by absolute name
            NameCompare nameCompare = new NameCompare(reverseOrder);
            return nameCompare.compare(file1, file2);
        }

        else if(file1Size > file2Size)
            comparisonResult = NOT_REVERSED_PARAM;
        else
            comparisonResult = REVERSE_PARAM;

        if(reverseOrder)
            return comparisonResult*(REVERSE_PARAM);
        return comparisonResult;
    }
}
