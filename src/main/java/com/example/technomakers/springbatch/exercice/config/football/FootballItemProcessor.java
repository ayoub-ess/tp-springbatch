package com.example.technomakers.springbatch.exercice.config.football;

import com.example.technomakers.springbatch.exercice.model.Football;
import com.example.technomakers.springbatch.exercice.model.Student;
import org.springframework.batch.item.ItemProcessor;

public class FootballItemProcessor implements ItemProcessor<Football, Football> {
    @Override
    public Football process(Football football) throws Exception {
        if (football.getGoals() > 20) {
            football.setPlayerName(football.getPlayerName().toUpperCase());
        }
        return football;
    }
}
