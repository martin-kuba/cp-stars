package cz.muni.fi.cpstars.dal.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
@Table(name = "cp_stars")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Star {

    @Id
    @GeneratedValue
    @Column(name = "id", unique = true)
    private long id;

    @Column(name = "name")
    private String name;

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

    @OneToMany(mappedBy = "star")
    private List<Magnitude> magnitudes;

    //    @OneToOne
    @OneToOne(mappedBy = "star", cascade = {CascadeType.ALL})
//    @JoinColumn(name = "indentifiers_id", referencedColumnName = "id")
    private Identifiers identifiers;

    //    @OneToOne
    @OneToOne(mappedBy = "star", cascade = {CascadeType.ALL})
//    @JoinColumn(name = "hr_diagram_values_id", referencedColumnName = "id")
    private HRDiagramValues hrDiagramValues;

    //    @OneToOne
    @OneToOne(mappedBy = "star", cascade = {CascadeType.ALL})
//    @JoinColumn(name = "proper_motion_id", referencedColumnName = "id")
    private ProperMotion properMotion;
}
