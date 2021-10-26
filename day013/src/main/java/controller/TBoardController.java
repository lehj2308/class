package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import model.tBoard.TBoardServiceImpl;
import model.tBoard.TBoardVO;

@Controller
@SessionAttributes("data")
public class TBoardController {

	@Autowired
	private TBoardServiceImpl tBoardService;
	
	@ModelAttribute("sm")
	public Map<String,String> searchMap(){
		Map<String,String> sm=new HashMap<String, String>();
		sm.put("제목", "title");
		sm.put("작성자", "writer");
		return sm;
	}
	
	@RequestMapping("/deleteTBoard")
	public String deleteTBoard(TBoardVO vo) {
		tBoardService.deleteTBoard(vo);
		return "redirect:main.do";
	}

	@RequestMapping("/getTBoard.do")
	public String getTBoard(TBoardVO vo, Model model) {
		vo = tBoardService.getTBoard(vo);
		model.addAttribute("tBoard", vo);
		return "getTBoard.jsp";
	}

	@RequestMapping("/insertTBoard.do")
	public String insertTBoard(TBoardVO vo) {
		tBoardService.insertTBoard(vo);
		return "redirect:main.do";
	}

	@RequestMapping("/updateTBoard.do")
	public String updateTBoard(TBoardVO vo) {
		tBoardService.updateTBoard(vo);
		return "redirect:main.do";
	}

	@RequestMapping("/main.do")
	public String getTBoardList(@RequestParam(value = "page", defaultValue = "1", required = false) int page,
			@RequestParam(value = "condition", defaultValue = "title", required = false) String condition,
			@RequestParam(value = "keyword", defaultValue = "", required = false) String keyword,
			TBoardVO vo, Model model) {

		List<TBoardVO> tBoardList = tBoardService.getTBoardList(vo, page);
		
		model.addAttribute("tBoardList", tBoardList);
		model.addAttribute("page", page);
		
		return "main.jsp";
	}
}
