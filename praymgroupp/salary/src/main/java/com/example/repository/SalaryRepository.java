package com.example.repository;

import com.example.model.Salary;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface SalaryRepository extends JpaRepository<Salary, Long>, JpaSpecificationExecutor<Salary> {
//    Optional<Salary> findBySalaryId(Long id);
    List<Salary> findAllByOwnerLastName(String responsible, Sort sort);
//    List<Salary> findAllByOwnerId(List<Long> ids);
}
