package com.mellomaths.library.infrastructure.out.mongo.loan;

import com.mellomaths.library.domain.dto.LoanDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "loan")
public class LoanDocument extends LoanDto {
    @Id
    private final String id;

    public LoanDocument(String id, String creationDate, String patronId, String bookId, String bookInstanceId, int days, String dueDate) {
        super(creationDate, patronId, bookId, bookInstanceId, days, dueDate);
        this.id = id;
    }

    public static LoanDocument from(LoanDto loanDto) {
        return new LoanDocument(
                loanDto.getId(),
                loanDto.getCreationDate(),
                loanDto.getPatronId(),
                loanDto.getBookId(),
                loanDto.getBookInstanceId(),
                loanDto.getDays(),
                loanDto.getDueDate()
        );
    }

    public LoanDto toDto() {
        return new LoanDto(id, creationDate, patronId, bookId, bookInstanceId, days, dueDate);
    }
}
