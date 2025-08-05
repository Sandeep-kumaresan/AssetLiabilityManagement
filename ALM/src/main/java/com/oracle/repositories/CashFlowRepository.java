package com.oracle.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.oracle.entities.CashFlow;
import com.oracle.enums.FlowType;
import com.oracle.enums.FlowStatus;
import com.oracle.enums.SourceType;

public interface CashFlowRepository extends JpaRepository<CashFlow, String> {

    @Query("SELECT c FROM CashFlow c WHERE c.flowType = :flowType")
    List<CashFlow> findByFlowType(@Param("flowType") FlowType flowType);

    @Query("SELECT c FROM CashFlow c WHERE c.status = :status")
    List<CashFlow> findByStatus(@Param("status") FlowStatus status);

    @Query("SELECT c FROM CashFlow c WHERE c.sourceType = :sourceType")
    List<CashFlow> findBySourceType(@Param("sourceType") SourceType sourceType);
}