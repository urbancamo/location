package uk.m0nom.location;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

@Getter
public class ProfileManager {
    @Value("${spring.profiles.active:}")
    private String activeProfiles;

}