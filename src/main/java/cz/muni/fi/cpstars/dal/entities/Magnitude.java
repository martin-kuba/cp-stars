package cz.muni.fi.cpstars.dal.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Star magnitude entity class.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "magnitudes")
public class Magnitude {

    @Id
    @GeneratedValue
    @Column(name = "id", unique = true)
    private long id;

    @JoinColumn(name = "star_id")
    @ManyToOne
    @JsonIgnore
    private Star star;

    @Column(name = "band")
    private String band;

    @Column(name = "value")
    private Double value;

    @Column(name = "error")
    private Double error;

    @Column(name = "quality")
    private String quality;

    public boolean isDefined() {
        return value != null
                || error != null
                || quality != null;
    }
}
