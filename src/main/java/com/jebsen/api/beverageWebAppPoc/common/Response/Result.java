package com.jebsen.api.beverageWebAppPoc.common.Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@ToString(callSuper=true)
@Builder(toBuilder = true)
public class Result implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7843687748424297839L;
	@JsonProperty("result_code")
	private String result_code;
	
	@JsonProperty("result_message")
	private String result_message = "";
	
	@JsonProperty("result_error")
	private List<Object> result_error = new ArrayList<Object>();
	
	@JsonProperty("result_content")
	private Map<String,Object> result_content = new HashMap<String, Object>();

	public Result() {
	}

	public Result(String result_code) {
		super();
		this.result_code = result_code;
	}
	
	public Result(String result_code, String result_message) {
		super();
		this.result_code = result_code;
		this.result_message = result_message;
	}

}
