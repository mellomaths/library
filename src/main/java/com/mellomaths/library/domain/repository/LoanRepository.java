package com.mellomaths.library.domain.repository;

import com.mellomaths.library.domain.dto.LoanDto;

import java.util.List;

public interface LoanRepository {
    void save(LoanDto loanDto);
    List<LoanDto> findByPatronId(String patronId);
}
