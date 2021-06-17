package com.utopiaxc.chest.beans;

public class BeanLicense {
    String project;
    String author;
    String license_link;
    int pic;

    public BeanLicense(String project, String author, String license_link, int pic) {
        this.project = project;
        this.author = author;
        this.license_link = license_link;
        this.pic = pic;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLicense_link() {
        return license_link;
    }

    public void setLicense_link(String license_link) {
        this.license_link = license_link;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }
}
