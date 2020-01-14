<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="../includes/header.jsp"%>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Board List</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				Board List Page
				<button id='regBtn' type='button' class='btn btn-xs pull-right'>Register
					New Board</button>
			</div>
			<!-- /.panel-heading -->
			<div class="panel-body">
				<table class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th>번 호</th>
							<th>제 목</th>
							<th>작성자</th>
							<th>작성일</th>
							<th>수정일</th>
						</tr>
					</thead>
					<!-- 게시판 리스트 반복문 -->
					<c:forEach var="vo" items="${list}">
						<tr>
							<td>${vo.bno}</td>
							<td><a href="/board/read?bno=${vo.bno}">${vo.title}</a></td>
							<td>${vo.writer}</td>
							<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
									value="${vo.regdate}" /></td>
							<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
									value="${vo.updatedate}" /></td>
						</tr>
					</c:forEach>
				</table>
				<div class="row">
					<!-- start search -->
					<div class="col-md-12">
						<div class="col-md-8">
							<!--search Form-->

						</div>
						<div class="col-md-2 col-md-offset-2">
							<!--페이지 목록 갯수 지정하는 폼-->
						</div>
					</div>
				</div>
				<!-- end search -->
				<!-- start Pagination -->
				<div class="text-center">
					<ul class="pagination">
						<c:if test="${pageDTO.prev}">
							<li class="paginate_button previous"><a
								href="${pageDTO.startPage-1}">Previous</a></li>
						</c:if>
						<c:forEach var="idx" begin="${pageDTO.startPage}"	end="${pageDTO.endPage}">
							<li class="paginate_button"><a href="${idx}">${idx}</a></li>
						</c:forEach>						
						<c:if test="${pageDTO.next}">
							<li class="paginate_button next"><a    
								href="${pageDTO.endPage+1}">Next</a></li>
						</c:if>
					</ul>
				</div>
				<!-- end Pagination -->
			</div>
			<!-- end panel-body -->
		</div>
		<!-- end panel -->
	</div>
</div>
<!-- /.row -->
<!-- 모달 추가 -->
<div class="modal" tabindex="-1" role="dialog" id="myModal">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">게시글 등록</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<p>처리가 완료되었습니다.</p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" data-dismiss="modal">확인</button>
			</div>
		</div>
	</div>
</div>
<!-- 스크립트 -->
<script>
	$(function() {
		var result = '${result}';
		checkModal(result);
		//history 값 변경
		//history.pushState : 새로운 경로 삽입
		history.replaceState({}, null, null);
		function checkModal(result) {
			if (result === '' || history.state)
				return;
			if (parseInt(result) > 0) {
				$(".modal-body").html("게시글" + parseInt(result) + "번이 등록되었습니다.");
			}
			$("#myModal").modal("show");
		}
		//regBtn 클릭시 새글 쓰기 폼 보여주기
		$("#regBtn").click(function() {
			location.href = "/board/register";
		});

	})
</script>
<%@include file="../includes/footer.jsp"%>





