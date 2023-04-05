package NewSales.controllers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

public class APIErrors {

    @Getter
    private List<String> errors;

    public APIErrors(List<String> errors) {
        this.errors = errors; }

    public APIErrors(String errorMsg) {
       this.errors = Arrays.asList(errorMsg);
    }
}
