<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>


<div class="container">
	<form>
		<input type="hidden" id="id" value="${board.id}"/>
		<div class="form-group">
			<label for="username">Title</label> <input value="${board.title}" type="text" class="form-control" placeholder="Enter title" id="title">
		</div>

		<div class="form-group">
			<label for="content">Content:</label>
			<textarea class="form-control summernote" rows="5" id="content">${board.content}</textarea>
		</div>

	</form>

<!-- javascript로 요청처리를 할 것임으로 form 밖으로 뺌 -->
	<button id="btn-update" class="btn btn-primary">글수정 완료</button>
</div>


     <script>
      $('.summernote').summernote({
        tabsize: 2,
        height: 300
      });
    </script>
    
    
<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>

