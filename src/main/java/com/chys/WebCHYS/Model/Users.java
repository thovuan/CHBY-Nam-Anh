package com.chys.WebCHYS.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.validator.constraints.UUID;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class Users {
    @Id
    @UUID
    @Column(nullable = false, updatable = false, length = 40, name="id")
    private String id;

    @Column( name="username")
    private String username;

    @Column( name = "password")
    private String password;

    @Column( name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(columnDefinition = "text", name = "avatar_url")
    private String avatarUrl;

    @Column(nullable = false, name = "role_id")
    private String roleId;

    @Column(name = "google_id")
    private String googleId;

    @Column(nullable = false, name = "account_type")
    private String accountType;

    @Column(nullable = false, name = "created_at")
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;
}