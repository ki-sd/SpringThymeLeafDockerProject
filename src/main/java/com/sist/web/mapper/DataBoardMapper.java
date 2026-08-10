package com.sist.web.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Repository;

import java.util.*;
import com.sist.web.vo.*;
@Mapper
@Repository
public interface DataBoardMapper {
	@Select("""
			SELECT no,name,subject,TO_CHAR(regdate,'yyyy-mm-dd') AS dbday,hit,filecount
			FROM springDataBoard
			ORDER BY no DESC
			OFFSET #{start} ROWS FETCH NEXT 10 ROWS ONLY
			""")
	public List<DataBoardVO> databoardListData(int start);
	
	@Select("""
			SELECT COUNT(*) FROM springDataBoard
			""")
	public int databoardCount();
	
	@SelectKey(keyProperty="no",resultType=int.class,before=true,statement="SELECT NVL(MAX(no)+1,1) AS no FROM springDataBoard")
	@Insert("""
			INSERT INTO springDataBoard(no,name,subject,content,pwd,filename,filesize,filecount) 
			VALUES(#{no},#{name},#{subject},#{content},#{pwd},#{filename},#{filesize},#{filecount})
			""")
	public void databoardInsert(DataBoardVO vo);
}
