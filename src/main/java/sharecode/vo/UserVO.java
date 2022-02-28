package sharecode.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserVO {
	private int user_no;
	private String user_id;
	private String user_pw;
	private String user_mail;
	private String user_date;

}
