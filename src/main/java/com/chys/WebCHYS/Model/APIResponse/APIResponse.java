package com.chys.WebCHYS.Model.APIResponse;

import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class APIResponse<T> {

    private boolean status;

    private OffsetDateTime datetime;

    private String message;



    private T data;
}
