package sfu.cmpt276.asn2.models;

import jakarta.persistence.*;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid;
    private String name;
    private int weight;
    private int height;
    private String hairColor;
    private double GPA;
    public User() {
        
    }
    public User(String name, int weight, int height, String hairColor, double gPA) {
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.hairColor = hairColor;
        GPA = gPA;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public String getHairColor() {
        return hairColor;
    }
    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }
    public double getGPA() {
        return GPA;
    }
    public void setGPA(double gPA) {
        GPA = gPA;
    }
    public int getUid() {
        return uid;
    }
    public void setUid(int uid) {
        this.uid = uid;
    }
    
}
