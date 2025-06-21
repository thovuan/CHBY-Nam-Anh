package com.chys.WebCHYS.Model.APIResponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class APIResponse<T> {

    private int status;

    private OffsetDateTime datetime;

    private String message;

    private String errorCode;

    private T data;

    public static <T> APIResponse<T> success(int status,String message, T data) {
        return new APIResponse<>(status, OffsetDateTime.now(), message, null, data);
    }

}
