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
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class for star-datasource attribute, e.g. number of observed nights for APASS for specific star.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
@Entity
@Table(name = "star_datasource_attributes")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StarDatasourceAttribute {

    @Id
    @GeneratedValue
    @Column(name = "id", unique = true)
    private long id;

    @JoinColumn(name = "attribute_definition_id")
    @ManyToOne
    private AttributeDefinition attributeDefinition;

    @JoinColumn(name = "datasource_id")
    @ManyToOne
    private DataSource datasource;

    @JoinColumn(name = "star_id")
    @ManyToOne
    @JsonIgnore
    private Star star;

    @Column(name = "value")
    private String value;

    public boolean isDefined() {
        return value != null;
    }
}
