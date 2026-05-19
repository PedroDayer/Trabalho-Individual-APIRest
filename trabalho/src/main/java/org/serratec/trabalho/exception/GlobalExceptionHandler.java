package org.serratec.trabalho.exception;

import org.serratec.trabalho.model.MensagemErro;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String mensagem = ex
                .getBindingResult() // pega os campos que cairam na validação == os que deram erro
                .getFieldErrors() // pega exatamente a lista dos erros que deram
                .stream()
                .map(i -> i.getField() + " " + i.getDefaultMessage())
                .collect(Collectors.joining(","));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensagemErro(mensagem, LocalDateTime.now()));
    }


    @ExceptionHandler(SolicitacaoNaoEncontradaException.class)
    public ResponseEntity<MensagemErro> handleClienteNaoEncontrado(SolicitacaoNaoEncontradaException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MensagemErro(ex.getMessage(), LocalDateTime.now()));
    }

    @ExceptionHandler(CampoInvalidoException.class)
    public ResponseEntity<MensagemErro> handleCampoInvalido(CampoInvalidoException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensagemErro(ex.getMessage(),LocalDateTime.now()));
    }

    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<MensagemErro> handlerRegraNegocio(RegraNegocioException ex){
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new MensagemErro(ex.getMessage(), LocalDateTime.now()));
    }

    @ExceptionHandler(DadosDuplicadosException.class)
    public ResponseEntity<MensagemErro> handlerDadosDuplicados(DadosDuplicadosException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new MensagemErro(ex.getMessage(), LocalDateTime.now()));
    }


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<MensagemErro> handleIncompatibilidadeDeTipos(MethodArgumentTypeMismatchException ex){

        if(ex.getRequiredType() == UUID.class){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensagemErro("UUID inválido. Siga o padrão UUID: 00000000-0000-0000-0000-000000000000", LocalDateTime.now()));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensagemErro("Valor informado inválido.", LocalDateTime.now()));
    }

//    @Override
//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    public ResponseEntity<MensagemErro> handleErroDesserializacao(HttpMessageNotReadableException ex) {
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensagemErro("Falha na leitura da requisição. Verifique se os campos enviados (como IDs ou formatos) estão corretos.", LocalDateTime.now()));
//    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<MensagemErro> handleErroServidor(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MensagemErro("Erro interno no servidor!", LocalDateTime.now()));
    }
}