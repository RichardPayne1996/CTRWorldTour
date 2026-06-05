package com.sparta.rp.ctrworldtour.config;

import com.sparta.rp.ctrworldtour.entities.Course;
import com.sparta.rp.ctrworldtour.entities.Player;
import com.sparta.rp.ctrworldtour.repositories.CourseRepository;
import com.sparta.rp.ctrworldtour.repositories.PlayerRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    @Transactional
    public CommandLineRunner loadData(PlayerRepository pRepo, CourseRepository cRepo) {
        return args -> {
            System.out.println("Data Loader Running...");

            if (pRepo.count() == 0) {
                var player1 = new Player("Loogie", "Loogie290696", "Loogie poo");
                var player2 = new Player("RiddyP", "Riddy240796", "Riddy P");
                pRepo.save(player1);
                pRepo.save(player2);
            }

            if (cRepo.count() == 0) {
                var course1 = new Course("Crash Cove", "crash-cove.jpg");
                var course2 = new Course("Mystery Caves", "mystery-caves.webp");
                var course3 = new Course("Sewer Speedway", "sewer-speedway.jpg");
                var course4 = new Course("Roo's tubes", "roos-tubes.jpg");
                var course5 = new Course("Turbo Track", "turbo-track.webp");
                cRepo.save(course1);
                cRepo.save(course2);
                cRepo.save(course3);
                cRepo.save(course4);
                cRepo.save(course5);
            }

        };
    }
}
