package com.crescendo.sql.informat.repositories;

import com.crescendo.sql.informat.models.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/** StudentRepository
 * @author Groep 5
 */
@Repository
public interface StudentRepository extends JpaRepository<Students, String> {

}
