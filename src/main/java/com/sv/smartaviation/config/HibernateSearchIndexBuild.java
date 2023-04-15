package com.sv.smartaviation.config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class HibernateSearchIndexBuild implements ApplicationListener<ApplicationReadyEvent> {

    private final EntityManagerFactory entityManagerFactory;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info("Started Initializing Indexes");
        var stopWatch = StopWatch.createStarted();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        SearchSession searchSession = Search.session(entityManager);

        try {
            searchSession.massIndexer().startAndWait();
        } catch (InterruptedException e) {
            log.warn("Failed to load data from database", e);
            Thread.currentThread().interrupt();
        }
        stopWatch.stop();
        log.info("Completed Indexing in {} ms",stopWatch.getTime());
    }
}
