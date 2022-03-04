package uk.m0nom.location.controller;

import org.springframework.web.bind.annotation.*;
import uk.m0nom.location.activity.Activity;
import uk.m0nom.location.activity.cota.Cota;
import uk.m0nom.location.activity.sota.Sota;
import uk.m0nom.location.repository.ActivityRepository;

@RestController
@RequestMapping(path = "/api/activity")
public class ActivityController {
    private final ActivityRepository activityRepository;

    public ActivityController(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @PostMapping("")
    public Activity saveActivity(@RequestBody Activity Activity) {
        return activityRepository.save(Activity);
    }

    @GetMapping("")
    public Activity getActivity(@RequestParam String type, @RequestParam String ref) {
        return activityRepository.getActivityByPk(type, ref);
    }

    @GetMapping("/cota")
    public Cota getCota(@RequestParam String ref) {
        return activityRepository.getCotaByPk(ref);
    }

    @GetMapping("/sota")
    public Sota getSota(@RequestParam String ref) {
        return activityRepository.getSotaByPk(ref);
    }

    @DeleteMapping("")
    public String deleteActivity(@RequestParam String type, @RequestParam String ref) {
        return activityRepository.delete(type, ref);
    }

    @PutMapping("")
    public String updateActivity(@RequestParam String type, @RequestParam String ref, @RequestBody Activity Activity) {
        return activityRepository.update(type, ref, Activity);
    }
}
