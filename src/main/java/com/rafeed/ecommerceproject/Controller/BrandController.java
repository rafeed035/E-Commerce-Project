package com.rafeed.ecommerceproject.Controller;

import com.rafeed.ecommerceproject.CustomException.Exceptions.EntityAlreadyExistsException;
import com.rafeed.ecommerceproject.CustomException.Exceptions.EntityNotfoundException;
import com.rafeed.ecommerceproject.Entity.Brand;
import com.rafeed.ecommerceproject.Service.BrandService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brand")
public class BrandController {

    private BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @PostMapping("/saveBrand")
    public Brand saveBrand(@RequestBody Brand brand) throws EntityAlreadyExistsException {
        return brandService.saveBrand(brand);
    }

    @GetMapping("/getBrandById")
    public Brand getBrandById(@RequestParam int brandId) throws EntityNotfoundException {
        return brandService.getBrandById(brandId);
    }

    @GetMapping("/getBrandByName")
    public Brand getBrandByName(@RequestParam String brandName) throws EntityNotfoundException {
        return brandService.getBrandByName(brandName);
    }

    @PutMapping("/updateBrand")
    public Brand updateBrand(@RequestParam int brandId, @RequestBody Brand brand) throws EntityNotfoundException, EntityAlreadyExistsException {
        return brandService.updateBrand(brandId, brand);
    }

    @GetMapping("/getBrandsByCategory")
    public List<Brand> getBrandByCategory(@RequestParam String categoryName) throws EntityNotfoundException {
        return brandService.getBrandsByCategory(categoryName);
    }

    @GetMapping("/getAllBrands")
    public List<Brand> getAllBrands(){
        return brandService.getAllBrands();
    }

    @DeleteMapping("/deleteBrand")
    public String deleteBrand(@RequestParam int brandId) throws EntityNotfoundException {
        brandService.deleteBrand(brandId);
        return "Deleted";
    }
}
