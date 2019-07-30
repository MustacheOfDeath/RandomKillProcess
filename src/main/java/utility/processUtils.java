package utility;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class processUtils {
    private processUtils() {
    }

    public static List<String> listRunningProcesses() {
        List<String> processList = new ArrayList<String>();
        try {

            File file = File.createTempFile("realhowto", ".vbs");
            file.deleteOnExit();
            FileWriter fw = new java.io.FileWriter(file);

            String vbs = "Set WshShell = WScript.CreateObject(\"WScript.Shell\")\n"
                    + "Set locator = CreateObject(\"WbemScripting.SWbemLocator\")\n"
                    + "Set service = locator.ConnectServer()\n"
                    + "Set processes = service.ExecQuery _\n"
                    + " (\"select name from Win32_Process\")\n"
                    + "For Each process in processes\n"
                    + "wscript.echo process.Name \n"
                    + "Next\n"
                    + "Set WSHShell = Nothing\n";

            fw.write(vbs);
            fw.close();
            Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
            BufferedReader input =
                    new BufferedReader
                            (new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                processList.add(line);
            }
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return processList;
    }

    public static void execKill(String killApp) throws InterruptedException {
        ;
        try {
            System.out.println("Killing " + killApp);
            String killSkype = "Skype.exe";
            Runtime.getRuntime().exec("TASKKILL /F /IM " + killSkype);
            TimeUnit.SECONDS.sleep(3);
            System.out.println(killApp + " killed");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void listProcess() {
        List<String> processes = processUtils.listRunningProcesses();
        String result = "";

        Iterator<String> it = processes.iterator();
        int i = 0;
        while (it.hasNext()) {
            result += it.next() + ",        ";
            i++;
            if (i == 5) {
                result += "\n";
                i = 0;
            }
        }
        System.out.println("Running processes :\n " + result);
    }
}
