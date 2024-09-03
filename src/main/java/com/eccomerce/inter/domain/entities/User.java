package com.eccomerce.inter.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity(name = "tb_users")
@Table(name = "tb_users")
public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    @Getter
    @Setter
    private UUID id;

    @Column(unique = true, name = "users")
    @NotBlank(message = "Name is mandatory")
    @NonNull
    @Getter
    @Setter
    private String username;

    @Column(unique = true, name = "emails")
    @NotBlank(message = "Email is mandatory")
    @NonNull
    @Getter
    @Setter
    private String email;
    private Boolean emailVerified;

    @NotBlank(message = "Password is mandatory")
    @NonNull
    @Getter
    @Setter
    private String password;

    @CreationTimestamp
    @NonNull
    @Getter
    @Setter
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    private Boolean isActive;

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
