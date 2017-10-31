package com.crescendo.sql.informat.repositories;

import com.crescendo.sql.informat.models.NewStudents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/** NewStudentRepository
 * @author Groep 5
 */
@Repository
public interface NewStudentRepository extends JpaRepository<NewStudents, String> {
}
