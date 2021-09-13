package com.mellomaths.library.mocks;

import com.mellomaths.library.domain.dto.LoanDto;
import com.mellomaths.library.domain.repository.LoanRepository;

import java.util.LinkedHashMap;
import java.util.Map;

public class LoanInMemoryRepository implements LoanRepository {

    Map<String, LoanDto> storage = new LinkedHashMap<>();

    @Override
    public void save(LoanDto loanDto) {
        storage.put(loanDto.getId(), loanDto);
    }
}
