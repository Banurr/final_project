package banurr.final_project.services;

import banurr.final_project.models.Comment;
import banurr.final_project.models.Product;
import banurr.final_project.repositories.ProductRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@Service
public class ProductService
{
    @Autowired
    private ProductRepository productRepository;

    public List<Product> allProducts()
    {
        return productRepository.findAll();
    }

    public Product findProduct(Long id)
    {
        return productRepository.findById(id).orElse(null);
    }

    public void deleteProduct(Long id)
    {
        productRepository.deleteById(id);
    }

    public void addProduct(Product product)
    {
        productRepository.save(product);
    }

    public void updateProduct(Product product)
    {
        productRepository.save(product);
    }

    public void setPhotoCategory(@NotNull MultipartFile multipartFile, Long id)
    {
        Product product = findProduct(id);
        product.setPicture(multipartFile.getOriginalFilename());
        productRepository.save(product);
    }
}
