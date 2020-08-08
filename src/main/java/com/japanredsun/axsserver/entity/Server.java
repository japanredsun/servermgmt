package com.japanredsun.axsserver.entity;


import javax.persistence.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "servers")
public class Server {
    private long id;
    private String server;
    private String platform;
    private String sshUserPassword;
    private String rootPassword;
    private int isAvailable;
    private String owner;
    private String startDate;
    private String endDate;
    private String amsVersion;
    private String note;




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    @Column(name = "server",nullable = true)
    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    @Column(name = "platform", nullable = true)
    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    @Column(name = "ssh_userpassword",nullable = true)
    public String getSshUserPassword() {
        return sshUserPassword;
    }

    public void setSshUserPassword(String sshUserPassword) {
        this.sshUserPassword = sshUserPassword;
    }

    @Column(name = "root_password",nullable = true)
    public String getRootPassword() {
        return rootPassword;
    }

    public void setRootPassword(String rootPassword) {
        this.rootPassword = rootPassword;
    }




    @Column(name = "isAvailable",nullable = false)
    public int getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(int isAvailable) {
        this.isAvailable = isAvailable;
    }

    @Column(name = "owner")
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Column(name = "start_date")
    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @Column(name = "end_date")
    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Column(name = "ams_version")
    public String getAmsVersion() {
        return amsVersion;
    }

    public void setAmsVersion(String amsVersion) {
        this.amsVersion = amsVersion;
    }

    @Column(name = "note")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
