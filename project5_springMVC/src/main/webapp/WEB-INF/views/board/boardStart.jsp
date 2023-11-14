<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../auth/logout.jsp"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>board start page</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<style>
#here {
	background: #aaa;
}
</style>
<script>
function boardDelete(bno) {
	//alert("delete 할 예정"+bno);
	$.ajax({
		url:"${appPath}/board/boardDelete.do/" + bno,
		type: "delete",
		success: function(resData) {
			alert(resData);
			boradList();
		}
	});
}

function getFormatDate(arg) {
	if(arg == null) return "";
	var date = new Date(arg);
	var year = date.getFullYear();
	var month = (1+date.getMonth());
	month = month >= 10 ? month : "0" + month;
	var day = date.getDate();
	day = day >= 10? day: "0"+day;
	return year+"-"+month+"-"+day;
}

function boardUpdate() {
	//alert("수정하기");
	// var formData = ${"#myfrm"}.serialize(); //?a=100&b=200
	var formData = $("#myfrm").serializeArray();
	console.log(formData);
	var obj = {};
	$.each(formData, function(idx, item) {
		obj[item.name] = item.value;
	});
	
	$.ajax({
		url:"${appPath}/board/boardUpdate.do",
		data: JSON.stringify(obj),
		contentType:"application/json; charset=utf-8",
		type: "put",
		success: function(resData) {
			alert("resData"+resData);
		}
	});
}

function boardDetail(bno) {
	//alert(bno);
	var loginUser = "${sessionScope.loginCust.cust_id}";
	$.ajax({
		url: "${appPath}/board/boardDetail.do/" + bno,
		type: "get",
		success: function(board) {
			console.log(loginUser, board.writer);
			console.log(board.pic);
			var disabled="";
			if(loginUser != board.writer) disabled="disabled";
			
			var regD = new Date(board.regdate).toISOString().split("T")[0];
			var updateD = board.updatedate;
			if(updateD!=null) {
				updateD = new Date(board.updatedate).toISOString().split("T")[0];
			} else {
				// updateD = "";
			}
			var imgFragment="";
			if(board.pic!=null) {
				imgFragment = `
					<div class="mb-3 mt-3" style="max-height:400px;height:100%;display:flex;justify-content:center;">
				  		<img src = "${appPath}/uploads/\${board.pic}" alt="\${board.pic}" style="display:block; height:100%;"/>
				  	</div>
				`;
			}
			
			var output = `
				<div class="container mt-3">
				  <h2>게시글 수정</h2>
					  <form id="myfrm">
						`+ imgFragment;
						

			output += `<div class="mb-3 mt-3">
					      <label for="bno">번호:</label>
					      <input type="text" class="form-control" id="bno" name="bno"
					     	value="\${board.bno}"
					      readonly="readonly">
					    </div>
					    <div class="mb-3 mt-3">
					      <label for="title">제목:</label>
					      <input type="text" class="form-control" id="title" name="title" 
					    	  value="\${board.title}"
					      />
					    </div>
					    <div class="mb-3">
					      <label for="content">내용:</label>
					      <textarea class="form-control" rows="5" id="content" name="content">\${board.content}</textarea>
					    </div>
					    <div class="mb-3 mt-3">
					      <label for="writer">작성자:</label>
					      <input type="text" class="form-control" id="writer" name="writer"  readonly
					      		value="\${board.writer}"
					      	/>
					    </div>
					    <div class="mb-3 mt-3">
					      <label for="regdate">작성일:</label>
					      <input type="date" class="form-control" id="regdate"  readonly
					     		 value="\${regD}"
				      		/>
					    </div>
					    <div class="mb-3 mt-3">
					      <label for="updatedate">수정일:</label>
					      <input type="date" class="form-control" id="updatedate" readonly
					  		    value="\${updateD}"
					      	/>
					    </div>
					    <div class="mb-3 mt-3">
					      <label for="viewcnt">조회수:</label>
					      <input type="number" class="form-control" id="viewcnt" readonly="readonly"
					    	  value="\${board.viewcnt}"
					      />
					    </div>
					    <div>
						    <input type="button" class="btn btn-primary" onclick="boardUpdate(event);" \${disabled} value="수정" />
						    <input type="button" class="btn btn-primary"
						    	onclick="boardDelete(\${bno});" \${disabled} value="삭제" />
					    </div>
					</form>
				</div>
			`;
			$("#here").html(output);
		}
	});
}

function boradList(){
	$.ajax({
		url:"${appPath}/board/boardList.do",
		type:"get",
		success: function(responseData) {
			var output = `
				<table class="table table-info">
					<tr>
						<td>번호</td>
						<td>제목</td>
						<td>내용</td>
						<td>작성자</td>
						<td>작성일</td>
						<td>조회수</td>
						<td>이미지</td>
					</tr>
			`;
			$.each(responseData, function(index, item) {
				//EL $ 충돌되어 구분을 위해 \$ 를 사용함
				output += `
					<tr>
						<td>
							<a href="javascript:boardDetail(\${item.bno});">\${item.bno}</a>
						</td>
						<td>\${item.title}</td>
						<td>\${item.content}</td>
						<td>\${item.writer}</td>
						<td>\${new Date(item.regdate).toISOString().split("T")[0]}</td>
						<td>\${item.viewcnt}</td>
						<td>\${item.pic!=null?"이미지" : ""}</td>
					</tr>
				`;
			});
			output += `</table>`;
			$("#here").html(output);
		},
		fail: function(err) {
			alert(err);
		}
	});
}

function boradInsert(){
	$.ajax({
		url:"${appPath}/board/insert.do",
		success: function(resData) {
			$("#here").html(resData);
		}
	});	
}
function boardInsertPost() {
	console.log($("#myfrm"));
	
	var formData = new FormData($("#myfrm")[0]);
	for(let key of formData.keys()) {
		console.log(key, ": ", formData.get(key));
	}

	//파일 업로드
	// 필수 주석 - 문자로 가지 말라고 적는 필수 옵션들임.
	$.ajax({
		 url:"${appPath}/board/insertPost.do",
		 type:"post",
		 enctype: 'multipart/form-data',// 필수
		 processData: false,//필수)
		 cache: false,
		 contentType: false, //필수)
		 //contentType:"application/json;charset=utf-8",
		 data: formData ,
		 success:function(responseData){ alert(responseData); }
	});
	//processData : data 파라미터로 전달된 데이터를 jQuery 내부적으로 query string 으로 만드는데, 파일 전송의 경우 이를 하지 않음으로 변경
	//contentType : default 값이 "application/x-www-form-urlencoded; charset=UTF-8" 인데, "multipart/form-data" 로 전송이 되게 false

	/* 파일 업로드 없는 기본
	var obj={
		"title" : $("#title").val(),
		"content" : $("#content").val()
	};	
	
	$.ajax({
		url:"${appPath}/board/insertPost.do",
		type:"post",
		data: JSON.stringify(obj),
		contentType:"application/json; charset=utf-8",
		success: function(responseData) {
			alert(responseData);
			boradList();
		}
	});
	*/
}
</script>
</head>
<body>

	<div class="container mt-5">
		<div class="row">
			<div class="col-sm-4">
				<h3 class="mt-4">Ajax로 보내기</h3>
				<p>RestController처리</p>
				<ul class="nav nav-pills flex-column">
					<li class="nav-item"><a class="nav-link"
						href="javascript:boradList();">게시목록</a></li>
					<li class="nav-item"><a class="nav-link"
						href="javascript:boradInsert();">게시글입력</a></li>
				</ul>
				<hr class="d-sm-none">
			</div>
			<div class="col-sm-8">
				<h2>결과</h2>
				<h5></h5>
				<div id="here">여기</div>
			</div>
		</div>
		<div class="mt-3 p-1 bg-dark text-white text-center">
			<p>신한금융소프트아카데미</p>
		</div>
	</div>
</body>
</html>