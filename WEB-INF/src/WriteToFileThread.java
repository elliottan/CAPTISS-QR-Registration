import javax.servlet.ServletContext;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

public class WriteToFileThread extends Thread {
    private ConcurrentHashMap<String, Date> registrationtime;

    public void run() {
        try {
            Thread.sleep(10000);
            System.out.println("Hello from a thread!");
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
        }
    }

    public void doShutdown() {

    }
}