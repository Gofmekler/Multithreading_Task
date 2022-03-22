package by.maiseichyk.task3.main;

public class MainCheck {
    static String login;
    static int host;
    public static void main(String args[]){
        CustomThread thread[] = new CustomThread[10];
        for (int i = 0; i < 10; i++){
            thread[i] = new CustomThread(login, host);
        }
        for (int i = 0; i < 10; i++){
            thread[i].start();
        }
    }
}
