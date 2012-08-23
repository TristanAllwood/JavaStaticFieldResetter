package blah;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.Comparator;


public class StaticFieldResetter {

    private final Class<?> base;
    private final String className;
    private final String extraClassPath;
    
    public StaticFieldResetter(String className, String extraClassPath) throws ClassNotFoundException, MalformedURLException {
        ClassLoader cl = URLClassLoader.newInstance(new URL[]{new URL("file", "", extraClassPath)});
        this.className = className;
        this.base = cl.loadClass(className);
        this.extraClassPath = extraClassPath;
    }
    
    public Class<?> getTheClass() {
        return base;
    }
    
    public void resetTheClassesStaticFields() throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException, MalformedURLException {
        ClassLoader cl = URLClassLoader.newInstance(new URL[]{new URL("file", "", extraClassPath)});
        Class<?> next = cl.loadClass(className);
        
        Field[] nextFields = next.getFields();
        
        Comparator<Field> cmp = new Comparator<Field>() {
            @Override
            public int compare(Field o1, Field o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };
        
        Field[] baseFields = base.getFields();

        Arrays.sort(baseFields, cmp);
        Arrays.sort(nextFields, cmp);
    
        for(int i = 0 ; i < baseFields.length ; i++) {
            if(Modifier.isStatic(baseFields[i].getModifiers())) {
                baseFields[i].set(null, nextFields[i].get(null));
            }
        }
        
    }
    
    
}
