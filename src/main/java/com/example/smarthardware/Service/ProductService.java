package com.example.smarthardware.Service;

import com.example.smarthardware.Entity.Product;
import com.example.smarthardware.Respository.ProductRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService (ProductRepository productRepository){
        this.productRepository=productRepository;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Product createorUpdate (Product product){
        return this.productRepository.save(product);

    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete (Long id){
        Optional<Product> product=this.productRepository.findById(id);
        if (product.isPresent())
            this.productRepository.delete(product.get());
    }

    public List<Product> findProducts(int page,int size,String filtername){
        List<Product> results=null;
        size=size<1?1:size;
        Pageable pageable = PageRequest.of(page, size);

        if (filtername!=null&&!filtername.trim().equals(""))     {
            results=this.productRepository.findByNameContainingIgnoreCase(filtername,pageable);

        }
        else {
            results= (this.productRepository.findAll(pageable)).getContent();
        }

        return results;


    }

    public Product findbyid(Long id){
        return productRepository.findById(id).get();
    }
}
