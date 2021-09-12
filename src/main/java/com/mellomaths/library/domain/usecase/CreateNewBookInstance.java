package com.mellomaths.library.domain.usecase;

import com.mellomaths.library.domain.BookInstance;
import com.mellomaths.library.domain.dto.BookDto;
import com.mellomaths.library.domain.dto.BookInstanceDto;
import com.mellomaths.library.domain.dto.NewBookInstanceDto;
import com.mellomaths.library.domain.exception.IsbnNotFoundException;
import com.mellomaths.library.domain.repository.BookInstanceRepository;
import com.mellomaths.library.domain.repository.BookRepository;

public class CreateNewBookInstance {

    private final BookRepository bookRepository;
    private final BookInstanceRepository bookInstanceRepository;

    public CreateNewBookInstance(BookRepository bookRepository, BookInstanceRepository bookInstanceRepository) {
        this.bookRepository = bookRepository;
        this.bookInstanceRepository = bookInstanceRepository;
    }

    public BookInstanceDto execute(NewBookInstanceDto newBookInstanceDto) {
        BookInstance bookInstance = newBookInstanceDto.toModel();
        BookDto bookDto = bookRepository.findByIsbn(bookInstance.getIsbn());
        if (bookDto == null) {
            throw new IsbnNotFoundException("ISBN " + newBookInstanceDto.getIsbn() + " was not found. Please register the book before creating new instances.");
        }
        BookInstanceDto bookInstanceDto = BookInstanceDto.fromModel(bookInstance);
        bookInstanceRepository.save(bookInstanceDto);
        return bookInstanceDto;
    }
}
