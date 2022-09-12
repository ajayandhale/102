package com.raktkosh.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raktkosh.dto.requests.BloodBankRepositoryDTO;
import com.raktkosh.dto.requests.BloodBankRepositoryIdDTO;
import com.raktkosh.services.IBloodBankRepositoryService;

@RestController
@RequestMapping("/bloodrepo")
@CrossOrigin(origins = { "${com.raktkosh.ORIGINS}" })
public class BloodRepositoryController {

	@Autowired
	private IBloodBankRepositoryService bloodBankService;

	@PostMapping("/{bankId}")
	public ResponseEntity<?> addBloodRepository(@RequestBody @Valid BloodBankRepositoryDTO bloodRepo,
			@PathVariable Long bankId) {
		bloodRepo.setBankId(bankId);
		return new ResponseEntity<>(bloodBankService.addRepository(bloodRepo), HttpStatus.CREATED);
	}

	@DeleteMapping("/{bankId}")
	public ResponseEntity<?> deleteBloodRepository(@RequestBody @Valid BloodBankRepositoryIdDTO bloodRepo,
			@PathVariable Long bankId) {
		bloodRepo.setBankId(bankId);
		return ResponseEntity.ok(bloodBankService.deleteRepositoryById(bloodRepo));
	}

	@PatchMapping("/{bankId}")
	public ResponseEntity<?> updateBloodRepository(@RequestBody @Valid BloodBankRepositoryDTO bloodRepo,
			@PathVariable Long bankId) {
		bloodRepo.setBankId(bankId);
		return ResponseEntity.ok(bloodBankService.updateRepositoryById(bloodRepo, bloodRepo.getAvailability()));
	}

	@GetMapping("/{bankId}")
	public ResponseEntity<?> getBloodRespositoryByBankId(@PathVariable Long bankId) {
		return ResponseEntity.status(HttpStatus.OK).body(bloodBankService.findByBloodBank(bankId));
	}

	@GetMapping("/find")
	public ResponseEntity<?> getBloodRespositoryByTypeAndAntigen(
			@RequestBody @Valid BloodBankRepositoryIdDTO bloodRepo) {
		return ResponseEntity.status(HttpStatus.OK).body(bloodBankService.findByBloodTypeAndAntigen(bloodRepo));
	}

}
