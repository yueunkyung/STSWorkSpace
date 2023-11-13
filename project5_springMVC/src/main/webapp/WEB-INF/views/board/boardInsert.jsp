<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>board 입력</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<div class="container mt-3">
	<h2>게시글 작성</h2>
   <div class="mb-3 mt-3">
     <label for="title">제목:</label>
     <input type="text" class="form-control" id="title" placeholder="Enter title">
   </div>
   <div class="mb-3">
   	<label for="content">내용:</label>
	<textarea class="form-control" rows="5" id="content"></textarea>
   </div>
   <button type="submit" 
   onclick="boardInsertPost()"
   class="btn btn-primary">등록</button>
</div>

</body>
</html>
