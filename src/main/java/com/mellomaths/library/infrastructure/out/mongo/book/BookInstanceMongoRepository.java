package com.mellomaths.library.infrastructure.out.mongo.book;

import com.mellomaths.library.domain.dto.BookInstanceDto;
import com.mellomaths.library.domain.repository.BookInstanceRepository;
import com.mellomaths.library.infrastructure.out.mongo.configuration.BookInstanceRepositoryMongoConfiguration;
import com.mellomaths.library.infrastructure.out.mongo.configuration.BookRepositoryMongoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Primary
public class BookInstanceMongoRepository implements BookInstanceRepository {

    private final BookInstanceRepositoryMongoConfiguration repository;
    private final BookRepositoryMongoConfiguration bookRepository;

    @Autowired
    public BookInstanceMongoRepository(final BookInstanceRepositoryMongoConfiguration repository, final BookRepositoryMongoConfiguration bookRepository) {
        this.repository = repository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void save(BookInstanceDto bookInstanceDto) {
        BookInstanceDocument instance = BookInstanceDocument.from(bookInstanceDto);
        repository.save(instance);

        Optional<BookDocument> bookDocument = bookRepository.findById(bookInstanceDto.getBookId());
        if (bookDocument.isPresent()) {
            BookDocument book = bookDocument.get();
            book.addNewInstance(instance);
            bookRepository.save(book);
        }
    }

    @Override
    public BookInstanceDto findById(String id) {
        Optional<BookInstanceDocument> document = repository.findById(id);
        if (document.isEmpty()) {
            return null;
        }

        return document.get().toDto();
    }

    @Override
    public List<BookInstanceDto> findByIsbn(String isbn) {
        List<BookInstanceDocument> documents = repository.findByIsbn(isbn);
        return documents.stream().map(BookInstanceDocument::toDto).collect(Collectors.toList());
    }

    @Override
    public List<BookInstanceDto> findByBookId(String bookId) {
        List<BookInstanceDocument> documents = repository.findByBookId(bookId);
        return documents.stream().map(BookInstanceDocument::toDto).collect(Collectors.toList());
    }
}
