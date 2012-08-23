package blah;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Arrays;

public class Main {

    public static void main(String... args) throws ClassNotFoundException, IllegalArgumentException, SecurityException, IllegalAccessException, NoSuchFieldException, MalformedURLException {
        StaticFieldResetter sfr = new StaticFieldResetter("test.Test", new File(System.getProperty("user.dir")).getAbsolutePath() + "/test_cp/");

        Class<?> theClass = sfr.getTheClass();
        
        System.out.println("Before Changes");
        printValues(theClass);
        
        int[] xs = (int[]) theClass.getField("xs").get(null);
        xs[2] = 999;
        
        theClass.getField("fire").set(null, "extinguished");
        
        System.out.println("After Changes");
        printValues(theClass);
        
        
        sfr.resetTheClassesStaticFields();
        
        System.out.println("After resetting");
        printValues(theClass);

    }

    private static void printValues(Class<?> testClass)
            throws IllegalArgumentException, SecurityException,
            IllegalAccessException, NoSuchFieldException {
        System.out.println(Arrays.toString((int[]) testClass.getField("xs")
                .get(null)));
        System.out.println((String) testClass.getField("fire").get(null));
    }
}
