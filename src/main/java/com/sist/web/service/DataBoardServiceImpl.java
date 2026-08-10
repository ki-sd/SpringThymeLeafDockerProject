package com.sist.web.service;

import java.util.*;

import org.springframework.stereotype.Service;

import com.sist.web.mapper.DataBoardMapper;
import com.sist.web.vo.DataBoardVO;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class DataBoardServiceImpl implements DataBoardService {
	private final DataBoardMapper dMapper;
	private final int ROWSIZE=10;
	@Override
	public List<DataBoardVO> databoardListData(int page) {
		int start=(page*ROWSIZE)-ROWSIZE;
		return dMapper.databoardListData(start);
	}

	@Override
	public int[] databoardPageData(int page) {
		int count=dMapper.databoardCount();
		int totalpage=(int)Math.ceil(count/(double)ROWSIZE);
		final int BLOCK=10;
		int startPage=((page-1)/BLOCK*BLOCK)+1;
		int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage) endPage=totalpage;
		int[] pages= {page,totalpage,startPage,endPage,count};
		return pages;
	}

	@Override
	public void databoardInsert(DataBoardVO vo) {
		dMapper.databoardInsert(vo);
	}

}
