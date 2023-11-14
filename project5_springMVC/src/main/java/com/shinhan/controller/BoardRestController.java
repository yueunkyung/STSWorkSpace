package com.shinhan.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.shinhan.dto.BoardVO;
import com.shinhan.dto.CustomerVO;
import com.shinhan.model.BoardService;

@RestController // @Controller + @ResponseBody
public class BoardRestController {

	@Autowired
	BoardService bService;

	Logger logger = LoggerFactory.getLogger(BoardRestController.class);

	// @RequestMapping(method=RequestMethod.DELETE)
	@DeleteMapping(value = "/board/boardDelete.do/{boardNO}", produces = "text/plain;charset=utf-8")
	public String delete(@PathVariable("boardNO") int bno) {
		int result = bService.delete(bno);

		return result > 0 ? "삭제 성공" : "삭제 실패";
	}

	// consumes는 클라이언트가 서버에게 보내는 데이터 타입을 명시한다.
	// produces는 서버가 클라이언트에게 반환하는 데이터 타입을 명시한다.
	@PutMapping(value = "/board/boardUpdate.do", consumes = "application/json" // 들어오는 것
			, produces = "text/plain;charset=utf-8" // 나가는(소비하는) 것
	)
	public String boardUpdate(@RequestBody BoardVO board) {
		int result = bService.update(board);
		return result > 0 ? "update 성공" : "update 실패";
	}

	@GetMapping(value = "/board/boardDetail.do/{boardNo}", produces = "application/json")
	public BoardVO boardDetail(@PathVariable("boardNo") int bno) {
		return bService.updateTransViewCnt(bno);
	}

	@GetMapping(value = "/board/boardList.do", produces = "application/json")
	public List<BoardVO> selectAll() {
		logger.info("@@@@@ slectAll에 들어옴 @@@@@@@@@@@@@@@@@@@@@@@@@");
		return bService.findAll();
	}

	/*
	 * 파일 업로드 없는 기본
	 * 
	 * @PostMapping(value="/board/insertPost.do" , consumes = "application/json" ,
	 * produces = "text/plain;charset=utf-8" ) public String insertPost(@RequestBody
	 * BoardVO board, HttpSession session) { Object cust =
	 * session.getAttribute("loginCust"); if(cust == null) { return
	 * "[fail] 게시글 등록은 반드시 로그인이 필요합니다."; }
	 * 
	 * CustomerVO loginCust = (CustomerVO)cust;
	 * board.setWriter(loginCust.getCust_id());
	 * System.out.println("@@@@@@@@@@@@@board @@@@@@@@@"+board); int result =
	 * bService.insert(board); return result>0? "success": "fail"; }
	 */

	// 파일 업로드
	@PostMapping(value = "/board/insertPost.do", produces = "text/plain;charset=utf-8")
	public String insertPost(MultipartHttpServletRequest multipart, HttpSession session) {
		Object cust = session.getAttribute("loginCust");
		if (cust == null) {
			return "[fail] 게시글 등록은 반드시 로그인이 필요합니다.";
		}

		// Multipart 처리 추가함
		BoardVO board = new BoardVO();
		HttpServletRequest request = (HttpServletRequest) multipart;
		board.setTitle(request.getParameter("title"));
		board.setContent(request.getParameter("content"));

		logger.info(board.toString());

		// 이미지 읽어서 upload, 경로는 칼럼에 추가하기
		List<MultipartFile> fileList = multipart.getFiles("pic");
		ServletContext application = session.getServletContext();
		String path = application.getRealPath("./resources/uploads");
		File fileDir = new File(path);
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}
		long time = System.currentTimeMillis();
		for (MultipartFile mf : fileList) {
			String originFileName = mf.getOriginalFilename(); // 원본 파일 명
			String saveFileName = String.format("%d_%s", time, originFileName);
			board.setPic(saveFileName);
			try {
				// 파일생성
				mf.transferTo(new File(path, saveFileName));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		CustomerVO loginCust = (CustomerVO) cust;
		board.setWriter(loginCust.getCust_id());
		int result = bService.insert(board);
		return result > 0 ? "success" : "fail";
	}

	@RequestMapping(value = "/board/aa", produces = "text/plain;charset=utf-8")
	public String test1() {
		return "오케이";
	}

	// produces : 내보내는 데이터 타입을 설정 application/json ==
	// MediaType.APPLICATION_JSON_VALUE
	// jackson이 Java 객체를 JSON으로 생성해 준다.
	@RequestMapping(value = "/board/bb", produces = MediaType.APPLICATION_JSON_VALUE)
	public BoardVO test2() {
		BoardVO board = new BoardVO();
		board.setTitle("월요일");
		board.setContent("추워요~~~");

		return board;
	}

	@RequestMapping(value = "/board/dd", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, String> test4() {
		Map<String, String> board = new HashMap<String, String>();
		board.put("title", "월요일");
		board.put("content", "추워요~~~");

		return board;
	}

	@RequestMapping(value = "/board/cc", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<BoardVO> test3() {
		List<BoardVO> blist = new ArrayList<>();

		IntStream.range(1, 6).forEach(i -> {
			BoardVO board = new BoardVO();
			board.setBno(i);
			board.setTitle("월요일" + i);
			board.setContent("추워요~~~" + i);
			blist.add(board);
		});

		return blist;
	}
}
