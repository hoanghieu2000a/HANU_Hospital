package se2.hanu_hospital.account.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import se2.hanu_hospital.account.model.Role;
import se2.hanu_hospital.account.model.RoleName;

@DataJpaTest
public class RoleRepositoryTest {
    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void testFindByName() {
        Role role = new Role(1L, RoleName.ROLE_DOCTOR);
        Role role1 = new Role(2L, RoleName.ROLE_NURSE);
        roleRepository.save(role);
        roleRepository.save(role1);
        assertThat(this.roleRepository.findByName(RoleName.ROLE_DOCTOR).getName().equals(RoleName.ROLE_DOCTOR)).isTrue();
    }
}

