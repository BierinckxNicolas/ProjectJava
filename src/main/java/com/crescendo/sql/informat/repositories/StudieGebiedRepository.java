package com.crescendo.sql.informat.repositories;

import com.crescendo.sql.informat.models.Studiegebied;
import org.hibernate.annotations.NamedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/** StudieGebiedRepository
 * @author Groep 5
 */
@Repository
public interface StudieGebiedRepository extends JpaRepository<Studiegebied, String>{

}
