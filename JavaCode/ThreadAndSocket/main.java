import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import ThreadPool.MyThreadFactor;

public class main {
    public static void main(String[] args) {
        ExecutorService CThread = Executors.newCachedThreadPool(new MyThreadFactor("线程"));
        for (int i = 0; i < 10; i++) {
            CThread.submit(() ->
            {

            });
        }
    }
}
