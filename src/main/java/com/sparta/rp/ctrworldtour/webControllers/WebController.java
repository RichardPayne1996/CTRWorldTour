package com.sparta.rp.ctrworldtour.webControllers;

import com.sparta.rp.ctrworldtour.repositories.CourseRepository;
import com.sparta.rp.ctrworldtour.services.CourseService;
import com.sparta.rp.ctrworldtour.services.PlayerService;
import com.sparta.rp.ctrworldtour.services.TournamentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/")
public class WebController {
    private final CourseService courseService;
    private final PlayerService playerService;
    private final TournamentService tournamentService;
    private final CourseRepository courseRepository;

    public WebController(CourseService courseService, PlayerService playerService, TournamentService tournamentService, CourseRepository courseRepository) {
        this.courseService = courseService;
        this.playerService = playerService;
        this.tournamentService = tournamentService;
        this.courseRepository = courseRepository;
    }


    @GetMapping("")
    public String home(Model model) {
        return "index";
    }

    @GetMapping("/leaderboard")
    public String leaderboard(Model model) {
        model.addAttribute("players", playerService.getLeaderboard());
        model.addAttribute("courseNumber", tournamentService.getCurrentCourse());
        model.addAttribute("total", courseRepository.findAll().size());
        return "pages/leaderboard";
    }

    @PostMapping("/start/prelim")
    public String startPrelims() {
        playerService.preliminaries();
        tournamentService.preliminaries();
        return "redirect:/leaderboard";
    }

    @PostMapping("/start/main")
    public String startMain() {
        playerService.mainEvent();
        tournamentService.mainEvent();
        return "redirect:/leaderboard";
    }

    @GetMapping("/{id}")
    public String home(Model model, @PathVariable int id) {
        model.addAttribute("course", courseService.getCourseById(id));
        model.addAttribute("players", playerService.getAllPlayers());
        return "pages/view";
    }

    @PostMapping("/courses/{id}/results")
    public String trackFinish(@PathVariable int id, @RequestParam Map<String, String> params) {
        params.forEach((playerId, rank) -> {
            int idToUpdate = Integer.parseInt(playerId);
            int scoreToAdd = Integer.parseInt(rank);

            playerService.addToScore(idToUpdate, scoreToAdd);
        });
        return "redirect:/leaderboard";
    }

    @PostMapping("courses/{id}/next")
    public String nextTrack() {
        tournamentService.nextTrack();
        if (tournamentService.getCurrentCourse() > courseRepository.findAll().size()) {
            return "redirect:/final";
        }
        return "redirect:/" + tournamentService.getCurrentCourse();
    }

    @GetMapping("/final")
    public String finalScreen(Model model) {
        model.addAttribute("winner", playerService.getWinner());
        return "pages/final";
    }
}
