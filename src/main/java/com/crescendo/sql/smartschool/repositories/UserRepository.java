package com.crescendo.sql.smartschool.repositories;


import com.crescendo.sql.smartschool.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/** UserRepository
 * @author Groep 5
 */
@Repository
public interface UserRepository extends JpaRepository<Users, Long>{
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Users c WHERE c.gebruikersnaam = :gebruikersnaam")
    boolean existsByUsername(@Param("gebruikersnaam") String gebruikersnaam);

    Users findByInternnummer(String internnummer);

    Boolean findByInternnummerEquals(String internummer);

    @Query("SELECT COUNT(*) FROM Users")
    int aantalSSUsers();
}
