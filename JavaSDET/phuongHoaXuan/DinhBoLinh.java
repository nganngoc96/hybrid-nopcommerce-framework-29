package phuongHoaXuan;

public class DinhBoLinh {
    // có access modifier là Private thì chỉ được phép sủ dụng trong Class chứa nó
    //Variable/ Property : thuộc tính
    // Private: chỉ cho phep trong class này dùng
    private String espersso;

    //set thông qua hàm
    private String getEspersso (){
        return espersso;
    }

    //default: chỉ cho phép các class trong cùng package truy cập được
    String capuchino;
    String getCapuchino (){
        return capuchino;
    }
    //Protected chỉ kế thừa moi dùng được
    protected String cherry;

    protected String getCherry (){
        return cherry;
    }
    public String catimor;

    public String getCatimor () {
        return catimor;
    }

    public static String monokai;

    //set trực tiếp
    public static void main(String[] args) {
        DinhBoLinh dinhBoLinh = new DinhBoLinh();
        dinhBoLinh.espersso = "Espresso";
        System.out.println(dinhBoLinh.getEspersso());

        dinhBoLinh.capuchino ="Capuchino";
        System.out.println(dinhBoLinh.getCapuchino());

        dinhBoLinh.cherry = "Cherry";
        System.out.println(dinhBoLinh.getCherry());

        dinhBoLinh.catimor = "Catimor";
        System.out.println(dinhBoLinh.getCatimor());

    }

}
