package softuni.examprepBattleships.services;

import org.springframework.stereotype.Service;
import softuni.examprepBattleships.repositories.CategoryRepository;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public boolean categoryExist() {
        if (categoryRepository.count() > 0) {
            return true;
        }
        return false;
    }
}
