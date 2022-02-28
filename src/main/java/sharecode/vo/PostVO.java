package sharecode.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostVO {
	private int post_no;
	private int user_no;
	private String post_title;
	private String post_content;
	private String post_category;
	private int post_hit;
	private String post_date;
	private String user_id;
}
