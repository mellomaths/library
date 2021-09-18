package com.mellomaths.library.infrastructure.out.mongo.loan;

import com.mellomaths.library.domain.dto.BookInstanceDto;
import com.mellomaths.library.domain.dto.LoanDto;
import com.mellomaths.library.domain.dto.PatronDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "loan")
public class LoanDocument extends LoanDto {
    @Id
    private final String id;
    private final String patronId;
    private final String bookId;
    private final String bookInstanceId;

    public LoanDocument(String id, String creationDate, String patronId, String bookId, String bookInstanceId, int daysToReturn, String dueDate) {
        super(creationDate, daysToReturn, dueDate);
        this.id = id;
        this.patronId = patronId;
        this.bookId = bookId;
        this.bookInstanceId = bookInstanceId;
    }

    public static LoanDocument from(LoanDto loanDto) {
        return new LoanDocument(
                loanDto.getId(),
                loanDto.getCreationDate(),
                loanDto.getPatron().getId(),
                loanDto.getBookInstance().getBookId(),
                loanDto.getBookInstance().getId(),
                loanDto.getDaysToReturn(),
                loanDto.getDueDate()
        );
    }

    public LoanDto toDto() {
        return new LoanDto(id, creationDate, new PatronDto(patronId), new BookInstanceDto(bookInstanceId, bookId), daysToReturn, dueDate);
    }
}
