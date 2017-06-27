package com.zacharyfox.rmonitor.utils;

import java.io.*;
import java.util.Properties;

/**
 * Created by dcbasso on 08/06/17.
 */
public class Settings {

    private static final String CONFIG_FOLDER = ".rmonitor";
    private static final String CONFIG_FILE = "rmonitorleaderboard.config";

    private static final String KEY_SERVER_IP = "remoteServerIp";
    private static final String KEY_SERVER_PORT = "remoteServerPort";

    private final String folderPath = System.getProperty("user.home") + File.separator + CONFIG_FOLDER + File.separator;
    private final String fileName = folderPath + CONFIG_FILE;

    private static Settings instance;
    private String remoteServerIp = "127.0.0.1";
    private String remoteServerPort = "8080";

    private Settings() {
        load();
    }

    public String getRemoteServerIp() {
        return remoteServerIp;
    }

    public void setRemoteServerIp(String remoteServerIp) {
        this.remoteServerIp = remoteServerIp;
    }

    public String getRemoteServerPort() {
        return remoteServerPort;
    }

    public void setRemoteServerPort(String remoteServerPort) {
        this.remoteServerPort = remoteServerPort;
    }

    //TODO: Check if it works on Windows and Mac os.
    public Boolean save() {
        Boolean saved = false;

        Properties properties = new Properties();
        OutputStream outputStream = null;
        try {
            properties.setProperty(KEY_SERVER_IP, this.getRemoteServerIp());
            properties.setProperty(KEY_SERVER_PORT, this.getRemoteServerPort());
            File propertiesFile = new File(this.fileName);
            File propertiesFolder = new File(this.folderPath);
            if (!propertiesFolder.exists()) {
                propertiesFolder.mkdirs();
                if (!propertiesFile.exists()) {
                    propertiesFile.createNewFile();
                }
            }
            properties.store(new FileOutputStream(this.fileName), null);
            saved = true;
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return saved;
    }

    public Boolean load() {
        Boolean read = false;
        Properties properties = new Properties();
        InputStream inputStream = null;
        try {
            File propertiesFile = new File(this.fileName);
            if (propertiesFile.exists()) {
                inputStream = new FileInputStream(fileName);
                properties.load(inputStream);
                this.setRemoteServerIp(properties.get(KEY_SERVER_IP).toString());
                this.setRemoteServerPort(properties.get(KEY_SERVER_PORT).toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return read;
    }

    public static Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }

}
