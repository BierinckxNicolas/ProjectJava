package com.crescendo.sql.informat.repositories;

import com.crescendo.sql.informat.DTO.StudentHistoryDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

/** StudenthistoryRepositoryCustom
 * Deze hoort bij de fix voor @Query voor tabellen zonder relatie
 * @author Groep 5
 */
@Repository
public interface StudenthistoryRepositoryCustom {

    List<StudentHistoryDTO> findHistoriesByFirstNameAndLastName(String firstname, String lastname);
}
