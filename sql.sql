select count(*) from SPRING_BOARD;

-- ���� ������(����ؼ� �����)
insert into spring_board(bno, title, content, writer)
(select seq_board.nextVal, title, content, writer from SPRING_BOARD);

-- rownum : �����÷�(�ӽúο�)
-- select rownum, bno, title from SPRING_BOARD where rownum>1;
select rownum, bno, title from SPRING_BOARD where rownum<=10;

-- rownum �� order by�� ���� ���� ����(rownum�� �켱������ ������)


-- spring_board���� �ֽű� 10�� ��������
 select rownum, bno, title, writer
 from (select bno, title, writer from spring_board order by bno desc)
 where rownum<=10;
 
  select rownum, bno, title, writer
 from (select /* +index_desc(spring_board pk_spring_board */
 bno, title, writer from spring_board)
 where rownum<=20;
 
 -- �ֽű� 1~10�� ��������
 select rownum, bno, title, writer 
 from (select /*+index_desc(spring_board pk_spring_board*/
 rownum rn, bno, title, writer from spring_board) where rownum <= 10)
 where rn > 0;
 
  -- �ֽű� 11~20�� ��������
 select rownum, bno, title, writer 
 from (select /*+index_desc(spring_board pk_spring_board*/
 rownum rn, bno, title, writer from spring_board) where rownum <= 20)
 where rn > 10;
 
 
 select count(*) from spring_board where bno>0;
 
 
 
 
 
 
 