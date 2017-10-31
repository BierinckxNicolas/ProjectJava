package com.crescendo.sql.app.repositories;

import com.crescendo.sql.app.models.CheckBox;
import com.crescendo.sql.app.models.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** StatisticRepository
 * @author Groep 5
 */
@Repository
public interface StatisticRepository extends JpaRepository<Statistic, Integer> {
}
