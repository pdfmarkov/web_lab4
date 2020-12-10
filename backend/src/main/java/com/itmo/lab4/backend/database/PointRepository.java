package com.itmo.lab4.backend.database;

import com.itmo.lab4.backend.database.entities.PointEntity;
import com.itmo.lab4.backend.database.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface PointRepository extends JpaRepository<PointEntity, Long> {

    @Transactional
    @Modifying
    @Query("delete from PointEntity p where p.user=:user")
    void deleteByUser(User user);
}
