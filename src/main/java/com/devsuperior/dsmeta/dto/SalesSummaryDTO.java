package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SalesReportProjection;
import com.devsuperior.dsmeta.projections.SalesSummaryProjection;

public class SalesSummaryDTO {
    private String name;
    private Double total;

    public SalesSummaryDTO(String name, Double total) {
        this.name = name;
        this.total = total;
    }

    public SalesSummaryDTO(SalesSummaryProjection projection) {
        name = projection.getName();
        total = projection.getTotal();
    }
    public SalesSummaryDTO(Sale sale) {
        name = sale.getSeller().getName();
        total = sale.getAmount();
    }

    public String getName() {
        return name;
    }

    public Double getTotal() {
        return total;
    }
}
