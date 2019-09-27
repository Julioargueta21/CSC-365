public class Main {

    public static void main(String[] args) {
        Thread ui = new Thread(new Runnable() {
            @Override
            public void run() {
               new GUI();
            }
        });

    ui.start();
    }
}
