package com.raktkosh.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.raktkosh.dto.requests.BloodBankRepositoryDTO;
import com.raktkosh.dto.requests.BloodBankRepositoryIdDTO;
import com.raktkosh.exceptions.BloodRepositoryException;
import com.raktkosh.pojos.BloodBank;
import com.raktkosh.pojos.BloodRepository;
import com.raktkosh.pojos.BloodRepositoryID;
import com.raktkosh.repositories.BloodBankRepository;

@Service
@Transactional
public class BloodBankRepositoryServiceImpl implements IBloodBankRepositoryService {
  
  @Autowired
  private BloodBankRepository bloodBankRepo;
  
  @Value("${com.raktkosh.blood.repository.NOT_FOUND}")
  private String repoNotFound;

  @Override
  public BloodRepository addRepository(BloodBankRepositoryDTO repository) {
    return bloodBankRepo.save(BloodRepository.build(repository));
  }

  @Override
  public boolean deleteRepositoryById(BloodBankRepositoryIdDTO id) {
    bloodBankRepo.deleteById(BloodRepositoryID.build(id));
    return true;
  }

  @Override
  public BloodRepository updateRepositoryById(BloodBankRepositoryIdDTO id, int quantity) {
    BloodRepository bloodRepo = bloodBankRepo.findById(BloodRepositoryID.build(id))
                                  .orElseThrow(() -> new BloodRepositoryException(repoNotFound));
    bloodRepo.setAvailability(quantity);
    return bloodBankRepo.save(bloodRepo);
  }

  @Override
  public List<BloodRepository> findByBloodBank(Long id) {
    BloodBank bank = new BloodBank();
    bank.setId(id);
    return bloodBankRepo.findByIdBank(bank);
  }

  @Override
  public List<BloodRepository> findByBloodTypeAndAntigen(BloodBankRepositoryIdDTO id) {
    return bloodBankRepo.findByIdTypeAndIdAntigen(id.getType(), id.getAntigen());
  }
}
