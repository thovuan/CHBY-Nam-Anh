package com.chys.WebCHYS.Model.APIResponse;

import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class APIResponse<T> {

    private int status;

    private OffsetDateTime datetime;

    private String message;

    private T data;

    public static <T> APIResponse<T> success(int status,String message, T data) {
        return new APIResponse<>(status, OffsetDateTime.now(), message, data);
    }

}
