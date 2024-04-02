package com.dango.dango.domain.log.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.dango.dango.domain.log.entity.Log;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class LogQueryRepository {
	private final EntityManager em;

	public List<Log> findAllByRefrigeratorNickname(String nickname, Boolean exist) {
		String jpql = "select l from Log l "
			+ "where l.refrigerator.nickname = :nickname "
			+ "and l.exist = :exist";

		if (!exist) {
			jpql += " and l.deleteTime = :yesterday";
		}

		TypedQuery<Log> query = em.createQuery(jpql, Log.class);
		query.setParameter("nickname", nickname);
		query.setParameter("exist", exist);

		if (!exist) {
			query.setParameter("yesterday", LocalDateTime.now().minusDays(1));
		}

		return query.getResultList();
	}

}
