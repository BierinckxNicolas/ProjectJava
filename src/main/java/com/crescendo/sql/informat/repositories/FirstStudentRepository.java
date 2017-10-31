package com.crescendo.sql.informat.repositories;

import com.crescendo.sql.informat.models.FirstStudents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/** FirstStudentRepository
 * @author Groep 5
 */
@Repository
public interface FirstStudentRepository extends JpaRepository<FirstStudents, String> {
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM FirstStudents c WHERE c.username = :username")
    boolean existsByUsername(@Param("username") String username);

    @Query("SELECT stamNr FROM FirstStudents WHERE id=:id")
    String getStamNrById(@Param("id") String id);

    @Query("SELECT COUNT(*) FROM FirstStudents")
    int getAantalCursisten();
}
