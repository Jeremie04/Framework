/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package etu2066.framework;

import etu2066.framework.annotation.Url;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jeremie
 */
public class Mapping {
    private String className;
    private String method;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
    
    public Mapping(String className, String method){
        this.setClassName(className);
        this.setMethod(method);
    }
    
    public Mapping(){
        
    }
    
    /*
        Get the list of mapping(class, method) from a given Class
    */
    public Mapping[] getMapping(Class cl){
        List<Mapping> list = new ArrayList<>();
        String className = cl.getName();
        Field[] fields = cl.getDeclaredFields();
        for(int i=0; i<fields.length; i++){
            if(fields[i].getAnnotations()!= null){
                Mapping m = new Mapping(className, fields[i].getAnnotation(Url.class).url());
                list.add(m);
            }
        }
        return list.toArray(Mapping[]::new);
    }
}
