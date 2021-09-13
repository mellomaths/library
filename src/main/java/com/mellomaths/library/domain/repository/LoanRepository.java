package com.mellomaths.library.domain.repository;

import com.mellomaths.library.domain.dto.LoanDto;

public interface LoanRepository {
    void save(LoanDto loanDto);
}
