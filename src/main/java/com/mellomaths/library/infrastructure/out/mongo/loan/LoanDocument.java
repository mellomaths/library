package com.mellomaths.library.infrastructure.out.mongo.loan;

import com.mellomaths.library.domain.Loan;
import com.mellomaths.library.domain.dto.LoanDto;
import org.springframework.data.annotation.Id;

public class LoanDocument extends LoanDto {
    @Id
    private final String id;

    public LoanDocument(String id, String creationDate, String patronId, String bookId, String bookInstanceId, int days) {
        super(creationDate, patronId, bookId, bookInstanceId, days);
        this.id = id;
    }

    public static LoanDocument from(LoanDto loanDto) {
        return new LoanDocument(
                loanDto.getId(),
                loanDto.getCreationDate(),
                loanDto.getPatronId(),
                loanDto.getBookId(),
                loanDto.getBookInstanceId(),
                loanDto.getDays()
        );
    }

    public LoanDto toDto() {
        return new LoanDto(id, creationDate, patronId, bookId, bookInstanceId, days);
    }
}
