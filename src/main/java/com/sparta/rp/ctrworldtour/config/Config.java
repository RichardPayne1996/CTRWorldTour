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
                var course2 = new Course("Mystery Caves", "placeholder");
                cRepo.save(course1);
                cRepo.save(course2);
            }

        };
    }
}
