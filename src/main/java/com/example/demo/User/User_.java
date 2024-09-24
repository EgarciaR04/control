package com.example.demo.User;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }) })
public class User_ implements UserDetails {
  @Id
  @Column(name = "id_user")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  long id;
  @Column(nullable = false)
  String username;
  @Column(nullable = false)
  String firstname;
  @Column(nullable = false)
  String lastname;
  String password;
  @Column(nullable = true)
  String tel;
  @Column(nullable = true)
  @Enumerated(EnumType.STRING)
  Role role;
  boolean hability;
  boolean changePassword;
  boolean changePasswordNextSession;

  public boolean getHability() {
    return this.hability;
  }
  public boolean getChangePassword() {
    return this.changePassword;
  }
  public boolean getChangePasswordNextSession() {
    return this.changePasswordNextSession;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    // TODO Auto-generated method stub
    return List.of(new SimpleGrantedAuthority((role.name())));
  }

}
