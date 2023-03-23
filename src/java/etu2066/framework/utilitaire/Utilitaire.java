/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package etu2066.framework.utilitaire;

import etu2066.framework.Mapping;
import etu2066.framework.annotation.Url;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jeremie
 */
public class Utilitaire {
    
    /*
        get a list all of Existing class
    */
    public Object[] selectAllClasses() throws IOException, ClassNotFoundException {
        List<Class> classNames = new ArrayList<>();
        /* get the absolute path */
        //String rootDirectory = System.getProperty("user.dir");
        File root = new File("etu2066/framework");

        /* verify each element one by one */
        for (File file : root.listFiles()) {
            if (file.isDirectory()) {
                String packageName = file.getName().replace("/", ".");
                classNames.addAll(findClasses(file, packageName));
            }
        }

        return classNames.toArray();
    }

    /*
        find Name of class in the given directory
    */
    private static List<String> findClassesName(File directory, String packageName)
            throws ClassNotFoundException {
        List<String> classNames = new ArrayList<>();
        if (!directory.exists()) {
            return classNames;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            /* call the function again if it is a folder */
            if (file.isDirectory()) {
                classNames.addAll(findClassesName(file, packageName + "." + file.getName()));
            } 
            /* add to the list if it a .class file */
            else if (file.getName().endsWith(".class")) {
                String className = packageName + '.' + file.getName().substring(0, file.getName().length() - 6);
                classNames.add(className);
            }
        }
        return classNames;
    }

    /*
        find class in the given directory
    */
    private static List<Class> findClasses(File directory, String packageName)
    throws ClassNotFoundException {
        List<Class> classNames = new ArrayList<>();
        if (!directory.exists()) {
            return classNames;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                classNames.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                String className = packageName + '.' + file.getName().substring(0, file.getName().length() - 6);
                classNames.add(Class.forName(className));
            }
        }
        return classNames;
    }


}
