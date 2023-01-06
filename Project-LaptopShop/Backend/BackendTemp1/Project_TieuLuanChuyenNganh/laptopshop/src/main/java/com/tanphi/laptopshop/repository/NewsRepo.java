package com.tanphi.laptopshop.repository;

import com.tanphi.laptopshop.entity.News;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepo extends JpaRepository<News,Integer>{
	News findNewsByNewsIdAndIsdeleted(Integer newsId,Integer status);
	Page<News> findByIsdeleted(Integer status,Pageable pageable);
    @Query("Select n from News n where n.title like %:title% and n.isdeleted =:status")
    Page<News> findByTitleContainingAndIsdeleted(String title,Integer status,Pageable pageable);
    News findByTitleAndIsdeleted(String title,Integer status);
    News findByTitleAndNewsIdNotAndIsdeleted(String title,Integer id,Integer status);
}
