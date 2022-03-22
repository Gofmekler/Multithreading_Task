package by.maiseichyk.task3.main;

public class CustomThread extends Thread{
    public CustomThread(String login, int host) {
    }

    public void run() {
        System.out.println(currentThread().getName());
        System.out.println("Run connect 1");
    }
}
