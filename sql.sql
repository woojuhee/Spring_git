select count(*) from SPRING_BOARD;

-- 더미 데이터(계속해서 만들기)
insert into spring_board(bno, title, content, writer)
(select seq_board.nextVal, title, content, writer from SPRING_BOARD);

-- rownum : 가상컬럼(임시부여)
-- select rownum, bno, title from SPRING_BOARD where rownum>1;
select rownum, bno, title from SPRING_BOARD where rownum<=10;

-- rownum 과 order by는 같이 쓸때 주의(rownum의 우선순위가 높음음)


-- spring_board에서 최신글 10개 가져오기
 select rownum, bno, title, writer
 from (select bno, title, writer from spring_board order by bno desc)
 where rownum<=10;
 
  select rownum, bno, title, writer
 from (select /* +index_desc(spring_board pk_spring_board */
 bno, title, writer from spring_board)
 where rownum<=20;
 
 -- 최신글 1~10개 가져오기
 select rownum, bno, title, writer 
 from (select /*+index_desc(spring_board pk_spring_board*/
 rownum rn, bno, title, writer from spring_board) where rownum <= 10)
 where rn > 0;
 
  -- 최신글 11~20개 가져오기
 select rownum, bno, title, writer 
 from (select /*+index_desc(spring_board pk_spring_board*/
 rownum rn, bno, title, writer from spring_board) where rownum <= 20)
 where rn > 10;
 
 
 select count(*) from spring_board where bno>0;
 
 
 
 
 
 
 