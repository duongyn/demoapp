package com.duonghai.shoppingonline.entity;

import com.duonghai.shoppingonline.model.Provider;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Setter
@Table(name = "users")
public class UserEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @Column(unique = true)
    private String username;

    private String password;

    @Singular
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "users_authorities", joinColumns = {
            @JoinColumn(name = "user_id", referencedColumnName = "ID") }, inverseJoinColumns = {
            @JoinColumn(name = "role_id", referencedColumnName = "ID") })
    private Set<RoleEntity> authorities;

    @Builder.Default
    private Boolean accountNonExpired = true;
    @Builder.Default
    private Boolean accountNonLocked = true;
    @Builder.Default
    private Boolean credentialsNonExpired = true;
    @Builder.Default
    private Boolean enabled = true;

    private String firstName;
    private String lastName;
    private String emailAddress;
    private LocalDate birthdate;

    @Enumerated(EnumType.STRING)
    private Provider provider;
}
