<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="cpath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>직원 목록</title>
<style>
#container {
	width: 90%;
	margin: 50px auto;
}

h1 {
	text-align: center;
}

table {
	border-collapse: collapse;
	margin: 0 auto;
}

table th, table td {
	border: 1px solid #bbb;
	font-size: 14px;
	box-sizing: border-box;
}

table th {
	padding: 10px;
	background-color: #185422;
	color: white;
	letter-spacing: 0.05em;
}

table td {
	padding: 7px 10px;
	text-align: center;
}

table tbody tr:hover td {
	background-color: #ffffd7;
}

table tbody tr td:hover {
	background-color: #ffff9b;
}

/* 직원 이름이 'D'로 시작하면 글씨색을 red */
/* 한칸 이상의 공백은 자손을 의미한다 */
table tbody tr td[fname^="D"] {
	color: red;
}

/* tr의 seq 속성에 값이 5로 끝나면 스타일 바꾸기 */
table tbody tr[seq$="5"] {
	background-color: #eaeaea;
}

/* data 중에 짝수번째 row에 border 스타일 변경 */
table tbody tr:nth-of-type(even) {
	border: 2px dotted green;
}

/* data 중에 짝수번째 row에 border 스타일 변경 */
table tbody tr:nth-of-type(2n+1) {
	border: 2px dotted hotpink;
}

table tbody tr:First-child, table tbody tr:last-child {
	background-color: aqua;
}

p:only-child {
	font-size: 30px;
}

p:only-of-type {
	font-size: 50px;
	color: maroon;
}

#container h1::before, #container h1::after {
	content: "**********";
}

tbody {
	counter-reset: mycount;
}

.aa::before {
	counter-increment: mycount;
	content: "[[@@" counter(mycount) "@@]]";
}

.hotpink {
	background-color: hotpink;
}

.yellow {
	background-color: yellow;
}

.white {
	background-color: white;
}
.search_box {
	padding: 30px 10px;
	box-sizing: border-box;
}
</style>
<script>
	window.onload = call;	

	function call() {
		// onclick => event 속성
		// f1 => 이벤트핸들러, 이벤트리스너
		document.querySelector("#search1").onclick = f1;
		document.querySelector("#search2").onclick = f2;
		document.querySelector("#search3").onclick = f3;
		document.querySelector("#btnClearAll").onclick = f4;
		document.querySelector("#reload").onclick = function() {
			location.reload();
		};

		function f1() {
			var fname = document.querySelector("#fname").value.toLowerCase();
			alert(fname);
			var nodeList = document
					.querySelectorAll("tbody tr td:nth-child(3)");
			var bgColor = "hotpink";

			nodeList.forEach(function(ele) {
				if (ele.textContent.trim().toLowerCase().startsWith(fname))
					ele.className = bgColor;
				else {
					// ele.className = white;
					ele.classList.remove(bgColor);
					//**자리를 차지하지 않음 : display
					//ele.parentNode.style.display = "none";
					//**자리를 차지함 : visibility
					//ele.parentNode.style.visibility = "hidden";
				}
			});
		}

		function f2(event) {
			var event = event || window.event;

			for ( let prop in event) {
				//console.log("prop : ", prop, "========event[prop] : ", event[prop]);
			}

			var salary = Number(document.querySelector("#salary").value);
			//alert(salary);
			var nodeList = document
					.querySelectorAll("tbody tr td:nth-child(7)");
			var bgColor = "yellow";

			nodeList.forEach(function(ele) {
				var su = Number(ele.textContent.replace(/[^0-9]/g,''));
				if (su >= salary)
					ele.className = bgColor;
				else {
					// ele.className = white;
					ele.classList.remove(bgColor);
				}
			});
		}

		function f3() {
			//이벤트 호출
			document.querySelector("#search2").onclick();

			//함수 호출
			// f2();
		}

		function f4() {
			var nodeList = document.querySelectorAll("tbody tr td");

			nodeList.forEach(function(ele) {
				ele.classList.remove("hotpink", "yellow");
			});
		}
	}
</script>
</head>
<body>

	<!-- 1)인클루드 디렉티브 태그: 합쳐서 자바로 만든다.(변수 공유가 가능) -->
	<%@include file="../auth/logout.jsp" %>
	
	<!-- 2)jsp표준태그: 각각을 자바 파일로 만든다.(변수 공유 불가) -->
	<%--<jsp:include page="../auth/logout.jsp"></jsp:include>--%>
	
	<%-- container --%>
	<div id="container">
		<div>
			<input id="fname" />
			<button id="search1">시작하는 이름찾기</button>
			<br> <input id="salary" />
			<button id="search2">&gt;=급여찾기</button>
			<button id="search3">&gt;=급여찾기2</button>
			<br> <br>
			<button id="btnClearAll">class 모두제거</button>
			<button id="reload">reset(새로고침)</button>
			<br>
			
		</div>
		<%--
		<div>
			<h1>p가 1개인 경우</h1>
			<p>Only Child</p>
		</div>
		--%>
		<br>
		<button onclick="location.href='${cpath}/emp/empInsert.do';">신규직원등록</button>
		<div class="search_box"> 
			부서선택 : <select id="deptSelect">
						<option value="0">모든 부서</option>
						<c:forEach items="${dlist}" var="dept">
							<option  ${select_deptid == dept.department_id?selected:""} value="${dept.department_id}">
								${dept.department_name}
							</option>
						</c:forEach>
					</select>
					
			직책선택 : <select id="jobSelect">
						<option value="%">모든 직책</option>
						<c:forEach items="${jlist}" var="job">
							<option value="${job.job_id}" ${select_jobid == job.job_id?selected:""}>
								${job.job_title}
							</option>
						</c:forEach>
					</select>
			급여(이상) : <input type="number" id="sal" value="${select_salary}" />
			입사일 :  <input type="date" id="hiredate" value="${select_hiredate}" disabled />
			<input type="checkbox" id="datechk" onclick="call_chk()" checked /> 전체일자
			<button onclick="call_ajax();">조건 조회</button>
		</div>
		<%-- here --%>
		<div id="here"></div>
		<%-- //here --%>
	</div>
	<%-- //container --%>
	<script>
		var msg = "${message}";
		if(msg!="") alert(msg);
		
		$(function(){
			call_ajax();
		});

		function call_chk() {
			var chk =$("#datechk").prop("checked");
			if(chk) {
				$("#hiredate").attr("disabled", true);
				
			}else {
				$("#hiredate").attr("disabled", false);
			}
		}
		
		function call_ajax() {
			var paramObj = {};
			paramObj.deptid = $("#deptSelect").val();
			paramObj.jobid = $("#jobSelect").val();
			paramObj.salary = $("#sal").val();
		  	var chk = $("#datechk").prop("checked");
			if(chk) {
				paramObj.hiredate = $("#hiredate").val("1900-01-01");
			} 
			paramObj.hiredate = $("#hiredate").val();
			
			
			$.ajax({
				url: "${cpath}/emp/empListAjax.do",
				data: paramObj,
				success: function(respnseData) {
					//console.log("respnseData",respnseData);
					$("#here").html(respnseData);
				},
				error:function() {
					console.log("ajax 오류");
				}
			});
		}
	</script>
</body>
</html>