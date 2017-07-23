package com.inti.student.androidassignment;



public class API {

    public String websiteName;
    public String url;

    public API(String websiteName, String url) {
        this.websiteName = websiteName;
        this.url = url;
    }

    public String getWebsiteName() {
        return websiteName;
    }
    public void setWebsiteName(String websiteName) {
        this.websiteName = websiteName;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

}
