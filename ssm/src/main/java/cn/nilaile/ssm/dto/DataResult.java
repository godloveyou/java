package cn.nilaile.ssm.dto;

import java.io.Serializable;
import java.util.List;

public class DataResult<T> implements Serializable {

	private static final long serialVersionUID = 6371894802849605669L;
	private boolean success;
	private String msg;
	private List<T> data;
	
	public DataResult(){
		
	}
	public DataResult(boolean success,List<T> data){
		super();
		this.data = data;
		this.success = success;
	}
	
	public DataResult(boolean success,String msg){
		super();
		this.msg = msg;
		this.success = success;
	}
	
	public DataResult(boolean success,List<T> data,String msg){
		super();
		this.data = data;
		this.success = success;
		this.msg = msg;
	}
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	


}
