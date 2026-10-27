package org.example.Model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "packages")
public class PackageEntity {
    @Id
    private String id;
    private String trackingId;
    private String containerId;
    private String uuid;
    private String ownershipFlag;

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTrackingId() { return trackingId; }
    public void setTrackingId(String trackingId) { this.trackingId = trackingId; }
    public String getContainerId() { return containerId; }
    public void setContainerId(String containerId) { this.containerId = containerId; }
    public String getUuid() { return uuid; }
    public void setUuid(String uuid) { this.uuid = uuid; }
    public String getOwnershipFlag() { return ownershipFlag; }
    public void setOwnershipFlag(String ownershipFlag) { this.ownershipFlag = ownershipFlag; }
}

