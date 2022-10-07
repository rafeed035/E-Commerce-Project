package com.rafeed.ecommerceproject.ServiceImplementation;

import com.rafeed.ecommerceproject.CustomException.Exceptions.EntityAlreadyExistsException;
import com.rafeed.ecommerceproject.CustomException.Exceptions.EntityNotfoundException;
import com.rafeed.ecommerceproject.Entity.Brand;
import com.rafeed.ecommerceproject.Entity.Category;
import com.rafeed.ecommerceproject.Entity.Product;
import com.rafeed.ecommerceproject.Repository.BrandRepository;
import com.rafeed.ecommerceproject.Repository.CategoryRepository;
import com.rafeed.ecommerceproject.Repository.ProductRepository;
import com.rafeed.ecommerceproject.Service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImplementation implements ProductService {

    private ProductRepository productRepository;
    private BrandRepository brandRepository;
    private CategoryRepository categoryRepository;

    public ProductServiceImplementation(ProductRepository productRepository,
                                        BrandRepository brandRepository,
                                        CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product saveProduct(Product product) throws EntityAlreadyExistsException, EntityNotfoundException {

        //check if the product already exists in the database
        String newProductName = product.getProductName();
        Product productCheck = productRepository.getProductByProductName(newProductName);
        if(productCheck != null){
            throw new EntityAlreadyExistsException("Product Already Exists!");
        }
        else{
            //check if the product brand and category exist in the database
            //if they do not exist, throw exception
            //else save the entity
            String newProductBrandName = product.getBrand().getBrandName();
            Brand brandCheck = brandRepository.getBrandByBrandName(newProductBrandName);
            String newProductCategoryName = product.getCategory().getCategoryName();
            Category categoryCheck = categoryRepository.getCategoryByCategoryName(newProductCategoryName);
            if(brandCheck == null || categoryCheck == null){
                throw new EntityNotfoundException("Brand or Category does not exist!");
            }
            else{
                return productRepository.save(product);
            }
        }
    }

    @Override
    public Product getProductById(int productId) throws EntityNotfoundException {
        Product productCheck = productRepository.getProductByProductId(productId);
        if(productCheck == null){
            throw new EntityNotfoundException("Product does not exist!");
        }
        else{
            return productCheck;
        }
    }

    @Override
    public Product getProductByName(String productName) throws EntityNotfoundException {
        Product productCheck = productRepository.getProductByProductName(productName);
        if(productCheck == null){
            throw new EntityNotfoundException("Product does not exist!");
        }
        else{
            return productCheck;
        }
    }

    @Override
    public Product updateProduct(int productId, Product product) throws EntityNotfoundException {
        Product productCheck = productRepository.getProductByProductId(productId);
        if(productCheck == null){
            throw new EntityNotfoundException("Product does not exist!");
        }
        else{
            String updateProductBrandName = product.getBrand().getBrandName();
            Brand brandCheck = brandRepository.getBrandByBrandName(updateProductBrandName);
            String updateProductCategoryName = product.getCategory().getCategoryName();
            Category categoryCheck = categoryRepository.getCategoryByCategoryName(updateProductCategoryName);

            if(brandCheck == null || categoryCheck == null){
                throw new EntityNotfoundException("Brand or Category does not exist!");
            }
            else{
                productCheck.setProductName(product.getProductName());
                productCheck.setProductSpecs(product.getProductSpecs());
                productCheck.setProductDescription(product.getProductDescription());
                productCheck.setProductPrice(product.getProductPrice());
                productCheck.setBrand(product.getBrand());
                productCheck.setCategory(product.getCategory());
            }
            return productRepository.save(productCheck);
        }
    }

    @Override
    public List<Product> getProductsByCategory(String categoryName) throws EntityNotfoundException {
        Category categoryCheck = categoryRepository.getCategoryByCategoryName(categoryName);
        if(categoryCheck == null){
            throw new EntityNotfoundException("Category does not exist");
        }
        return null;
    }

    @Override
    public List<Product> getProductsByBrand(String brandName) {
        return null;
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String categoryName, String brandName) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public void deleteProduct(int productId) {

    }
}
