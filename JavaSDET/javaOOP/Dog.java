package javaOOP;
// Kế thừa
public class Dog extends Annimals {
    public static void main (String []args) {
        Dog dog = new Dog();
        System.out.println(dog.annimalAge);
        dog.setAnnimalName("Huskey");
        System.out.println(dog.getAnnimalName());
    }
}
