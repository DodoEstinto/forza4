package forza4.testers;

import forza4.IO.SavedGameColumn;
import java.util.Arrays;

/**
 *Tests the SavedGameColumn class.
 * @author Paossi Davide
 */
public class SavedGameColumnTester {

    public static void test() {
        SavedGameColumn sgc = new SavedGameColumn();
        System.out.println("Creating a new SavedGameColumn object");
        System.out.println("Expected: null");
        System.out.println("Actual: "+sgc);
        sgc.setColumn(new int[]{0,0,0,0,0,0});
        System.out.println("\nSetto la colonna con un array di 6 zeri");
        System.out.println("Expected: [0, 0, 0, 0, 0, 0]");
        System.out.println("Actual: "+sgc);
        sgc.setColumn(new int[]{4,967,0,7,0});
        System.out.println("\nSetto la colonna con un array mal formato");
        System.out.println("Expected: [0, 0, 0, 0, 0, 0]");
        System.out.println("Actual: "+sgc);
        System.out.println("\nPrendo la colonna dall'oggetto e la trasformo in stringa");
        System.out.println("Expected: [0, 0, 0, 0, 0, 0]");
        System.out.println("Actual: "+Arrays.toString(sgc.getColumn()));
    }
}
