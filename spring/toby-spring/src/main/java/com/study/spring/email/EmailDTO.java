package com.study.spring.email;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class EmailDTO {

    private final String receiver;
    private final String subject;
    private final String contents;

    @Builder
    public EmailDTO(String receiver, String subject, String contents) {
        this.receiver = receiver;
        this.subject = subject;
        this.contents = contents;
    }
}
