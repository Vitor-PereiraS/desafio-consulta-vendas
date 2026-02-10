package com.devsuperior.dsmeta.services;

import java.time.LocalDate;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.SalesReportDTO;
import com.devsuperior.dsmeta.dto.SalesSummaryDTO;
import com.devsuperior.dsmeta.projections.SalesReportProjection;
import com.devsuperior.dsmeta.projections.SalesSummaryProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

    public Page<SalesReportDTO> getReport(LocalDate startDate, LocalDate endDate, String sellerName, Pageable pageable) {

         Page<SalesReportProjection> result = repository.sourceReport(startDate, endDate, sellerName, pageable);
        ; return result.map(projection ->
                new SalesReportDTO(
                        projection.getId(),
                        projection.getDate(),
                        projection.getAmount(),
                        projection.getSellerName()
                ));
    }
    public Page<SalesSummaryDTO> getSummary(LocalDate startDate, LocalDate endDate, Pageable pageable) {
        Page<SalesSummaryProjection> result = repository.sourceSummary(startDate, endDate, pageable);
        return result.map(SalesSummaryDTO::new);
    }
}
