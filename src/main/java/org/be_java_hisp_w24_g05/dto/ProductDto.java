package org.be_java_hisp_w24_g05.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ProductDto(
        @JsonProperty("product_id") Integer productId,
        @JsonProperty("product_name") String productName,
        String type,
        @Size(max = 25,message = "La longitud de brand no puede superar los 25 caracteres.")
        @NotBlank(message = "El campo brand no puede estar vacío.")
        @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "El campo brand no puede poseer caracteres especiales.")
        String brand,
        @Size(max = 15,message = "La longitud de color no puede superar los 15 caracteres.")
        @NotBlank(message = "El campo color no puede estar vacío.")
        @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "El campo color no puede poseer caracteres especiales.")
        String color,
        @Size(max = 80,message = "La longitud no puede superar los 80 caracteres.")
        @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "El campo notes no puede poseer caracteres especiales.")
        String notes
) {
}
