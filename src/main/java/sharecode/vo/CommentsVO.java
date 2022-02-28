package sharecode.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentsVO {
	private int com_no; //댓글 번호
	private int post_no; //게시글 번호
	private int user_no; //회원 번호 
	private String com_content; //댓글 내용 
	private int com_pnum; //상위 댓글 번호 
	private int com_job; //댓글(0) 답글(1) 여부 
	private String com_date; //댓글 작성일 
	private String user_id;
	private int com_del; //삭제 여부 (1일 경우에 삭제된 댓글) 
}
