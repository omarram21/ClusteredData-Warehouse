package com.ramadan.clusteredData.warehouse.model.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ramadan.clusteredData.warehouse.model.Response;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DealDTO {
    @JsonProperty(required = true)
    @Size(max= 3, min=3, message = "fromCurrency takes 3 letters")
    private String fromCurrency;

    @JsonProperty(required = true)
    @Size(max= 3, min=3, message = "toCurrency code takes 3 letters")
    private String toCurrency;

    @JsonProperty(required = true)
    private LocalDateTime dealTimestamp;

    @JsonProperty(required = true)
    private BigDecimal dealAmount;
    public Response isValid() {
        if (fromCurrency != null && toCurrency != null && dealTimestamp != null && dealAmount != null) {
            if (fromCurrency.equals(toCurrency)) {
                return new  Response(false,"From and To currencies should not be the same");
            }
            else if (fromCurrency.length() != 3 || toCurrency.length() != 3){
                return new  Response(false,"fromCurrency and toCurrency should be 3 letters length.") ;
            }
            else if (dealAmount.compareTo(BigDecimal.ZERO) <= 0) {
                return new  Response(false,"Deal amount should be a positive value.");
            }

        } else {
            return new Response(false,"If any of the required fields is null, it's not valid.");
        }
        return new Response(true,"Vaild");
    }

}
