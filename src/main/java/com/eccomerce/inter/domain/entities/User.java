package com.eccomerce.inter.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tb_users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
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

    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
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
}
