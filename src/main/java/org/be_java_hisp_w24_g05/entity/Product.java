package org.be_java_hisp_w24_g05.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private Integer productId;

    private String productName;

    private String type;

    private String brand;

    private String color;

    private String note;
}
