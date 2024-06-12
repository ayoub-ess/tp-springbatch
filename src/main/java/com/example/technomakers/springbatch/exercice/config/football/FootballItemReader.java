package com.example.technomakers.springbatch.exercice.config.football;

import com.example.technomakers.springbatch.exercice.model.Football;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;

public class FootballItemReader extends FlatFileItemReader<Football> {
    public FootballItemReader() {
        setResource(new ClassPathResource("random_football_data.csv"));
        setLinesToSkip(1);
        setLineMapper(new DefaultLineMapper<Football>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames("playerId", "playerName", "team", "position", "country","age","goals","assists","yellowCards","redCards");
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Football>() {{
                setTargetType(Football.class);
            }});
        }});
    }
}
