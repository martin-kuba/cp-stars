package cz.muni.fi.cpstars.dal.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cz.muni.fi.cpstars.dal.Constraints;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Star motion entity class.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
@Entity
@Table(name = "motions")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Motion {

    @Id
    @GeneratedValue
    @Column(name = "id", unique = true)
    private long id;

    @JoinColumn(name = "star_id", referencedColumnName = "id")
    @OneToOne
    @JsonIgnore
    private Star star;

    @JoinColumn(name = "datasource_id")
    @ManyToOne
    private DataSource datasource;

    @Column(name = "proper_motion_ra")
    private Double properMotionRa;

    @Column(name = "proper_motion_ra_error")
    @PositiveOrZero
    private Double properMotionRaError;

    @Column(name = "proper_motion_dec")
    private Double properMotionDec;

    @Column(name = "proper_motion_dec_error")
    @PositiveOrZero
    private Double properMotionDecError;

    @Column(name = "parallax")
    @Min(Constraints.MOTION_PARALLAX_MIN)
    @Max(Constraints.MOTION_PARALLAX_MAX)
    private Double parallax;

    @Column(name = "parallax_error")
    @PositiveOrZero
    private Double parallaxError;

    public boolean isDefined() {
        return properMotionRa != null
                || properMotionRaError != null
                || properMotionDec != null
                || properMotionDecError != null
                || parallax != null
                || parallaxError != null;
    }
}
