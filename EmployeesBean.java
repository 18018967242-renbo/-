//这是一个javabean，对应employees表，代表数据
//他的一个实例（对象），对于employees表中的一条记录
package zy.model;

public class EmployeesBean {

	public String getE_ID() {
		return E_ID;
	}
	public void setE_ID(String e_ID) {
		E_ID = e_ID;
	}
	public String getE_Name() {
		return E_Name;
	}
	public void setE_Name(String e_Name) {
		E_Name = e_Name;
	}
	public String getE_LoginName() {
		return E_LoginName;
	}
	public void setE_LoginName(String e_LoginName) {
		E_LoginName = e_LoginName;
	}
	public String getE_LoginPwd() {
		return E_LoginPwd;
	}
	public void setE_LoginPwd(String e_LoginPwd) {
		E_LoginPwd = e_LoginPwd;
	}
	public String getE_Title() {
		return E_Title;
	}
	public void setE_Title(String e_Title) {
		E_Title = e_Title;
	}
	private String E_ID;
	private	String E_Name;
	private String E_LoginName;
	private String E_LoginPwd;
	private String E_Title;
	
	
}
