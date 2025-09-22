package baskakov.dev.springboormvc.model;

import java.time.LocalDateTime;

public record ServerErrorDTO(
        String errorMessage,
        String errorDetailMessage,
        LocalDateTime errorDateTime
) {
}
