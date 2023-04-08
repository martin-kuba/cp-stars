package cz.muni.fi.cpstars.dal.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

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
    @NotNull
    private long id;

    @JoinColumn(name = "star_id", referencedColumnName = "id")
    @ManyToOne
    private Star star;

    @JoinColumn(name = "datasource_id")
    @ManyToOne
    @NonNull
    @NotNull
    private DataSource datasource;

    @Column(name = "radial_velocity")
    @NonNull
    @NotNull
    private Double radialVelocity;

    @Column(name = "radial_velocity_error")
    @PositiveOrZero
    private Double radialVelocityError;
}
