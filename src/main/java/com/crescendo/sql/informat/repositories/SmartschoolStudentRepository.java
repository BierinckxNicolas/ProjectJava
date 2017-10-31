package com.crescendo.sql.informat.repositories;

import com.crescendo.sql.informat.models.SmartschoolStudents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/** SmartschoolStudentRepository
 * @author Groep 5
 */
@Repository
public interface SmartschoolStudentRepository extends JpaRepository<SmartschoolStudents, String> {
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM SmartschoolStudents c WHERE c.username = :username")
    boolean existsByUsername(@Param("username") String username);

    @Query("SELECT COUNT(*) FROM SmartschoolStudents")
    int getAllNewSmartschoolUsers();
}
