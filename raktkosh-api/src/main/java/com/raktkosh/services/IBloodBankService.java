package com.raktkosh.services;

import com.raktkosh.pojos.BloodBank;

public interface IBloodBankService {
	BloodBank saveBankDetails(BloodBank bloodBank);

	BloodBank getBankDetailsById(Long bankId);

	BloodBank getBankDetailsByBankName(String bankName);

	boolean deleteBankDetails(Long bankId);

	BloodBank updateBankDetails(BloodBank bloodBank);
}
