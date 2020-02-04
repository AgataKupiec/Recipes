package pl.kupiec.recipes.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "units")
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    private String name;
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Unit)) return false;
        Unit unit = (Unit) o;
        return Objects.equals(id, unit.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
