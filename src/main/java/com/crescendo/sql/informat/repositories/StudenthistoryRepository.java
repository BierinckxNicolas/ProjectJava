package com.crescendo.sql.informat.repositories;

import com.crescendo.sql.informat.models.Studenthistories;
import com.crescendo.sql.informat.models.Studiegebied;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/** StudenthistoryRepository
 * @author Groep 5
 */
@Repository
public interface StudenthistoryRepository extends JpaRepository<Studenthistories, String>, StudenthistoryRepositoryCustom{

    @Query(value = "SELECT courseId FROM Studenthistories WHERE schoolyear=:schoolyear AND stamnr =:stamnr")
    List<String> getCoursesByStamnrAndYear(@Param("schoolyear") String schoolyear, @Param("stamnr") String stamnr);
}
