package com.practicaljava.data.controller;

import com.practicaljava.data.repository.MatchRepository;
import com.practicaljava.data.repository.TeamRepository;
import com.practicaljava.model.Team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamController {
    
    @Autowired
    private TeamRepository repository;

    @Autowired
    private MatchRepository matchRepository;
    
    @GetMapping("/team/{teamName}")
    public Team getTeam(@PathVariable("teamName") String teamName){
        Team team  = repository.findByTeamName(teamName);
      
        team.setMatches(matchRepository.findLatestMatechedByTeam(teamName, 5));
        return team;
    }
}
