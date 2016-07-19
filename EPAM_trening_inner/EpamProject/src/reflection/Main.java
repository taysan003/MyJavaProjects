package reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;


public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        Class c = Class.forName("java.io.InputStream");
       // String nameCl = c.getName();
        System.out.println("name - " + c.getName());
       java.lang.reflect.Constructor[] cons =c.getConstructors();
        for (int i = 0; i <cons.length ; i++) {
            System.out.println(cons[i]);
        }
        Class []intef = c.getInterfaces();
        for (int i = 0; i <intef.length ; i++) {
            System.out.println(intef[i]);
        }
        int mod = c.getModifiers();
        if (Modifier.isPrivate(c.getModifiers()))
        System.out.println("private " );

        if (Modifier.isPublic(c.getModifiers()))
        System.out.println("public " );

        if (Modifier.isStatic(c.getModifiers()))
        System.out.println("static " );

        Method [] methods= c.getMethods();
        for (int i = 0; i <methods.length ; i++) {
            System.out.println(methods[i] );
        }
       Field [] fild = c.getFields();
        for (int i = 0; i <fild.length ; i++) {
            System.out.println(fild[i] );
        }


    }

}
