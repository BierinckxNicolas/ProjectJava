package com.crescendo.sql.informat.repositories;

import com.crescendo.sql.informat.models.OtherTeachers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** OtherTeacherRepository
 * @author Groep 5
 */
@Repository
public interface OtherTeacherRepository extends JpaRepository<OtherTeachers, String>{

}
