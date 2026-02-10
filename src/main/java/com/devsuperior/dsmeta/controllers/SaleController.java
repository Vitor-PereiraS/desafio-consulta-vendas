package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.SalesReportDTO;
import com.devsuperior.dsmeta.dto.SalesSummaryDTO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;

    LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<Page<SalesReportDTO>> getReport(
            @RequestParam(value = "minDate", required = false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate minDate,
            @RequestParam(value = "maxDate", required = false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate maxDate,
            @RequestParam(value = "name", defaultValue = "") String name,
            Pageable pageable
    ) {
        if (maxDate == null) {
            maxDate = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
        }

        if (minDate == null) {
            minDate = maxDate.minusYears(1L);
        }

		Page<SalesReportDTO> dto = service.getReport(minDate, maxDate, name, pageable);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<Page<SalesSummaryDTO>> getSummary(
            @RequestParam(value = "minDate", required = false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate minDate,
            @RequestParam(value = "maxDate", required = false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate maxDate,
            Pageable pageable
    ) {
        if (maxDate == null) {
            maxDate = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
        }

        if (minDate == null) {
            minDate = maxDate.minusYears(1L);
        }
        Page<SalesSummaryDTO> dto = service.getSummary(minDate, maxDate, pageable);
		return ResponseEntity.ok(dto);
	}

}
