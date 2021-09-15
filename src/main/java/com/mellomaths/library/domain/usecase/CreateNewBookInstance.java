package com.mellomaths.library.domain.usecase;

import com.mellomaths.library.domain.Book;
import com.mellomaths.library.domain.BookInstance;
import com.mellomaths.library.domain.dto.BookDto;
import com.mellomaths.library.domain.dto.BookInstanceDto;
import com.mellomaths.library.domain.dto.NewBookInstanceDto;
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
        BookDto bookDto = new SearchBookByISBN(bookRepository, bookInstanceRepository).execute(newBookInstanceDto.getIsbn());
        Book book = Book.fromDto(bookDto);
        BookInstance instance = book.createNewInstance(newBookInstanceDto.getType());

        BookInstanceDto bookInstanceDto = BookInstanceDto.fromModel(instance);
        bookInstanceRepository.save(bookInstanceDto);
        return bookInstanceDto;
    }
}
