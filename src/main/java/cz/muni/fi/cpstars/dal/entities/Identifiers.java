package cz.muni.fi.cpstars.dal.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import cz.muni.fi.cpstars.dal.Constraints;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Star basic identifiers entity class.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
@Entity
@Table(name = "identifiers")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Identifiers {

    @Id
    @GeneratedValue
    @Column(name = "id", unique = true)
    private long id;

    @OneToOne
    @JoinColumn(name = "star_id", referencedColumnName = "id")
    @JsonIgnore
    private Star star;

    @Column(name = "gaia_dr2", length = Constraints.IDENTIFIERS_GAIADR2_MAX_LENGTH)
    @Size(max = Constraints.IDENTIFIERS_GAIADR2_MAX_LENGTH)
    private String gaiaDR2;

    @Column(name = "gaia_dr3", length = Constraints.IDENTIFIERS_GAIADR3_MAX_LENGTH)
    @Size(max = Constraints.IDENTIFIERS_GAIADR3_MAX_LENGTH)
    private String gaiaDR3;

    @Column(name = "hd", length = Constraints.IDENTIFIERS_HD_MAX_LENGTH)
    @Size(max = Constraints.IDENTIFIERS_HD_MAX_LENGTH)
    private String hd;

    @Column(name = "tyc", length = Constraints.IDENTIFIERS_TYC_MAX_LENGTH)
    @Size(max = Constraints.IDENTIFIERS_TYC_MAX_LENGTH)
    private String tyc;

    @Column(name = "hip", length = Constraints.IDENTIFIERS_HIP_MAX_LENGTH)
    @Size(max = Constraints.IDENTIFIERS_HIP_MAX_LENGTH)
    private String hip;

    public boolean isDefined() {
        return (gaiaDR2 != null && !gaiaDR2.isEmpty())
                || (gaiaDR3 != null && !gaiaDR3.isEmpty())
                || (hd != null && !hd.isEmpty())
                || (tyc != null && !tyc.isEmpty())
                || (hip != null && !hip.isEmpty());
    }
}
