package org.be_java_hisp_w24_g05.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.*;


public record ProductDto(
        @NotNull(message = "El product_id no debe estar vacío")
        @Min(value=1, message = "El product_id debe ser mayor que 0")
        @JsonProperty("product_id") Integer productId,
        @NotBlank(message = "El product_name no debe estar vacío")
        @Size(max = 40, message = "La longitud máxima del product_name es 40 caracteres")
        @Pattern(regexp = "^[a-zA-Z0-9\\s]*$", message = "El product_name no debe contener caracteres especiales")
        @JsonProperty("product_name") String productName,
        @NotBlank(message = "El type no debe estar vacío")
        @Size(max = 15, message = "La longitud máxima del type es 15 caracteres")
        @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "El type no debe contener caracteres especiales")
        String type,
        String brand,
        String color,
        String notes
) {
}
