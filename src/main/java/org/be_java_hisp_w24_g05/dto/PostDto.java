package org.be_java_hisp_w24_g05.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;




public record PostDto(
        @NotNull(message = "userId no puede estar vacio")
        @Min(value=1, message = "userId tiene que ser mayor a 0")
        @JsonProperty("user_id") Integer userId,
        @NotBlank(message = "La fecha no debe estar vacía")
        String date,
        @Valid ProductDto product,
        Integer category,
        Double price
) {
}
