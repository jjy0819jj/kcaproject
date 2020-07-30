package member.bean;

import org.springframework.web.multipart.MultipartFile;

public class MemberDTO {
	private String name;
	private String email;
	private String pwd;
	private String tel;
	private String tel2;
	private String tel3;
	private int member_code;
	private String startwork;
	private String addr;
	private String file_name;
	private MultipartFile uploadFile;
	private int resumeHit;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getTel2() {
		return tel2;
	}

	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}

	public String getTel3() {
		return tel3;
	}

	public void setTel3(String tel3) {
		this.tel3 = tel3;
	}

	public int getMember_code() {
		return member_code;
	}

	public void setMember_code(int member_code) {
		this.member_code = member_code;
	}

	public String getStartwork() {
		return startwork;
	}

	public void setStartwork(String startwork) {
		this.startwork = startwork;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public MultipartFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}

	public int getResumeHit() {
		return resumeHit;
	}

	public void setResumeHit(int resumeHit) {
		this.resumeHit = resumeHit;
	}
	
	
}
