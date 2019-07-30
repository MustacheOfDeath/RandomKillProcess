import java.util.*;

import static utility.processUtils.*;

public class BootApplication {
    public static void main(String[] args) throws InterruptedException {
        List<String> randomList = createRandomList(listRunningProcesses());
        execKill(randomList);
    }
}
