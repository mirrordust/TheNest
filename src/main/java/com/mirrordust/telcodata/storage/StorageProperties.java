package com.mirrordust.telcodata.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties {

    /**
     * Folder location for storing files
     */
    private String location = "uploads";

    /**
     * Folder location for app (apk file)
     */
    private String appLocation = "downloads";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAppLocation() {
        return appLocation;
    }

    public void setAppLocation(String appLocation) {
        this.appLocation = appLocation;
    }
}