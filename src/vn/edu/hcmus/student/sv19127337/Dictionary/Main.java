package vn.edu.hcmus.student.sv19127337.Dictionary;

import java.io.IOException;
import java.util.Vector;

public class Main {

    public static void main(String[] args) throws IOException {
        Dictionary d = new Dictionary();
        String path = "D:\\Code Java\\Dict_19127337\\src\\vn\\edu\\hcmus\\student\\sv19127337\\Dictionary\\slang.txt";
        d = d.readFromFile(path);

        UI ui = new UI();
        ui.createAndShowGUI(d);
    }
}
