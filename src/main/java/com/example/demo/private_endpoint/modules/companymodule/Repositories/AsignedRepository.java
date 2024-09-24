package com.example.demo.private_endpoint.modules.companymodule.Repositories;

import java.util.List;
import java.util.Optional;

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

    @Query("SELECT ua FROM UserAsigned ua WHERE ua.company.id = :companyId")
    List<UserAsigned> findUserByCompanyId(@Param("companyId") long companyId);

    @Query("SELECT ua.id FROM UserAsigned ua WHERE ua.user.username = :username")
    long findIdByUserName(@Param("username") String username);

    @Query("SELECT a FROM UserAsigned a WHERE a.id = :id_asg")
    UserAsigned findAsignedById(@Param("id_asg") long id);

    @Query("SELECT ua.user FROM UserAsigned ua WHERE ua.id = :id_asig")
    Optional<User_> findUserByIdAsig(@Param("id_asig") long id);
}