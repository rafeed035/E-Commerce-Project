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
                productCheck = product;
                productCheck.setBrand(brandCheck);
                productCheck.setCategory(categoryCheck);
                return productRepository.save(productCheck);
            }
        }
    }

    @Override
    public Product getProductById(int productId) throws EntityNotfoundException {

        //check whether the product already exists in the database or not
        //if it does not exist, then throw an exception
        //else return the entity
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

        //check whether the product already exists in the database or not
        //if it does not exist, then throw an exception
        //else return the entity
        Product productCheck = productRepository.getProductByProductName(productName);
        if(productCheck == null){
            throw new EntityNotfoundException("Product does not exist!");
        }
        else{
            return productCheck;
        }
    }

    @Override
    public Product updateProduct(int productId, Product product) throws EntityNotfoundException, EntityAlreadyExistsException {

        //check whether the product already exists in the database or not
        //if it does not exist, then throw an exception
        //else update the entity
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
                productCheck.setBrand(brandCheck);
                productCheck.setCategory(categoryCheck);
            }
            return productRepository.save(productCheck);
        }
    }

    @Override
    public List<Product> getProductsByCategory(String categoryName) throws EntityNotfoundException {

        //check whether the category already exists in the database or not
        //if it does not exist, then throw an exception
        //else return the entity list
        Category categoryCheck = categoryRepository.getCategoryByCategoryName(categoryName);
        if(categoryCheck == null){
            throw new EntityNotfoundException("Category does not exist!");
        }
        return productRepository.getProductsByCategory(categoryCheck);
    }

    @Override
    public List<Product> getProductsByBrand(String brandName) throws EntityNotfoundException {

        //check whether the brand already exists in the database or not
        //if it does not exist, then throw an exception
        //else return the entity list
        Brand brandCheck = brandRepository.getBrandByBrandName(brandName);
        if(brandCheck == null){
            throw new EntityNotfoundException("Brand does not exist!");
        }
        return productRepository.getProductsByBrand(brandCheck);
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String categoryName, String brandName) throws EntityNotfoundException {

        //check whether the category and brand already exists in the database or not
        //if it does not exist, then throw an exception
        //else return the entity list
        Category categoryCheck = categoryRepository.getCategoryByCategoryName(categoryName);
        Brand brandCheck = brandRepository.getBrandByBrandName(brandName);
        if(categoryCheck == null || brandCheck == null){
            throw new EntityNotfoundException("Brand or Category does not exist");
        }
        return productRepository.getProductsByCategoryAndBrand(categoryCheck, brandCheck);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public void deleteProduct(int productId) throws EntityNotfoundException {

        //check whether the product already exists in the database or not
        //if it does not exist, then throw an exception
        //else delete the entity list
        Product productDelete = productRepository.getProductByProductId(productId);
        if(productDelete == null){
            throw new EntityNotfoundException("Product does not exist");
        }
        productRepository.delete(productDelete);
    }
}
