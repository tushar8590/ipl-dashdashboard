package com.practicaljava.data.repository;
import java.time.LocalDate;
import java.util.List;

import com.practicaljava.model.Match;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface MatchRepository extends CrudRepository<Match, Long>  {

    List<Match>  findByTeam1OrTeam2OrderByMatchDateDesc(String team1, String team2, Pageable pageable);

    default List<Match> findLatestMatechedByTeam(String teamName, int count){
            return findByTeam1OrTeam2OrderByMatchDateDesc(teamName,teamName,PageRequest.of(0, count));
    }

}