package javaBasic;

public class Dog extends  Annimal{
    String annimalName; // Biến Global

    public Dog(String annimalName){ // Biến Local
        super(annimalName); // Gọi tới Constructor của lớp cha Annimal
        this.annimalName = annimalName; // Biến local
    }
}
