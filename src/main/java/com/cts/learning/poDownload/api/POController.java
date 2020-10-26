package com.cts.learning.poDownload.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.learning.poDownload.model.PO;
import com.cts.learning.poDownload.service.POService;

@RestController
@RequestMapping("po")
public class POController {
	
	@Autowired
	POService poService;

	@PostMapping
	public ResponseEntity<PO> createPO(@RequestBody PO poModel) {
		PO responseModel = poService.createPO(poModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseModel);
	}

	@GetMapping("/{poNumber}")
	public ResponseEntity<PO> getPODetails(@PathVariable String poNumber) {
		PO responseModel = poService.getPODetails(poNumber);
		return ResponseEntity.status(HttpStatus.OK).body(responseModel);
	}
	
}
