package com.akash.ipldashboard.controller;

import com.akash.ipldashboard.model.Match;
import com.akash.ipldashboard.model.Team;
import com.akash.ipldashboard.repository.MatchRepository;
import com.akash.ipldashboard.repository.TeamRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@CrossOrigin
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

        team.setMatches(this.matchRepository.findLatestMatchesByTeam(teamName, 4));

        return team;
    }

    @GetMapping("/api/team/{teamName}/matches")
    public List<Match> getMatchesForTeam(@PathVariable String teamName, @RequestParam int year){
        LocalDate startDate = LocalDate.of(year, 1,1);
        LocalDate endDate = LocalDate.of(year+1, 1,1);

        //return this.matchRepository.getByTeam1AndDateBetweenOrTeam2AndDateBetweenOrderByDateDesc(teamName, startDate, endDate, teamName, startDate, endDate);

        return this.matchRepository.getMatchesByTeamBetweenDates(teamName, startDate,endDate);
    }

}
