package cz.muni.fi.cpstars.dal.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cz.muni.fi.cpstars.dal.Constraints;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class for magnitude measurement attribute, e.g. weight of V magnitude.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
@Entity
@Table(name = "magnitude_attributes")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MagnitudeAttribute {

    @Id
    @GeneratedValue
    @Column(name = "id", unique = true)
    private long id;

    @JoinColumn(name = "attribute_definition_id")
    @ManyToOne
    private AttributeDefinition attributeDefinition;

    @JoinColumn(name = "magnitude_id")
    @ManyToOne
    @JsonIgnore
    private Magnitude magnitude;

    @Column(name = "value", length = Constraints.MAGNITUDE_ATTRIBUTE_VALUE_MAX_LENGTH)
    @Size(max = Constraints.MAGNITUDE_ATTRIBUTE_VALUE_MAX_LENGTH)
    private String value;

    public boolean isDefined() {
        return value != null && !value.isEmpty();
    }
}
