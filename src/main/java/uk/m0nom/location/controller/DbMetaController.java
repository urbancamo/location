package uk.m0nom.location.controller;

import com.amazonaws.AmazonServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import uk.m0nom.location.repository.ActivityRepository;

@RestController
@RequestMapping(path = "/api/dbmeta")
public class DbMetaController {
    private final ActivityRepository activityRepository;

    public DbMetaController(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @PostMapping("/table/{name}")
    public String createTable(@PathVariable String name) {
        if ("activity".equals(name)) {
            try {
                activityRepository.createActivityTable();
            } catch (AmazonServiceException ase) {
                throw new ResponseStatusException(HttpStatus.valueOf(ase.getStatusCode()));
            }
            return "activity";
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/table/{name}")
    public String deleteTable(@PathVariable String name) {
        if ("activity".equals(name)) {
            try {
                activityRepository.deleteActivityTable();
            } catch (AmazonServiceException ase) {
                throw new ResponseStatusException(HttpStatus.valueOf(ase.getStatusCode()));
            }
            return "activity";
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/table/{name}/seed")
    public String seedTable(@PathVariable String name) {
        if ("activity".equals(name)) {
            try {
                activityRepository.seedActivityTable();
            } catch (AmazonServiceException ase) {
                throw new ResponseStatusException(HttpStatus.valueOf(ase.getStatusCode()));
            }
            return "activity";
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

}
