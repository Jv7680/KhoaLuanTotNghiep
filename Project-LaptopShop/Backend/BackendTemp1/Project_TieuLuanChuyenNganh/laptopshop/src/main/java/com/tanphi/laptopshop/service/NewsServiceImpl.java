package com.tanphi.laptopshop.service;

import com.tanphi.laptopshop.entity.Accounts;
import com.tanphi.laptopshop.entity.News;
import com.tanphi.laptopshop.entity.enums.IsDeleteStatus;
import com.tanphi.laptopshop.exception.BadRequestException;
import com.tanphi.laptopshop.repository.AccountsRepo;
import com.tanphi.laptopshop.repository.NewsRepo;
import com.tanphi.laptopshop.request.news.NewsRequest;

import java.sql.Timestamp;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl implements NewsService {
	@Autowired
	private NewsRepo newsRepo;
	@Autowired private AccountsRepo accountsRepo;

	@Override
	public News GetNewsById(int id) {
		// TODO Auto-generated method stub
		return newsRepo.findNewsByNewsIdAndIsdeleted(id, IsDeleteStatus.NO.getCode());
	}

	@Override
	public Page<News> GetListNewsPage(Pageable pageable) {
		// TODO Auto-generated method stub
		return newsRepo.findByIsdeleted(IsDeleteStatus.NO.getCode(), pageable);
	}

	@Override
	public Page<News> GetListNewsPageByKeyword(Pageable pageable, String keyword) {
		// TODO Auto-generated method stub
		return newsRepo.findByTitleContainingAndIsdeleted(keyword, IsDeleteStatus.NO.getCode(), pageable);
	}

	@Override
	public void AddNews(NewsRequest request) {
		// TODO Auto-generated method stub
		News dbNews=newsRepo.findByTitleAndIsdeleted(request.getTitle(), IsDeleteStatus.NO.getCode());
		Accounts accounts=accountsRepo.findAccountsByAccountId(request.getAccountId());
		if(dbNews!=null)
		{
			throw new BadRequestException("Bản tin đã tồn tại, vui lòng thay đổi tên bản tin");
		}
		if(accounts==null)
		{
			throw new BadRequestException("Người dùng không tồn tại");
		}
		News newNews=new News();
		newNews.setContent(request.getContent());
		newNews.setCreated(Timestamp.from(Instant.now()));
		newNews.setImageLink(request.getImageLink());
		newNews.setTitle(request.getTitle());
		newNews.setIsdeleted(IsDeleteStatus.NO.getCode());
		newNews.setAccounts(accounts);
		newsRepo.save(newNews);
	}

	@Override
	public void UpdateNews(NewsRequest request) {
		// TODO Auto-generated method stub
		News dbNewsById=newsRepo.findNewsByNewsIdAndIsdeleted(request.getNewsId(), IsDeleteStatus.NO.getCode());
		News dbNewsByTittle=newsRepo.findByTitleAndNewsIdNotAndIsdeleted(request.getTitle(),request.getNewsId(), IsDeleteStatus.NO.getCode());
		if(dbNewsById==null)
		{
			throw new BadRequestException("Bản tin không tồn tại để thay đổi");
		}
		if(dbNewsByTittle!=null)
		{
			throw new BadRequestException("Bản tin đã tồn tại, vui lòng thay đổi tên bản tin");
		}
		dbNewsById.setContent(request.getContent());
		dbNewsById.setImageLink(request.getImageLink());
		dbNewsById.setTitle(request.getTitle());
		newsRepo.save(dbNewsById);
	}

	@Override
	public void DeleteNews(Integer newsId) {
		// TODO Auto-generated method stub
		News dbNewsById=newsRepo.findNewsByNewsIdAndIsdeleted(newsId, IsDeleteStatus.NO.getCode());
		if(dbNewsById==null)
		{
			throw new BadRequestException("Bản tin không tồn tại để thay đổi");
		}
		dbNewsById.setIsdeleted(IsDeleteStatus.YES.getCode());
		newsRepo.save(dbNewsById);
	}

}
