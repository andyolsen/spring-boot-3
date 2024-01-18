package mypackage;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class Config {

    @Bean
    @Scope("prototype")
    public Transcript verboseTranscript() {
        return new Transcript(5, true);
    }

    @Bean
    @Scope("prototype")
    public Transcript briefTranscript() {
        return new Transcript(5, false);
    }
}