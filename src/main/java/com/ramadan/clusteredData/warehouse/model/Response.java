package com.ramadan.clusteredData.warehouse.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Response{
    private Boolean status;
    private String message;
}
