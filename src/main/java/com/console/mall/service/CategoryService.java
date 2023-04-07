package com.console.mall.service;

import com.console.mall.entitiy.Category;
import com.console.mall.respository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    public Category findOneCategory(Long id) {
        return categoryRepository.findOne(id);
    }

    public List<Category> findAllCategory() {
        return categoryRepository.findAll();
    }


}
