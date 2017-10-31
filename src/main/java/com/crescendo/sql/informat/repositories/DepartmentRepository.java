package com.crescendo.sql.informat.repositories;

import com.crescendo.sql.informat.models.Departments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/** DepartmentRepository
 * @author Groep 5
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Departments, String> {
}
