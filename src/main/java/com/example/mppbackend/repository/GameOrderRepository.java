package com.example.mppbackend.repository;

import com.example.mppbackend.entity.GameOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameOrderRepository extends JpaRepository<GameOrder, Long> {
}
