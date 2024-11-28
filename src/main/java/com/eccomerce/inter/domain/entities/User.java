package com.eccomerce.inter.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tb_users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;

    @Email
    private String email;

    private String password;

    private String recoveryCode;

    @Temporal(TemporalType.TIMESTAMP)
    private Date codeValidityDate;

    @ManyToOne
    @JoinColumn(name = "address")
    private Address address;

    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @Setter(value = AccessLevel.NONE)
    private List<PermissionUser> permissionUsers;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    public void setPermissionUsers(List<PermissionUser> permissionUsers) {
        for(PermissionUser p : permissionUsers) {
            p.setUser(this);
        }
        this.permissionUsers = permissionUsers;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return permissionUsers;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
