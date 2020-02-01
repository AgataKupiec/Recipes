package pl.kupiec.recipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kupiec.recipes.entity.Unit;

public interface UnitRepository extends JpaRepository<Unit, Long> {
}
