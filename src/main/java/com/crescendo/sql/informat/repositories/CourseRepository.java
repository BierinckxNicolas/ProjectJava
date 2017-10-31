package com.crescendo.sql.informat.repositories;

import com.crescendo.sql.informat.models.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/** CourseRepository
 * @author Groep 5
 */
@Repository
public interface CourseRepository extends JpaRepository<Courses, String> {
    List<Courses> findByNameEndingWith(String endString);

    @Query(value="SELECT thirdPartyRef FROM Courses WHERE id=:id")
    String getThirdpartyrefById(@Param("id") String id);

    @Query(value="SELECT COUNT(*) FROM Courses WHERE name LIKE '%_SS'")
    int getCourseWhereNameLikeSS();

    @Query(value="SELECT COUNT(*) FROM Courses")
    int getAantalCourses();

    @Query(value="SELECT COUNT(*) FROM Courses WHERE nrOfRegistrations = maxRegistrations")
    int getVolleCourses();
}
