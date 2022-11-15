package com.mustache.bbs4.controller;

import com.mustache.bbs4.domain.Entity.Article;
import com.mustache.bbs4.domain.dto.ArticleDto;
import com.mustache.bbs4.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/articles")
@Slf4j
public class ArticleController {

    private final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping("/new")
    public String createArticle() {
        return "new";
    }

    @PostMapping("/posts")
    public String insert(ArticleDto articleDto) {
        Article article = articleDto.toEntity();
        articleRepository.save(article);
        return String.format("redirect:/articles/%d",article.getId());
    }

    @GetMapping("/{id}")
    public String selectOne(@PathVariable Long id, Model model) {
        Optional<Article> optArticle = articleRepository.findById(id);
        if(optArticle.isEmpty()) {
            model.addAttribute("message",String.format("%d에 해당하는 정보가 없습니다.", id));
            return "error";
        } else {
            model.addAttribute("article", optArticle.get());
            return "show";
        }
    }

    @GetMapping("/list")
    public String selectAll(Model model){
        List<Article> articleList = articleRepository.findAll();
        model.addAttribute("articles", articleList);
        return "list";
    }

    @GetMapping("")
    public String greeting() {
        return "redirect:/articles/list";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Optional<Article> optArticle = articleRepository.findById(id);
        if(optArticle.isEmpty()) {
            model.addAttribute("message",String.format("%d에 해당하는 정보가 없습니다.", id));
            return "error";
        } else {
            model.addAttribute("article", optArticle.get());
            return "edit";
        }
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable Long id, ArticleDto articleDto, Model model) {
        log.info("title:{} content:{}", articleDto.getTitle(), articleDto.getContent());
        Article article = articleRepository.save(articleDto.toEntity());
        model.addAttribute("article", article);
        return String.format("redirect:/articles/{id}",article.getId());
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id, Model model) {
        articleRepository.deleteById(id);
        model.addAttribute("message",String.format("%d번 글이 지워졌습니다.", id));
        Optional<Article> optArticle = articleRepository.findById(id);
        if (optArticle.isEmpty()) {
            return "show";
        } else {
            return "show";
        }
//
//        return "redirect:/articles";
    }
}
