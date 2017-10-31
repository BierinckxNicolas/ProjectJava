package com.crescendo.sql.informat.repositories;

import com.crescendo.sql.informat.models.DeletedStudents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** DeletedStudentsRepository
 * @author Groep 5
 */
@Repository
public interface DeletedStudentsRepository extends JpaRepository<DeletedStudents, String> {
}
