package com.example.technomakers.springbatch.exercice.config.football;

import com.example.technomakers.springbatch.exercice.model.Football;
import com.example.technomakers.springbatch.exercice.model.Student;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.FieldExtractor;
import org.springframework.core.io.FileSystemResource;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FootballItemWriter extends FlatFileItemWriter<Football> {

    private static final String HEADER = "PlayerID,PlayerName,Goals";
    private static final String FILE_NAME = "output.csv"; // Specify the output folder here

    public FootballItemWriter() {
        setResource(new FileSystemResource(FILE_NAME));
        setLineAggregator(new DelimitedLineAggregator<Football>() {{
            setDelimiter(",");
            setFieldExtractor( football -> new Object[] {
                    football.getPlayerID(),
                    football.getPlayerName(),
                    football.getGoals()
            });
        }});
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        super.open(executionContext);
        writeHeader();
    }

    private void writeHeader() {
        Path path = Paths.get(FILE_NAME);
        if (!Files.exists(path) || isFileEmpty(path)) {
            try (Writer writer = Files.newBufferedWriter(path)) {
                writer.write(HEADER);
                writer.write(System.lineSeparator());
            } catch (IOException e) {
                throw new ItemStreamException("Erreur de création de header", e);
            }
        }
    }

    private boolean isFileEmpty(Path path) {
        try {
            return Files.size(path) == 0;
        } catch (IOException e) {
            throw new ItemStreamException("Pas de possibilté de vérifier l'xistance de fichier", e);
        }
    }
}