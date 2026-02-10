package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SalesReportProjection;

import java.time.LocalDate;

public class SalesReportDTO {
    private Long id;
    private LocalDate date;
    private Double amount;
    private String sellerName;

    public SalesReportDTO(Long id, LocalDate date, Double amount, String name) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.sellerName = name;
    }

    public SalesReportDTO(Sale sale) {
        id = sale.getId();
        date = sale.getDate();
        amount = sale.getAmount();
        sellerName = sale.getSeller().getName();
    }

    public SalesReportDTO(SalesReportProjection projection) {
        id = projection.getId();
        date = projection.getDate();
        amount = projection.getAmount();
        sellerName = projection.getSellerName();
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public Double getAmount() {
        return amount;
    }

    public String getSellerName() {
        return sellerName;
    }
}
