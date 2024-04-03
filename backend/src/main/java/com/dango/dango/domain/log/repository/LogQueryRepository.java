package com.dango.dango.domain.log.repository;

import com.dango.dango.domain.log.entity.Log;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class LogQueryRepository {
    private final EntityManager em;

    public List<Log> findAllByRefrigeratorNickname(String nickname, Boolean exist) {
        String jpql = "select l from Log l "
                + "where l.refrigerator.nickname = :nickname "
                + "and l.exist = :exist";

        if (!exist) {
            jpql += " and l.deleteTime between :startDate and :endDate";
//            jpql += " and l.deleteTime = :yesterday";
        }

        TypedQuery<Log> query = em.createQuery(jpql, Log.class);
        query.setParameter("nickname", nickname);
        query.setParameter("exist", exist);

        if (!exist) {
            query.setParameter("startDate", LocalDateTime.now().minusDays(1));
            query.setParameter("endDate", LocalDateTime.now());
        }

        return query.getResultList();
    }

}
