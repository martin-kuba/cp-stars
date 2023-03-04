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

    //    @OneToOne(mappedBy = "identifiers")
    @OneToOne
    @JoinColumn(name = "star_id", referencedColumnName = "id")
    @JsonIgnore
    private Star star;

    @Column(name = "gaia_dr2")
    private String gaiaDR2;

    @Column(name = "gaia_dr3")
    private String gaiaDR3;

    @Column(name = "hd")
    private String hd;

    @Column(name = "tyc")
    private String tyc;

    @Column(name = "hip")
    private String hip;

    public boolean isDefined() {
        return hd != null
                || tyc != null
                || hip != null;
    }
}
