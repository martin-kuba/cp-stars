package cz.muni.fi.cpstars.dal.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Entity class for Hertzsprung-Russell Diagram values -> luminosity and effective temperature.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
@Entity
@Table(name = "hr_diagram_values")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(exclude = "star")
public class HRDiagramValues {

    @Id
    @GeneratedValue
    @Column(name = "id", unique = true)
    private long id;

//        @OneToOne(mappedBy = "hrDiagramValues", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @OneToOne
    @JoinColumn(name = "star_id", referencedColumnName = "id")
    @JsonIgnore
    private Star star;

    @Column(name = "teff")
    private Double effectiveTemperature;

    @Column(name = "lower_teff")
    private Double lowerEffectiveTemperature;

    @Column(name = "higher_teff")
    private Double higherEffectiveTemperature;

    @Column(name = "lum")
    private Double luminosity;

    @Column(name = "lower_lum")
    private Double lowerLuminosity;

    @Column(name = "higher_lum")
    private Double higherLuminosity;

    public boolean isDefined() {
        return effectiveTemperature != null
                || lowerEffectiveTemperature != null
                || higherEffectiveTemperature != null
                || luminosity != null
                || lowerLuminosity != null
                || higherLuminosity != null;
    }
}
