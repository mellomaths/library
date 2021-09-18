package com.mellomaths.library.mocks;

import com.mellomaths.library.domain.dto.LoanDto;
import com.mellomaths.library.domain.repository.LoanRepository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LoanInMemoryRepository implements LoanRepository {

    Map<String, LoanDto> storage = new LinkedHashMap<>();

    @Override
    public void save(LoanDto loanDto) {
        storage.put(loanDto.getId(), loanDto);
    }

    @Override
    public List<LoanDto> findByPatronId(String patronId) {
        return storage.values()
                .stream().filter((loan) -> loan.getPatron().getId().equals(patronId)).collect(Collectors.toList());
    }
}
