package com.akash.ipldashboard.controller;

import com.akash.ipldashboard.model.Team;
import com.akash.ipldashboard.repository.MatchRepository;
import com.akash.ipldashboard.repository.TeamRepository;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TeamController {

    private TeamRepository teamRepository;
    private MatchRepository matchRepository;

    public TeamController(TeamRepository teamRepository, MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }

    @GetMapping("/api/team/{teamName}")
    public Team getTeam(@PathVariable String teamName){
        Team team = this.teamRepository.findByTeamName(teamName);
        Pageable pageable = PageRequest.of(0,4);
        team.setMatches(this.matchRepository.getByTeam1OrTeam2OrderByDateDesc(teamName, teamName, pageable));

        return team;
    }

}
