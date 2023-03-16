/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package etu2066.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 *
 * @author jeremie
 */

@Target(ElementType.METHOD)
public @interface Url {
    
    String url()default "";
    
}
