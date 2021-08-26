package com.example.smarthardware.Controller;

import com.example.smarthardware.Entity.Product;
import com.example.smarthardware.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(path = "/")
    public ResponseEntity<List<Product>> getProducts(@RequestParam int size,@RequestParam int page, @RequestParam String filtername){
        List<Product> products=this.productService.findProducts(page,size,filtername);
        return ResponseEntity.ok(products);

    }

    @PostMapping(path = "/")
    public ResponseEntity<Product> create(@RequestBody Product product){
        Product product1=this.productService.createorUpdate(product);
        return ResponseEntity.ok(product1);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        this.productService.delete(id);
        return ResponseEntity.ok("product deleted");
    }
}
