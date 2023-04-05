package NewSales.controllers;

import NewSales.exceptions.BusinessRulesException;
import NewSales.exceptions.PedidoNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(BusinessRulesException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public APIErrors handleBusinessRulesException(BusinessRulesException br){
        return new APIErrors(br.getMessage());}


    @ExceptionHandler(PedidoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public APIErrors handlePedidoNaoEcontradoException(PedidoNaoEncontradoException pn){
        return new APIErrors(pn.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public APIErrors handleMethodNotValidException (MethodArgumentNotValidException mn){
       List<String> errors =  mn.getBindingResult().getAllErrors()
                .stream()
                .map(newErro -> newErro.getDefaultMessage()).collect(Collectors.toList());
        return new APIErrors(errors);
    }


}
