package cz.muni.fi.cpstars.dal.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

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
    @NotNull
    private long id;

    @JoinColumn(name = "star_id", referencedColumnName = "id")
    @ManyToOne
    @NonNull
    @NotNull
    private Star star;

    @JoinColumn(name = "datasource_id")
    @ManyToOne
    @NonNull
    @NotNull
    private DataSource datasource;

    @Column(name = "proper_motion_ra")
    @Schema(nullable = true)
    private Double properMotionRa;

    @Column(name = "proper_motion_ra_error")
    @Schema(nullable = true)
//    @PositiveOrZero
    private Double properMotionRaError;

    @Column(name = "proper_motion_dec")
    @Schema(nullable = true)
    private Double properMotionDec;

    @Column(name = "proper_motion_dec_error")
    @Schema(nullable = true)
//    @PositiveOrZero
    private Double properMotionDecError;

    @Column(name = "parallax")
    @Schema(nullable = true)
//    @Min(Constraints.MOTION_PARALLAX_MIN)
//    @Max(Constraints.MOTION_PARALLAX_MAX)
    private Double parallax;

    @Column(name = "parallax_error")
    @Schema(nullable = true)
//    @PositiveOrZero
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
