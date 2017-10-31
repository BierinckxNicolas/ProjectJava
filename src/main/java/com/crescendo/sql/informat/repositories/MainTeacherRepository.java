package com.crescendo.sql.informat.repositories;

import com.crescendo.sql.informat.models.MainTeachers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/** MainTeacherRepository
 * @author Groep 5
 */
@Repository
public interface MainTeacherRepository extends JpaRepository<MainTeachers, String> {
}
