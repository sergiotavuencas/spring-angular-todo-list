package com.tavuencas.sergio.todo_list.handlers;


import com.tavuencas.sergio.todo_list.exception.ErrorCodes;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Schema(description = "Holds error code, error message and related error messages of an error")
public class ErrorDto {

    @Schema(defaultValue = "The error code.", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer httpCode;

    @Schema(defaultValue = "The error code.", requiredMode = Schema.RequiredMode.REQUIRED)
    private ErrorCodes code;

    @Schema(defaultValue = "A detailed error message.")
    private String message;

    @Schema(defaultValue = "The input fields related to the error, if any.")
    private List<String> errors = new ArrayList<>();
}
