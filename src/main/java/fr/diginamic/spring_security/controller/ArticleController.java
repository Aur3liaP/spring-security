package fr.diginamic.spring_security.controller;

import fr.diginamic.spring_security.entity.Article;
import fr.diginamic.spring_security.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    ArticleRepository articleRepository;

//    @PostMapping("/new")
//    public String registerUser(@ModelAttribute Article article) throws Exception {
//        articleRepository.save(new Article(article.getTitre(), article.getContenu()));
//        return "article créé";
//    }

    @PostMapping("/new")
    public String registerUser(@ModelAttribute Article article, Authentication authentication) throws Exception {    // Pour avoir auteur de l'article
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        articleRepository.save(new Article(article.getTitre(), article.getContenu(), username));
        return "redirect:/article/all";
    }

    @GetMapping("/all")
    public String allArticles(Model model) {
        model.addAttribute("articles", articleRepository.findAll());
        return "article-all";
    }

    @GetMapping("/{id}")
    public String articleDetail(@PathVariable Integer id, Model model) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Article non trouvé"));
        model.addAttribute("article", article);
        return "article-detail";
    }

}
