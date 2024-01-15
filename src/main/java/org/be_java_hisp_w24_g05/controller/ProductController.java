package org.be_java_hisp_w24_g05.controller;

import org.be_java_hisp_w24_g05.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService productService;
}
