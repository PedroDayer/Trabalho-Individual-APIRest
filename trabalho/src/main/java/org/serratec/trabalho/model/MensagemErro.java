package org.serratec.trabalho.model;

import java.time.LocalDateTime;

public record MensagemErro(String mensagem, LocalDateTime data) {
}
