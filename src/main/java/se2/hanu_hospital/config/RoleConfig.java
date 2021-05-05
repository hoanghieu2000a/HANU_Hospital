package se2.hanu_hospital.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import se2.hanu_hospital.account.model.Role;
import se2.hanu_hospital.account.model.RoleName;
import se2.hanu_hospital.account.repository.RoleRepository;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class RoleConfig {
    @Bean
    CommandLineRunner orderLineCommandLineRunner(RoleRepository roleRepository) {
        return args -> {
            Role r1 = new Role();
            r1.setName(RoleName.ROLE_ADMIN);
            Role r2 = new Role();
            r2.setName(RoleName.ROLE_NURSE);
            Role r3 = new Role();
            r3.setName(RoleName.ROLE_DOCTOR);
            Role r4 = new Role();
            r4.setName(RoleName.ROLE_RECEPTIONIST);
            List<Role> list = new ArrayList<>();
            list.add(r1);
            list.add(r2);
            list.add(r3);
            list.add(r4);
            if (roleRepository.findByName(RoleName.ROLE_ADMIN) != null){
                if ( !roleRepository.findByName(RoleName.ROLE_ADMIN).getName().toString().equals(RoleName.ROLE_ADMIN.toString()))
                    roleRepository.saveAll(list);
            }
        };
    }
}