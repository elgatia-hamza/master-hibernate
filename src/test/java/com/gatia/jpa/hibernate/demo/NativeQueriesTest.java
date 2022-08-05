package com.gatia.jpa.hibernate.demo;

import com.gatia.jpa.hibernate.demo.entity.Course;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
class NativeQueriesTest {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	@Test
	void native_queries_basic() {
		Query query = em.createNativeQuery("SELECT * FROM COURSE", Course.class);
		List courseList = query.getResultList();
		logger.info("SELECT * FROM COURSE -> {}", courseList);
	}

	@Test
	void native_queries_parameter() {
		Query query = em.createNativeQuery("SELECT * FROM COURSE WHERE id = ?", Course.class);
		query.setParameter(1, 1L);
		List courseList = query.getResultList();
		logger.info("SELECT * FROM COURSE id = ? -> {}", courseList);
	}

	@Test
	void native_queries_named_parameter() {
		Query query = em.createNativeQuery("SELECT * FROM COURSE WHERE id=:id", Course.class);
		query.setParameter("id",1L);
		List courseList = query.getResultList();
		logger.info("SELECT * FROM COURSE id=:id -> {}", courseList);
	}

	@Test
	@Transactional
	void native_queries_update() {
		Query query = em.createNativeQuery("UPDATE COURSE SET last_update_at=CURRENT_DATE", Course.class);
		int numOfUpdatedRows = query.executeUpdate();
		logger.info("UPDATE COURSE SET last_update_at=sysdate(),  -> {} rows updated.", numOfUpdatedRows);
	}

}
