package com.tanphi.laptopshop.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tanphi.laptopshop.entity.News;
import com.tanphi.laptopshop.request.news.NewsRequest;

public interface NewsService {

	News GetNewsById(int id);

	Page<News> GetListNewsPage(Pageable pageable);

	Page<News> GetListNewsPageByKeyword(Pageable pageable, String keyword);

	void AddNews(NewsRequest request);

	void UpdateNews(NewsRequest request);

	void DeleteNews(Integer id);

}
