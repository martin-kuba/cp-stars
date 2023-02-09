package cz.muni.fi.cpstars.dal.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Star proper motion entity class.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
@Entity
@Table(name = "proper_motion")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProperMotion {

    @Id
    @GeneratedValue
    @Column(name = "id", unique = true)
    private long id;

    //    @OneToOne(mappedBy = "properMotion")
    @OneToOne
    @JoinColumn(name = "star_id", referencedColumnName = "id")
    @JsonIgnore
    private Star star;

    @Column(name = "proper_motion_ra")
    private Double properMotionRa;

    @Column(name = "proper_motion_ra_error")
    private Double properMotionRaError;

    @Column(name = "proper_motion_dec")
    private Double properMotionDec;

    @Column(name = "proper_motion_dec_error")
    private Double properMotionDecError;

    public boolean isDefined() {
        return properMotionRa != null
                || properMotionRaError != null
                || properMotionDec != null
                || properMotionDecError != null;
    }
}
