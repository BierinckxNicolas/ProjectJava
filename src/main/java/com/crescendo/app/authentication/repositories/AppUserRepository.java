package com.crescendo.app.authentication.repositories;

import com.crescendo.app.authentication.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** AppUser Repository
 * @author Groep 5
 */
@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long>{
    AppUser findOneByUsername(String username);
}
