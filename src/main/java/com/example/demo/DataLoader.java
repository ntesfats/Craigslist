package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;

    @Autowired
    JobRepository jobRepository;

    @Override
    public void run(String... args) throws Exception {

        User user = new User();
        user.setFirstName("Nahom");
        user.setLastName("Haile");
        user.setPhoneNumber("301-589-0589");
        userRepository.save(user);

        User user2 = new User();
        user2.setFirstName("Hermon");
        user2.setLastName("Bob");
        user2.setPhoneNumber("301-747-8759");
        userRepository.save(user2);



        Job job1 = new Job();
        job1.setTitle("Java developer");
        job1.setPositionType("Full Time");
        job1.setLocation("Washigton DC.");
        job1.setDescription("Develop an web application using Java, Spring boot");
        job1.setPostedDate("June 10, 2020");

        Job job2 = new Job();
        job2.setTitle("Python developer");
        job2.setPositionType("Full Time");
        job2.setLocation("Baltimore, Maryland");
        job2.setDescription("Develop secure web application using Python, Flask framework");
        job2.setPostedDate("July 8, 2020");

        Job job3 = new Job();
        job3.setTitle("JavaScript developer");
        job3.setPositionType("Part Time");
        job3.setLocation("Rockville, Maryland");
        job3.setDescription("Develop secure web application using JavaScript,Nodejs");
        job3.setPostedDate("May 19, 2020");


        job1.setUser(user);
        job2.setUser(user);
        job3.setUser(user2);


        jobRepository.save(job1);
        jobRepository.save(job2);
        jobRepository.save(job3);






        Job j;
        for (int i=0; i < 50; i++) {
            j = new Job();
            j.setTitle("JavaScript developer");
            j.setPositionType("Part Time");
            j.setLocation("Rockville, Maryland");
            j.setDescription("Develop secure web application using JavaScript,Nodejs");
            j.setPostedDate("May 19, 2020");
            j.setUser(user2);
            jobRepository.save(j);
        }






    }
}
