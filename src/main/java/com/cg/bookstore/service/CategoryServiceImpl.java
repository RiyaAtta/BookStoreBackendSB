  
package com.cg.bookstore.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cg.bookstore.entities.Category;
import com.cg.bookstore.exceptions.CustomException;
import com.cg.bookstore.repository.IBookRepository;
import com.cg.bookstore.repository.ICategoryRepository;

@Service
public class CategoryServiceImpl implements ICategoryService {
	@Autowired
	private ICategoryRepository categoryServiceRepo;
	@Autowired
	private IBookRepository bookRepo;

	@Override
	public String addCategory(String categoryName) {
		// TODO Auto-generated method stub
		Optional<Category> findCategoryByCategoryName = categoryServiceRepo.findByCategoryName(categoryName);
		if (!findCategoryByCategoryName.isPresent()) {
			categoryServiceRepo.save(new Category(categoryName));
			return "Category added!";
		} else
			throw new CustomException("Category " + categoryName + " already exists!!");
	}

	@Override
	public Category editCategory(Category cat) {
		// TODO Auto-generated method stub
		Optional<Category> findCategoryByCategoryName = categoryServiceRepo.findById(cat.getCategoryId());
		if (findCategoryByCategoryName.isPresent()) {
			categoryServiceRepo.save(cat);
			return cat;
		} else
			throw new CustomException("Category with Id: " + cat.getCategoryId() + " not exists!!");
	}

	@Override
	public ResponseEntity<String> removeCategory(Integer categoryId) {
		try {
			// TODO Auto-generated method stub
			Optional<Category> findBookingByCategoryId = categoryServiceRepo.findById(categoryId);
			if (findBookingByCategoryId.isPresent()) {
				categoryServiceRepo.deleteById(categoryId);
			}

			else {
				if (bookRepo.findByCategory(findBookingByCategoryId.get()) != null) {
					return new ResponseEntity<>("Book exists with such a category", HttpStatus.NOT_FOUND);
				}
			}
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>("No Category Present", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>("Category Deleted!!", HttpStatus.OK);
	}

	@Override
	public List<Category> viewAllCategories() {
		// TODO Auto-generated method stub
		return categoryServiceRepo.findAll();
	}

}