package com.example.demo.private_endpoint.modules.companymodule.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "empresa", uniqueConstraints = { @UniqueConstraint(columnNames = { "company_name" }) })
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_company")
    long id;

    boolean hability;
    String company_name;
    String address;
    String nit;
    String dueño;
    String tel;
    String observations;
    String deparment;
    String state;
}
