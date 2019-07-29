import java.util.*;

public class BootApplication {

    public static void main(String[] args) throws InterruptedException {
        Random randomGenerator = new Random();

        List<String> processList = processUtils.listRunningProcesses();
        int randomNum = randomGenerator.nextInt( processList.size()+1) + 1;
        processUtils.execKill(processList.get(randomNum));
    }
}
