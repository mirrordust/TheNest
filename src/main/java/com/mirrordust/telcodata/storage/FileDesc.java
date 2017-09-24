package com.mirrordust.telcodata.storage;

public class FileDesc {
    private String name;
    private String downloadUrl;
    private String viewUrl;

    public FileDesc(String name, String downloadUrl, String viewUrl) {
        this.name = name;
        this.downloadUrl = downloadUrl;
        this.viewUrl = viewUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getViewUrl() {
        return viewUrl;
    }

    public void setViewUrl(String viewUrl) {
        this.viewUrl = viewUrl;
    }
}
