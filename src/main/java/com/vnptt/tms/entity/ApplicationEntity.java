package com.vnptt.tms.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "application")
public class ApplicationEntity extends BaseEntity {
    @Column(name = "packagename", nullable = false)
    private String packagename;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "version", nullable = false)
    private String version;
    @Column(name = "isSystem", nullable = false)
    private boolean issystem;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "device_application",
            joinColumns = @JoinColumn(name = "application_id"),
            inverseJoinColumns = @JoinColumn(name = "device_id"))
    private List<DeviceEntity> deviceEntitiesApplication = new ArrayList<>();

    @OneToMany(mappedBy = "applicationEntityHistory")
    private List<HistoryApplicationEntity> historyApplicationEntities = new ArrayList<>();


    public String getPackagename() {
        return packagename;
    }

    public void setPackagename(String packagename) {
        this.packagename = packagename;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<DeviceEntity> getDeviceEntitiesApplication() {
        return deviceEntitiesApplication;
    }

    public void setDeviceEntitiesApplication(List<DeviceEntity> deviceEntitiesApplication) {
        this.deviceEntitiesApplication = deviceEntitiesApplication;
    }

    public List<HistoryApplicationEntity> getHistoryApplicationEntities() {
        return historyApplicationEntities;
    }

    public void setHistoryApplicationEntities(List<HistoryApplicationEntity> historyApplicationEntities) {
        this.historyApplicationEntities = historyApplicationEntities;
    }

    public boolean isIssystem() {
        return issystem;
    }

    public void setIssystem(boolean issystem) {
        this.issystem = issystem;
    }
}
