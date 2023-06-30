package com.pointtech.server.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.pointtech.server.entities.Image;
import com.pointtech.server.entities.Rating;
import com.pointtech.server.entities.Research;
import com.pointtech.server.entities.User;
import com.pointtech.server.entities.enums.CurrencyType;
import com.pointtech.server.entities.Country;
import com.pointtech.server.repositories.RatingRepository;
import com.pointtech.server.repositories.ResearchRepository;
import com.pointtech.server.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{

    @Autowired
    private UserRepository userRepository;

    @Autowired 
    private RatingRepository ratingRepository;

    @Autowired
    private ResearchRepository researchRepository;


    @Override
    public void run(String... args) throws Exception {

        User u1 = new User(null, "Maria Brown", "maria@email.com", "323323");
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "523543");

        userRepository.saveAll(Arrays.asList(u1,u2));

        Rating r1 = new Rating(null, 7, "Good");
        Rating r2 = new Rating(null, 5, "Bad");
        Rating r3 = new Rating(null, 8, "Good");
        Rating r4 = new Rating(null, 9, "Good");

        ratingRepository.saveAll(Arrays.asList(r1,r2,r3,r4));

        Country country1 = new Country("Brazil", 4000, "Glassdoor", CurrencyType.BRL);

        Research research1 = new Research(null, "Research 1", "Description 1", Instant.parse("2021-09-10T19:53:07Z"), new Image("https://img.icons8.com/ios/452/research.png", "Image1"));

        research1.getRatings().addAll(Arrays.asList(r1,r2,r3,r4));
        research1.getCountries().add(country1);
        

        researchRepository.saveAll(Arrays.asList(research1));
      
    }
    
}
