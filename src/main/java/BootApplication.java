import java.util.*;
import static utility.processUtils.*;

public class BootApplication {

    public static void main(String[] args) throws InterruptedException {
        Random randomGenerator = new Random();
        List<String> processList = listRunningProcesses();

        int randomNum = randomGenerator.nextInt( processList.size()+1) + 1;
        execKill(processList.get(randomNum));
    }
}
