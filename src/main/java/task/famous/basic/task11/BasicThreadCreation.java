package task.famous.basic.task11;

public class BasicThreadCreation {

    public static void main(String[] args) {

        Thread thread = new Thread(() -> System.out.println("Hello Mars"));

        System.out.println("Hello World From Main");

        thread.start();

    }
}
