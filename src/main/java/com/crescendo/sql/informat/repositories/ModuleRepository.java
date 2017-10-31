package com.crescendo.sql.informat.repositories;

import com.crescendo.sql.informat.models.Modules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Column;

/** ModuleRepository
 * @author Groep 5
 */
@Repository
public interface ModuleRepository extends JpaRepository<Modules, String> {
}
