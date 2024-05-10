package com.example.mppbackend.repository;

import com.example.mppbackend.entity.EnumRole;
import com.example.mppbackend.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(EnumRole name);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM user_roles WHERE user_id  = ?1", nativeQuery = true)
    void deleteUserRolesByUserId(Long userId);
}
