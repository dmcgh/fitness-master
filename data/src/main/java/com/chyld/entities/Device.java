package com.chyld.entities;

import com.chyld.enums.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "devices")
@Data
public class Device {
    private int id;
    private int version;
    private String serialNumber;
    private String product;
    private Category category;
    private User user;
    private List<Run> runs;
    private Date created;
    private Date modified;

    @Id
    @GeneratedValue
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    @Version
    public int getVersion() {return version;}
    public void setVersion(int version) {this.version = version;}

    @Column(name = "serial_number", nullable = false)
    public String getSerialNumber() {return serialNumber;}
    public void setSerialNumber(String serialNumber) {this.serialNumber = serialNumber;}

    @Column(nullable = false)
    public String getProduct() {return product;}
    public void setProduct(String product) {this.product = product;}

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('RUN', 'SWIM')")
    public Category getCategory() {return category;}
    public void setCategory(Category category) {this.category = category;}

    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonIgnore
    public User getUser() {return user;}
    public void setUser(User user) {this.user = user;}

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "device")
    public List<Run> getRuns() {return runs;}
    public void setRuns(List<Run> runs) {this.runs = runs;}

    @CreationTimestamp
    public Date getCreated() {return created;}
    public void setCreated(Date created) {this.created = created;}

    @UpdateTimestamp
    public Date getModified() {return modified;}
    public void setModified(Date modified) {this.modified = modified;}
}
