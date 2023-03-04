package cz.muni.fi.cpstars.dal.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Star-Datasource Attribute definition. Contains general information about attribute.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
@Entity
@Table(name = "attribute_definitions")
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class AttributeDefinition {
    @Id
    @GeneratedValue
    @Column(name = "id", unique = true)
    private long id;

    @Column(name = "name", length = 30, unique = true)
    @NonNull
    private String name;

    @Column(name = "type", length = 20)
    @NonNull
    private String type;

    @Column(name = "description")
    @NonNull
    private String description;
}
