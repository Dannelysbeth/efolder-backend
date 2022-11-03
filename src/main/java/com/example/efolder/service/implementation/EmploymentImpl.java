package com.example.efolder.service.implementation;

import com.example.efolder.exceptions.EmploymentNotFoundException;
import com.example.efolder.model.Employment;
import com.example.efolder.model.User;
import com.example.efolder.repository.EmploymentRepository;
import com.example.efolder.service.definition.EmploymentService;
import com.example.efolder.service.definition.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class EmploymentImpl implements EmploymentService {
    private final EmploymentRepository employmentRepository;
    private final UserService userService;

    @Override
    public Employment getEmployment(String username) {
        return employmentRepository.findByUser_Username(username)
                .orElseThrow(EmploymentNotFoundException::new);
    }

    @Override
    public Employment saveEmployment(Employment employment) {
        User user = userService.getUser(employment.getUser().getUsername());
        user.setEmployment(employment);
        userService.updateUser(user);
        return employmentRepository.save(employment);
    }


    @Override
    public List<Employment> getAllByHrManager(String hrManagerName) {
        return employmentRepository.findAllByHrManager_Username(hrManagerName);
    }

    @Override
    public List<Employment> getAllBySupervisor(String managerUsername) {
        return employmentRepository.findAllByTeam_TeamLeader_Username(managerUsername);
    }

    @Override
    public List<Employment> getAllByTeamName(String teamName) {
        return employmentRepository.findAllByTeam_Name(teamName);
    }
}
