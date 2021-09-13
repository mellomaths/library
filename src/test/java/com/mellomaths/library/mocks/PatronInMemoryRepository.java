package com.mellomaths.library.mocks;

import com.mellomaths.library.domain.dto.PatronDto;
import com.mellomaths.library.domain.repository.PatronRepository;

import java.util.LinkedHashMap;
import java.util.Map;

public class PatronInMemoryRepository implements PatronRepository {

    Map<String, PatronDto> storage = new LinkedHashMap<>();

    @Override
    public void save(PatronDto patronDto) {
        storage.put(patronDto.getId(), patronDto);
    }

    @Override
    public PatronDto findById(String id) {
        return storage.get(id);
    }
}
