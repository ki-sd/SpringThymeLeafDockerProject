package com.sist.web.service;

import java.util.*;

import com.sist.web.vo.DataBoardVO;

public interface DataBoardService {
	public List<DataBoardVO> databoardListData(int page);
	public int[] databoardPageData(int page);
	public void databoardInsert(DataBoardVO vo);
}
