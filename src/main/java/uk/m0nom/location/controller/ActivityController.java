package uk.m0nom.location.controller;

import org.springframework.web.bind.annotation.*;
import uk.m0nom.location.activity.Activity;
import uk.m0nom.location.activity.cota.Cota;
import uk.m0nom.location.activity.gma.Gma;
import uk.m0nom.location.activity.hema.Hema;
import uk.m0nom.location.activity.iota.Iota;
import uk.m0nom.location.activity.lota.Lota;
import uk.m0nom.location.activity.pota.Pota;
import uk.m0nom.location.activity.rota.Rota;
import uk.m0nom.location.activity.sota.Sota;
import uk.m0nom.location.activity.wota.Wota;
import uk.m0nom.location.activity.wwff.Wwff;
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

    @GetMapping("/type")
    public String findActivityByRef(@RequestParam String ref) {
        return activityRepository.findActivityTypeByRef(ref);
    }

    @GetMapping("/cota")
    public Cota getCota(@RequestParam String ref) {
        return activityRepository.getCotaByPk(ref);
    }

    @GetMapping("/gma")
    public Gma getGma(@RequestParam String ref) {
        return activityRepository.getGmaByPk(ref);
    }

    @GetMapping("/hema")
    public Hema getHema(@RequestParam String ref) {
        return activityRepository.getHemaByPk(ref);
    }

    @GetMapping("/iota")
    public Iota getIota(@RequestParam String ref) {
        return activityRepository.getIotaByPk(ref);
    }

    @GetMapping("/lota")
    public Lota getLota(@RequestParam String ref) {
        return activityRepository.getLotaByPk(ref);
    }

    @GetMapping("/pota")
    public Pota getPota(@RequestParam String ref) {
        return activityRepository.getPotaByPk(ref);
    }

    @GetMapping("/rota")
    public Rota getRota(@RequestParam String ref) {
        return activityRepository.getRotaByPk(ref);
    }

    @GetMapping("/sota")
    public Sota getSota(@RequestParam String ref) {
        return activityRepository.getSotaByPk(ref);
    }

    @GetMapping("/wota")
    public Wota getWota(@RequestParam String ref) {
        return activityRepository.getWotaByPk(ref);
    }

    @GetMapping("/wwff")
    public Wwff getWwff(@RequestParam String ref) {
        return activityRepository.getWwffByPk(ref);
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
