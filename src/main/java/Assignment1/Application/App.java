package Assignment1.Application;


import java.io.IOException;

public class App {
    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            try {
                new GUI();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        t1.start();

           }
}

