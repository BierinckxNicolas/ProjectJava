package com.crescendo.sql.informat.repositories;

import com.crescendo.sql.informat.models.Locations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/** LocationRepository
 * @author Groep 5
 */
@Repository
public interface LocationRepository extends JpaRepository<Locations, String> {
}
