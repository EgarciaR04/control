package com.example.demo.private_endpoint.modules.companymodule.Models;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "empresa", uniqueConstraints = { @UniqueConstraint(columnNames = { "company_name" }) })
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_company")
    long id;

    @Column(nullable = false)
    @NotNull
    private boolean hability;

    @Column(nullable = false)
    @Size(max = 25)
    @NotNull
    private String company_name;

    @Column(nullable = false)
    @Size(max = 50)
    @NotNull
    private String address;

    @Column(nullable = false)
    @Size(max = 14)
    @NotNull
    private String nit;

    @Column(nullable = false)
    @Size(max = 25)
    @NotNull
    private String owner;

    @Column(nullable = false)
    @Size(max = 10, min = 6)
    @NotNull
    private String usernameExtension;

    @Size(max = 10)
    @Nullable
    private String tel;

    @Size(max = 100)
    @Nullable
    private String observations;

    @Size(max = 25)
    @Nullable
    private String deparment;

    @Size(max = 25)
    @Nullable
    private String state;
}
