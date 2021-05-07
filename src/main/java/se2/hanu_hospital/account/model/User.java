package se2.hanu_hospital.account.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
        }),
        @UniqueConstraint(columnNames = {
                "phoneNumber"
        })
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "INT(6) UNSIGNED ", precision = 4, updatable = false)
    private Long id;

    @NotBlank
    @NotNull
    @Size(max = 30)
    @Column(length = 30)
    private String name;

    @NotBlank
    @NotNull
    @Size(max = 30)
    @Column(length = 30)
    private String username;

    @NotBlank
    @NotNull
    @Size(max = 100)
    @Column(length = 100)
    private String password;

    @NotBlank
    @NotNull
    @Size(max = 30)
    @Column(length = 30)
    private String phoneNumber;

    @Size(max = 100)
    @Column(length = 100)
    @NotNull
    private String address;

    @OneToOne()
    private Role role;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    public User() {

    }

    public User(String name, String username, String password, String phoneNumber, String address, String status) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.status = UserStatus.valueOf(status);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = UserStatus.valueOf(status);
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}
