package com.thesis.alumni.system.initial;

import com.thesis.alumni.system.entity.Article;
import com.thesis.alumni.system.entity.Category;
import com.thesis.alumni.system.entity.Role;
import com.thesis.alumni.system.entity.User;
import com.thesis.alumni.system.repository.ArticleRepository;
import com.thesis.alumni.system.repository.CategoryRepository;
import com.thesis.alumni.system.repository.RoleRepository;
import com.thesis.alumni.system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements ApplicationRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CategoryRepository categoryRepository;
    private final ArticleRepository articleRepository;
    public void run(ApplicationArguments args) {
        initUser();
        intArticle();
    }

    private void initUser() {
        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");
        if (roleRepository.findRoleByName("ROLE_ADMIN") == null) {
            roleAdmin = roleRepository.save(roleAdmin);
        }
        if (roleRepository.findRoleByName("ROLE_USER") == null) {
            roleUser = roleRepository.save(roleUser);
        }

        List<Role> roles = List.of(roleAdmin, roleUser);
        User user = userRepository.findUserByEmail("nguyenhuuvu.doc@gmail.com").orElse(null);
        if (user == null)
            userRepository.save(new User("1", "Nguyen VU", "nguyenhuuvu.doc@gmail.com", "$2a$12$bCKqq2nbSEtXzf1GnfLNGuH9RO7i6lSZxJVi7h0Pz5vC04Vt9HYTS", true, roles, "-1", "Chưa làm khảo sát"));
    }

    private void intArticle() {
        Category category1 = Category.builder().name("Tin tức").slug("tin-tuc").build();
        Category category2 = Category.builder().name("Gương mặt cựu sinh viên").slug("guong-mat-cuu-sinh-vien").build();
        Category category3 = Category.builder().name("Sự kiện").slug("su-kien").build();
        Category category4 = Category.builder().name("Giới thiệu").slug("gioi-thieu").build();
        if (categoryRepository.findCategoryBySlug("su-kien") == null)
            category3 = categoryRepository.save(category3);
        if (categoryRepository.findCategoryBySlug("tin-tuc") == null)
            category1 = categoryRepository.save(category1);
        if (categoryRepository.findCategoryBySlug("guong-mat-cuu-sinh-vien") == null)
            category2 = categoryRepository.save(category2);
        if (categoryRepository.findCategoryBySlug("gioi-thieu") == null)
            category4 = categoryRepository.save(category4);
        if (articleRepository.findArticleBySlug("bai-viet-test") == null) {
            Article article = Article.builder().content("<div>day la content")
                    .title("day la title")
                    .thumbnail("https://thietkewebhcm.com.vn/an-featured-image-trong-bai-viet-wordpress/imager_8383.jpg")
                    .category(category1)
                    .slug("bai-viet-test")
                    .build();
            articleRepository.save(article);
        }
    }
}