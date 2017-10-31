package com.crescendo.sql.app.repositories;

import com.crescendo.sql.app.models.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/** EmailRepository -- crud
 * @author Groep 5
 */
@Repository
public interface EmailRepository extends JpaRepository<Email, Integer> {

}
