package com.cg.bookstore.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.Category;
import com.cg.bookstore.service.CategoryServiceImpl;
import com.cg.bookstore.service.ICategoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.Contact;

@Api(value="category",description="Operations pertaining to category")
@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "http://localhost:4200") 
public class CategoryController {
	@Autowired
	private ICategoryService categoryService;
	
	@ApiOperation(value="add category name",notes="To add a category by the admin",response=Contact.class)
    @PostMapping("/addcategory")
    ResponseEntity<String> addCategory(@Valid @RequestParam String categoryName){
        // persisting the category
    	String cat=categoryService.addCategory(categoryName);
        return ResponseEntity.ok("Category "+categoryName+" is valid");
    }
	@ApiOperation(value="view all categories",notes="To view the categories",response=Contact.class)
    @GetMapping("/viewallcategories")
    public List<Category> getAllCategories(){
    	return categoryService.viewAllCategories();
    }
	
	@ApiOperation(value="update a category",notes="To update the category",response=Contact.class)
	@PutMapping("/updatecategory")
	public Category updateCategory(@RequestBody Category updateCategory) {

		return categoryService.editCategory(updateCategory);
	}
	
	@ApiOperation(value="delete a category",notes="To delete categories if no book exists for it. If a book is present with a certain category then that category cannot be deleted!",response=Contact.class)
	@DeleteMapping("/deletecategory/{categoryId}")
	public ResponseEntity<String> deleteBookingByID(@PathVariable("categoryId") Integer categoryId) {

		return categoryService.removeCategory(categoryId);
	}
	

}
