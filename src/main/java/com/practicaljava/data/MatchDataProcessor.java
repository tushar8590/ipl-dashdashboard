package com.practicaljava.data;

import java.time.LocalDate;
import com.practicaljava.model.Match;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class MatchDataProcessor implements ItemProcessor<MatchInput,Match> {
    
    private static final Logger log = LoggerFactory.getLogger(MatchDataProcessor.class);

  @Override
  public Match process(final MatchInput matchInput) throws Exception {
   
    Match match = new Match();
    match.setId(Long.parseLong(matchInput.getId()));
    match.setMatchWinner(matchInput.getWinner());
    match.setCity(matchInput.getCity());
    match.setMatchDate(LocalDate.parse(matchInput.getDate()));
    match.setPlayerOfMatch(matchInput.getPlayer_of_match());
    if(matchInput.getVenue().length() >= 20){
      match.setVenue(matchInput.getVenue().substring(0,18));
    }else{
      match.setVenue(matchInput.getVenue());
    }
    
    String firstInningsTeam, secondInningsTeam;

    if("bat".equals(matchInput.getToss_decision())){
        firstInningsTeam = matchInput.getToss_winner();
        secondInningsTeam = matchInput.getToss_winner().equals(matchInput.getTeam1())
                                ?matchInput.getTeam2() : matchInput.getTeam1();
    }else{
        secondInningsTeam = matchInput.getToss_winner();
        firstInningsTeam = matchInput.getToss_winner().equals(matchInput.getTeam1())
                                ?matchInput.getTeam2() : matchInput.getTeam1();
    }
    match.setTeam1(firstInningsTeam);
    match.setTeam2(secondInningsTeam);
    match.setTossWinner(matchInput.getToss_winner());
    match.setResult(matchInput.getResult());
    match.setResultMargin(matchInput.getResult_margin());
    match.setUmpire1(matchInput.getUmpire1());
    match.setUmpire2(matchInput.getUmpire2());

    log.info("Processing " + match.getId());
    return match;
  }

}
