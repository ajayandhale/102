package com.raktkosh.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raktkosh.pojos.BloodBank;
import com.raktkosh.repositories.BankRepository;

@Service
@Transactional
public class BloodBankServiceImpl implements IBloodBankService {

	@Autowired
	private BankRepository bankRepo;

	// add blood bank
	@Override
	public BloodBank saveBankDetails(BloodBank bloodBank) {

		return bankRepo.save(bloodBank);
	}

	// get blood bank details by bankId
	@Override
	public BloodBank getBankDetailsById(Long bankId) {

		return bankRepo.findById(bankId).orElseThrow();
	}

	// get blood bank details by bankName
	@Override
	public BloodBank getBankDetailsByBankName(String bankName) {

		return bankRepo.findByName(bankName).orElseThrow();
	}

	// delete blood bank details by bankId
	@Override
	public boolean deleteBankDetails(Long bankId) {
		bankRepo.deleteById(bankId);
		return true;
	}

	// update bank details
	@Override
	public BloodBank updateBankDetails(BloodBank bloodBank) {
		return bankRepo.save(bloodBank);
	}

}
