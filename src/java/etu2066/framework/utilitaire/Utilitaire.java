/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package etu2066.framework.utilitaire;

import etu2066.framework.Mapping;
import etu2066.framework.annotation.Url;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.lang.Class;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.servlet.ServletContext;


/**
 *
 * @author jeremie
 */
public class Utilitaire {
    
    /*
        get a list all of Existing class
    */

    public Class[] getClasses(InputStream configInputStream) throws ClassNotFoundException, SAXException, IOException, ParserConfigurationException, Exception {
        ArrayList<Class> classes = new ArrayList<Class>();
        // Get a File object for the package
        File directory = null;
        String pckgname = getConfigPackageValue(configInputStream);
        try {
            ClassLoader cld = Thread.currentThread().getContextClassLoader();
            if (cld == null) {
                throw new ClassNotFoundException("Can't get class loader.");
            }
            String path = pckgname.replace('.', '/');
            java.net.URL resource = cld.getResource(path);
            if (resource == null) {
                throw new ClassNotFoundException("No resource for " + path);
            }
            directory = new File(resource.getFile());
        }
        catch (NullPointerException x) {
            throw new ClassNotFoundException(pckgname + " (" + directory + ") does not appear to be a valid package");
        }
        if (directory.exists()) {
            // Get the list of the files contained in the package
            File[] files = directory.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].getName().endsWith(".class")) {
                    // removes the .class extension
                    classes.add(Class.forName(pckgname + '.' + files[i].getName().substring(0, files[i].getName().length() - 6)));
                }else if(files[i].isDirectory()){
                    findClasses(files[i], pckgname + '.');
                }
            }
        }
        else {
            throw new ClassNotFoundException(pckgname + " does not appear to be a valid package");
        }
        Class[] classesA = new Class[classes.size()];
        classes.toArray(classesA);
        return classesA;
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


    private String getConfigPackageValue(InputStream inputStream) throws Exception{
        // Créer un parseur de document XML
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Analyser le document XML
        Document document = builder.parse(inputStream);

        // Fermer le flux d'entrée
        inputStream.close();

        // Récupérer les éléments du document XML
        Element root = document.getDocumentElement();
        NodeList node = root.getElementsByTagName("classPackage");

        // Prendre l'élement dans package
        String packageName = node.item(0).getTextContent();
        return packageName;       
    }
}
