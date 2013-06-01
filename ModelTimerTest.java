import java.util.Scanner;


public class ModelTimerTest {
    public static void main(String[] args) {
        //ModelTimer t = new ModelStopwatch(0);
        ModelTimer t = new ModelCountdown(60);
        Scanner s = new Scanner(System.in);
        while(true) {
            System.out.println("Enter a command");
            String command = s.next();
            if(command.equals("s")) {
                return;
            } else if(command.equals("p")) {
                t.pauseTimer();
            } else if (command.equals("u")) {
                t.unpauseTimer();
            }
            System.out.println("Timer: " + t.getTimer());
        }
    }
}
