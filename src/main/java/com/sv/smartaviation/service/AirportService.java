package com.sv.smartaviation.service;

import com.sv.smartaviation.entity.Airport;
import java.util.List;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import static com.sv.smartaviation.util.Constants.AIRPORTS_CACHE_NAME;

@Service
@RequiredArgsConstructor
@Slf4j
public class AirportService {

    private final EntityManager entityManager;

    @Cacheable(AIRPORTS_CACHE_NAME)
    public List<Airport> searchAirport(String query) {
        log.debug("Searching airports with query: {}", query);
        SearchSession searchSession = Search.session(entityManager);

        var searchResult = searchSession.search(Airport.class)
                .where(f -> f.wildcard()
                        .fields("name", "city", "cityCode", "county", "state")
                        .matching("*" + query + "*")
                ).sort(f -> f.field("name_sort").asc());

        return searchResult.fetchAllHits();

    }

}
