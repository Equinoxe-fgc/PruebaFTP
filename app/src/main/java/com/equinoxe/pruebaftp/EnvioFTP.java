package com.equinoxe.pruebaftp;

import android.os.Build;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EnvioFTP extends Thread{
    String sAddr;
    String sFicheroLocal;
    int iPort;

    public EnvioFTP(String sAddr, int iPort, String sFicheroLocal) {
        this.sAddr = sAddr;
        this.sFicheroLocal = sFicheroLocal;
        this.iPort = iPort;
    }

    @Override
    public void run() {
        try  {
            //byte []addr = {(byte)192, (byte)168, (byte)177, (byte)229};
            //byte []addr = {(byte)192, (byte)168, (byte)1, (byte)34};
            byte []bytesEjemplo = {(byte)150, (byte)214, (byte)59, (byte)23};
            FTPClient client = new FTPClient();

                //sFicheroLocal = Environment.getExternalStorageDirectory().getAbsolutePath() + "/TempFile.txt";
            FileOutputStream fOutput = new FileOutputStream(sFicheroLocal);
            fOutput.write(bytesEjemplo);
            fOutput.close();

                //InetAddress iAddr = InetAddress.getByAddress(addr);
            InetAddress iAddr = InetAddress.getByName(sAddr);
            client.connect(iAddr, iPort);

            if (client.login("Equinoxe", "Frabela_1")) {
                client.enterLocalPassiveMode();
                client.setFileType(FTP.BINARY_FILE_TYPE);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.UK);
                String sFicheroRemoto = Build.MODEL + "_" + sdf.format(new Date()) + ".txt";

                FileInputStream fInput = new FileInputStream(sFicheroLocal);
                client.storeFile(sFicheroRemoto, fInput);

                fInput.close();
            }

            client.logout();
            client.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
