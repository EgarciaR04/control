package com.example.demo.private_endpoint.modules.companymodule.Models;

import com.example.demo.User.User_;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
@Table(name = "asignacion", uniqueConstraints = { @UniqueConstraint(columnNames = { "user" }) })
public class UserAsigned {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // Asignacion de usuario a empresa con llave foranea Uno a uno
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user")
    private User_ user;

    // Asignacion de empresa con usuario llave uno a muchos
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_company", referencedColumnName = "id_company")
    private Company company;

}
