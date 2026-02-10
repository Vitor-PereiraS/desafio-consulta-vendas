package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.projections.SalesReportProjection;
import com.devsuperior.dsmeta.projections.SalesSummaryProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface SaleRepository extends JpaRepository<Sale, Long> {

@Query(nativeQuery = true, value =
        "SELECT " +
        "s.id AS id, " +
        "s.date AS date, " +
        "ROUND(s.amount, 2) AS amount, " +
        "se.name AS sellerName " +
        "FROM TB_SALES s " +
        "INNER JOIN TB_SELLER se ON se.id = s.seller_id " +
        "WHERE s.date BETWEEN :startDate AND :endDate " +
        "AND (UPPER(se.name) LIKE CONCAT('%', UPPER(:sellerName), '%')) " +
        "ORDER BY s.date ASC, s.id ASC",
        countQuery =
        "SELECT COUNT(*) " +
        "FROM TB_SALES s " +
        "INNER JOIN TB_SELLER se ON se.id = s.seller_id " +
        "WHERE s.date BETWEEN :startDate AND :endDate " +
        "AND (UPPER(se.name) LIKE CONCAT('%', UPPER(:sellerName), '%'))"
)
Page<SalesReportProjection> sourceReport(
        @Param("startDate") LocalDate startDate,
        @Param("endDate") LocalDate endDate,
        @Param("sellerName") String sellerName,
        Pageable pageable);


    @Query(nativeQuery = true, value =
            "SELECT  TB_SELLER.NAME AS name,  ROUND(SUM(TB_SALES.AMOUNT), 2) AS total FROM TB_SELLER " +
            "INNER JOIN TB_SALES ON TB_SALES.SELLER_ID =  TB_SELLER.ID " +
            "WHERE TB_SALES.date BETWEEN :startDate AND :endDate " +
            "GROUP BY name", countQuery =
            """
            SELECT COUNT(*)
            FROM TB_SALES
            INNER JOIN TB_SELLER ON TB_SELLER.id = TB_SALES.SELLER_ID
            WHERE TB_SALES.date BETWEEN :startDate AND :endDate
        """
    )
Page<SalesSummaryProjection> sourceSummary(
        @Param("startDate") LocalDate startDate,
        @Param("endDate") LocalDate endDate,
        Pageable pageable
);

}
