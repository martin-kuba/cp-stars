package cz.muni.fi.cpstars.dal.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Redial velocity entity class.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
@Entity
@Table(name = "radial_velocities")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RadialVelocity {

    @Id
    @GeneratedValue
    @Column(name = "id", unique = true)
    private long id;

    @JoinColumn(name = "star_id", referencedColumnName = "id")
    @ManyToOne
    @JsonIgnore
    private Star star;

    @JoinColumn(name = "datasource_id")
    @ManyToOne
    private DataSource datasource;

    @Column(name = "radial_velocity")
    private Double radialVelocity;

    @Column(name = "radial_velocity_error")
    private Double radialVelocityError;
}
