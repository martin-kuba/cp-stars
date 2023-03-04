package cz.muni.fi.cpstars.dal.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * Datasource entity, e.g. APASS.
 *
 * @author Ľuboslav Halama <lubo.halama@gmail.com>
 */
@Entity
@Table(name = "datasources")
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class DataSource {

    @Id
    @GeneratedValue
    @Column(name = "id", unique = true)
    private long id;

    @Column(name = "name", length = 30)
    @NonNull
    private String name;

    @Column(name = "description", length = 16384)
    @NonNull
    private String description;
}
