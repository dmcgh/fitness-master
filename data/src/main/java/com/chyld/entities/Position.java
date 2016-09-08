package com.chyld.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "positions")
@Data
public class Position {
    private int id;
    private int version;
    private Float latitude;
    private Float longitude;
    private int altitude;
    private Run run;
    private Date created;
    private Date modified;

    @Id
    @GeneratedValue
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    @Version
    public int getVersion() {return version;}
    public void setVersion(int version) {this.version = version;}

    @Column(nullable = false)
    public Float getLatitude() {return latitude;}
    public void setLatitude(Float latitude) {this.latitude = latitude;}

    @Column(nullable = false)
    public Float getLongitude() {return longitude;}
    public void setLongitude(Float longitude) {this.longitude = longitude;}

    @Column(nullable = false)
    public int getAltitude() {return altitude;}
    public void setAltitude(int altitude) {this.altitude = altitude;}

    @ManyToOne
    @JoinColumn(name="run_id")
    @JsonIgnore
    public Run getRun() {return run;}
    public void setRun(Run run) {this.run = run;}

    @CreationTimestamp
    public Date getCreated() {return created;}
    public void setCreated(Date created) {this.created = created;}

    @UpdateTimestamp
    public Date getModified() {return modified;}
    public void setModified(Date modified) {this.modified = modified;}
}
