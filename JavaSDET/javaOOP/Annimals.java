package javaOOP;

public class Annimals {
    private String annimalName;
    protected int annimalAge = 5;

    protected String getAnnimalName() { //Getter
        return annimalName;
    }

    protected void setAnnimalName(String annimalName) { //setter
        this.annimalName = annimalName;
    }
}
