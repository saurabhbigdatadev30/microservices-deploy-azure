package edu.tcu.cs.hogwartsartifactsonline;

import edu.tcu.cs.hogwartsartifactsonline.artifact.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
// Swagger - https://app.swaggerhub.com/apis/Washingtonwei/hogwarts-openapi/1.0.0#/artifact/updateArtifact
@SpringBootApplication
public class HogwartsArtifactsOnlineApplication {

    public static void main(String[] args) {
        SpringApplication.run(HogwartsArtifactsOnlineApplication.class, args);
    }

    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1, 1);
    }

}
