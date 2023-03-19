/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package etu2066.framework.modele;

import etu2066.framework.annotation.Url;

/**
 *
 * @author jeremie
 */
public class Emp {
    String nom;
    String prenom;
    int age;
    int salaire;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSalaire() {
        return salaire;
    }

    public void setSalaire(int salaire) {
        this.salaire = salaire;
    }
    
    public Emp(String nom, String prenom, int age, int salaire){
        setNom(nom);
        setPrenom(prenom);
        setAge(age);
        setSalaire(salaire);
    }
    
    @Url(url="getEmployee")
    public void getEmp(){
        System.out.println("emp");
    }
}
