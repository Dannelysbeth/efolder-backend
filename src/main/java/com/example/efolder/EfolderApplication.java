package com.example.efolder;

import com.example.efolder.model.*;
import com.example.efolder.service.definition.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.HashSet;

@SpringBootApplication
public class EfolderApplication {

    public static void main(String[] args) {
        SpringApplication.run(EfolderApplication.class, args);
    }

//    @Bean
//    CommandLineRunner run(UserService userService, RoleService roleService, TeamService teamService, AddressService addressService, UserService userInfoService){
//        return args ->{
//            roleService.saveRole(new Role(null, "ROLE_REGULAR_EMPLOYEE"));
//            roleService.saveRole(new Role(null, "ROLE_HR_ADMIN"));
//            roleService.saveRole(new Role(null, "ROLE_MANAGER"));
//            roleService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));
//
////            teamService.saveTeam(new Team(null, "HR", "Human Resources", new ArrayList<>(), null));
////            teamService.saveTeam(new Team(null, "Java", "Java Development", new ArrayList<>(), null));
////            teamService.saveTeam(new Team(null, "ZMiTAC", "The living hell", new ArrayList<>(), null));
//
//            userService.saveUser(new User("John", "Travolta","jtravolta", "password", "jtravolta@test.com"));
//            userService.saveUser(new User("Sarah", "Miley","smiley", "password", "smiley@test.com"));
//            userService.saveUser(new User("Eduard", "Pierre","epierre", "password", "epierre@test.com"));
////            userService.saveUser(new User(null, "smiley", "password", new ArrayList<>(), null, null, null, null));
////            userService.saveUser(new User(null, "epierre", "password", new ArrayList<>(), null, null, null, null));
//
//            userService.addRoleToUser("jtravolta", "ROLE_MANAGER");
//            userService.addRoleToUser("jtravolta", "ROLE_REGULAR_EMPLOYEE");
//            userService.addRoleToUser("smiley", "ROLE_REGULAR_EMPLOYEE");
//            userService.addRoleToUser("epierre", "ROLE_REGULAR_EMPLOYEE");
//            userService.addRoleToUser("epierre", "ROLE_HR_ADMIN");
//            userService.addRoleToUser("epierre", "ROLE_SUPER_ADMIN");
////            UserInfo userInfo = userInfoService.getUser("smclavish");
////            addressService.saveAddress(new Address(
////                    userInfo.getId(),
////                    userInfo,
////                    "UK",
////                    "County of London",
////                    "London",
////                    "Baker's Street",
////                    "226B",
//        };
//    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
