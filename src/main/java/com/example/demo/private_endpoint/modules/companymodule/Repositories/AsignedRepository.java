package com.example.demo.private_endpoint.modules.companymodule.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.private_endpoint.modules.companymodule.Models.UserAsigned;
import com.example.demo.User.User_;

public interface AsignedRepository extends JpaRepository<UserAsigned, Long> {
    // @Query("SELECT c FROM Company c JOIN UserAsigned u ON u.id_user = :id_user")
    // Optional<Company> findCompanyByUserId(long id);

    @Query("SELECT ua.company.id FROM UserAsigned ua WHERE ua.user.id = :userId")
    long findCompanyByUser(@Param("userId") long userId);

    @Query("SELECT ua.user FROM UserAsigned ua WHERE ua.company.id = :companyId")
    List<User_> findUserByCompanyId(@Param("companyId") long companyId);
}