package cz.muni.fi.cpstars.dal.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Chemically Peculiar (CP) Star entity class
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
@Entity
@Table(name = "stars")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Star {

    @Id
    @GeneratedValue
    @Column(name = "id", unique = true)
    private long id;

//    @Column(name = "name")
//    private String name;

    @Column(name = "category_affiliation_probability_flag", length = 1)
    private String consideredCategoryAffiliationProbabilityFlag;

    @Column(name = "id_2009_A_and_A_498_961_R", length = 20)
    private String id_2009_A_AND_A_498_961_R;

    @Column(name = "binary_system_component", length = 1)
    private String binarySystemComponent;

    @Column(name = "icrs_ra")
    private Double icrsRightAscension;

    @Column(name = "icrs_ra_error")
    private Double icrsRightAscensionError;

    @Column(name = "icrs_dec")
    private Double icrsDeclination;

    @Column(name = "icrs_dec_error")
    private Double icrsDeclinationError;

    @Column(name = "gal_longitude")
    private Double galacticLongitude;

    @Column(name = "gal_latitude")
    private Double galacticLatitude;

    @Column(name = "alpha", length = 20)
    private String alpha;

    @Column(name = "delta", length = 20)
    private String delta;

    @OneToMany(mappedBy = "star", cascade = {CascadeType.ALL})
    private List<Magnitude> magnitudes;

    //    @OneToOne
    @OneToOne(mappedBy = "star", cascade = {CascadeType.ALL})
//    @JoinColumn(name = "indentifiers_id", referencedColumnName = "id")
    private Identifiers identifiers;

    //    @OneToOne
    @OneToOne(mappedBy = "star", cascade = {CascadeType.ALL})
//    @JoinColumn(name = "proper_motion_id", referencedColumnName = "id")
    private Motion motion;

    @OneToMany(mappedBy = "star", cascade = {CascadeType.ALL})
    private List<RadialVelocity> radialVelocities;

    @OneToMany(mappedBy = "star", cascade = {CascadeType.ALL})
    private List<StarDatasourceAttribute> starDatasourceAttributes;
}
