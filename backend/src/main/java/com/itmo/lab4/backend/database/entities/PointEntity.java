package com.itmo.lab4.backend.database.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "points", schema = "public")
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
@JsonIgnoreProperties("user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PointEntity implements Serializable {

    @NotNull
    private Double xcoord;
    @NotNull
    private Double ycoord;
    @NotNull
    private Double radius;

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "ISHIT")
    private String isHit;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "REQUESTDATE")
    private Date requestDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointEntity that = (PointEntity) o;
        return Objects.equals(xcoord, that.xcoord) &&
                Objects.equals(ycoord, that.ycoord) &&
                Objects.equals(radius, that.radius);
    }

    public void setIsHit(){
        isHit = xcoord >= 0 && ycoord <= 0 && ycoord >= 2.0 * xcoord - radius ||
                xcoord <= 0 && ycoord >= 0 && xcoord >= -radius / 2 && ycoord <= radius ||
                xcoord <= 0 && ycoord <= 0 && xcoord * xcoord + ycoord * ycoord <= radius * radius ? "YES" : "NO";
    }

    @Override
    public int hashCode() {
        return Objects.hash(xcoord, ycoord, radius);
    }
}