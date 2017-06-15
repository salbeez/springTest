package com.guest.domain;

import java.net.URLEncoder;

import org.omg.IOP.Encoding;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PageMaker {

	private int totalCount;
	private int startpage;
	private int endpage;
	private boolean prev;
	private boolean next;

	private int displatPageNum = 10;

	private Criteria cri;

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcData();
	}

	private void calcData() {
		endpage = (int) (Math.ceil(cri.getPage() / (double) displatPageNum) * displatPageNum);
		startpage = (endpage - displatPageNum) + 1;

		int tempEndPage = (int) (Math.ceil(totalCount / (double) cri.getPerPageNum()));

		if (endpage > tempEndPage) {
			endpage = tempEndPage;
		}

		prev = startpage == 1 ? false : true;
		next = endpage * cri.getPerPageNum() >= totalCount ? false : true;
	}

	public int getStartpage() {
		return startpage;
	}

	public void setStartpage(int startpage) {
		this.startpage = startpage;
	}

	public int getEndpage() {
		return endpage;
	}

	public void setEndpage(int endpage) {
		this.endpage = endpage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getDisplatPageNum() {
		return displatPageNum;
	}

	public void setDisplatPageNum(int displatPageNum) {
		this.displatPageNum = displatPageNum;
	}

	public Criteria getCri() {
		return cri;
	}

	public void setCri(Criteria cri) {
		this.cri = cri;
	}

	@Override
	public String toString() {
		return "PageMaker [totalCount=" + totalCount + ", startpage=" + startpage + ", endpage=" + endpage + ", prev="
				+ prev + ", next=" + next + ", displatPageNum=" + displatPageNum + ", cri=" + cri + "]";
	}

	public String makeQuery(int page) {
		UriComponents uriComponents = UriComponentsBuilder.newInstance().queryParam("page", page)
				.queryParam("perPageNum", cri.getPerPageNum()).build();
		return uriComponents.toString();
	}

	public String makeSearch(int page) {
		UriComponents uriComponents = UriComponentsBuilder.newInstance().queryParam("page", page)
				.queryParam("perPageNum", cri.getPerPageNum())
				.queryParam("searchType", ((SearchCriteria) cri).getSearchType())
				.queryParam("keyword", encoding(((SearchCriteria) cri).getKeyword())).build();
		return uriComponents.toString();
	}

	private String encoding(String keyword) {
		if (keyword == null || keyword.trim().length() == 0) {
			return "";
		}
		try {
			return URLEncoder.encode(keyword, "UTF-8");
		} catch (Exception e) {
			return "";
		}
	}
}
