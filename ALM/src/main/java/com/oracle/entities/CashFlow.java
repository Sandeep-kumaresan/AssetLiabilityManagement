package com.oracle.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import com.oracle.enums.FlowStatus;
import com.oracle.enums.FlowType;
import com.oracle.enums.SourceType;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Table(name = "cash_flows")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CashFlow {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "flow_id", nullable = false, length = 36)
	private String flowId;  // Store UUID as string

    @Enumerated(EnumType.STRING)
    @Column(name = "source_type", nullable = false)
    private SourceType sourceType;

    @Column(name = "flow_date", nullable = false)
    private LocalDate flowDate;

    @Column(name = "amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;

    @Column(name = "currency", nullable = false, length = 3)
    private String currency;

    @Enumerated(EnumType.STRING)
    @Column(name = "flow_type", nullable = false)
    private FlowType flowType;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private FlowStatus status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "source_id", nullable = false, length = 36)
    private String sourceId;

    // Lifecycle callbacks to auto-set timestamps
    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
