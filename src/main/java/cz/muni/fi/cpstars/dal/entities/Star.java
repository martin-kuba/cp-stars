package cz.muni.fi.cpstars.dal.entities;

import cz.muni.fi.cpstars.dal.Constraints;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
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
    @NotNull
    private long id;

    @Column(name = "category_affiliation_probability_flag", length = Constraints.STAR_FLAG_CONSIDERED_CATEGORY_AFFILIATION_PROBABILITY_MAX_LENGTH)
    @Size(max = Constraints.STAR_FLAG_CONSIDERED_CATEGORY_AFFILIATION_PROBABILITY_MAX_LENGTH)
    private String consideredCategoryAffiliationProbabilityFlag;

    @Column(name = "id_2009_A_and_A_498_961_R", length = Constraints.STAR_ID_2009_A_AND_A_498_961_R_MAX_LENGTH)
    @Size(max = Constraints.STAR_ID_2009_A_AND_A_498_961_R_MAX_LENGTH)
    private String id_2009_A_AND_A_498_961_R;

    @Column(name = "binary_system_component", length = Constraints.STAR_FLAG_BINARY_SYSTEM_COMPONENT_MAX_LENGTH)
    @Size(max = Constraints.STAR_FLAG_BINARY_SYSTEM_COMPONENT_MAX_LENGTH)
    private String binarySystemComponent;

    @Column(name = "icrs_ra")
    @Min(value = Constraints.STAR_COORDINATE_RA_MIN)
    @Max(value = Constraints.STAR_COORDINATE_RA_MAX)
    private Double icrsRightAscension;

    @Column(name = "icrs_ra_error")
    @PositiveOrZero
    private Double icrsRightAscensionError;

    @Column(name = "icrs_dec")
    @Min(value = Constraints.STAR_COORDINATE_DEC_MIN)
    @Max(value = Constraints.STAR_COORDINATE_DEC_MAX)
    private Double icrsDeclination;

    @Column(name = "icrs_dec_error")
    @PositiveOrZero
    private Double icrsDeclinationError;

    @Column(name = "gal_longitude")
    @Min(value = Constraints.STAR_COORDINATE_GALACTIC_LONGITUDE_MIN)
    @Max(value = Constraints.STAR_COORDINATE_GALACTIC_LONGITUDE_MAX)
    private Double galacticLongitude;

    @Column(name = "gal_latitude")
    @Min(value = Constraints.STAR_COORDINATE_GALACTIC_LATITUDE_MIN)
    @Max(value = Constraints.STAR_COORDINATE_GALACTIC_LATITUDE_MAX)
    private Double galacticLatitude;

    @Column(name = "alpha", length = Constraints.STAR_COORDINATE_ALPHA_MAX_LENGTH)
    @Size(max = Constraints.STAR_COORDINATE_ALPHA_MAX_LENGTH)
    private String alpha;

    @Column(name = "delta", length = Constraints.STAR_COORDINATE_DELTA_MAX_LENGTH)
    @Size(max = Constraints.STAR_COORDINATE_DELTA_MAX_LENGTH)
    private String delta;

    @OneToMany(mappedBy = "star", cascade = {CascadeType.ALL})
    private List<Magnitude> magnitudes;

    @OneToOne(mappedBy = "star", cascade = {CascadeType.ALL})
    private Identifiers identifiers;

    @OneToOne(mappedBy = "star", cascade = {CascadeType.ALL})
    private Motion motion;

    @OneToMany(mappedBy = "star", cascade = {CascadeType.ALL})
    private List<RadialVelocity> radialVelocities;

    @OneToMany(mappedBy = "star", cascade = {CascadeType.ALL})
    private List<StarDatasourceAttribute> starDatasourceAttributes;
}
