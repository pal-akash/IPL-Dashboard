package com.akash.ipldashboard.data;

import com.akash.ipldashboard.model.Match;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MatchDataProcessor implements ItemProcessor<MatchInput, Match> {

    private static final Logger log = LoggerFactory.getLogger(MatchDataProcessor.class);

    @Override
    public Match process(final MatchInput matchInput) throws Exception {

        Match match = new Match();

        match.setId(Long.parseLong(matchInput.getId()));

        match.setCity(matchInput.getCity());

        String date1 = matchInput.getDate();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate  d1 = LocalDate.parse(date1, df);
        match.setDate(d1);

        match.setVenue(matchInput.getVenue());

        match.setTossWinner(matchInput.getToss_winner());
        match.setTossDecision(matchInput.getToss_decision());

        match.setWinningTeam(matchInput.getWinning_team());

        match.setPlayerOfMatch(matchInput.getPlayer_of_match());

        String firstInningsTeam, secondInningsTeam;

        //setting team 1 and team 2 depending upon innings order
        if("bat".equals(matchInput.getToss_decision())){
            firstInningsTeam= matchInput.getToss_winner();
            secondInningsTeam= matchInput.getToss_winner().equals(matchInput.getTeam1()) ? matchInput.getTeam2() : matchInput.getTeam1();
        }else{
            secondInningsTeam= matchInput.getToss_winner();
            firstInningsTeam= matchInput.getToss_winner().equals(matchInput.getTeam1()) ? matchInput.getTeam2() : matchInput.getTeam1();
        }

        match.setTeam1(firstInningsTeam);
        match.setTeam2(secondInningsTeam);

        match.setTossWinner(matchInput.getToss_winner());
        match.setTossDecision(matchInput.getToss_decision());

        match.setWinningTeam(matchInput.getWinning_team());
        match.setWonBy(matchInput.getWon_by());
        match.setMargin(matchInput.getMargin());

        match.setUmpire1(matchInput.getUmpire1());
        match.setUmpire2(matchInput.getUmpire2());

        return match;
    }

}
