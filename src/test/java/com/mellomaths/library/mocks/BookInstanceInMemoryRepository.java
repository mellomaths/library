package com.mellomaths.library.mocks;

import com.mellomaths.library.domain.dto.BookInstanceDto;
import com.mellomaths.library.domain.repository.BookInstanceRepository;

import java.util.LinkedHashMap;
import java.util.Map;

public class BookInstanceInMemoryRepository implements BookInstanceRepository {

    Map<String, BookInstanceDto> storage = new LinkedHashMap<>();

    @Override
    public void save(BookInstanceDto bookInstance) {
        storage.put(bookInstance.getId(), bookInstance);
    }

}
