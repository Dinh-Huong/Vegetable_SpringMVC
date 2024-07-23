package com.vegetable.helpers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class PaginationHelper {
	public String getParamFromUrl(HttpServletRequest request) {
		StringBuilder urlBuilder = new StringBuilder(request.getRequestURI());
		Map<String, String[]> parameterMap = request.getParameterMap();
		
		urlBuilder.append("?");
		if(!parameterMap.isEmpty()) {
			for (Map.Entry<String, String[]> entry: parameterMap.entrySet()) {
				String key = entry.getKey();
                String[] values = entry.getValue();
                if(!key.equals("page")) {
                	for (String value : values) {
                		urlBuilder.append(key).append("=").append(value).append("&");
                	}                	
                }
			}
		}
        return urlBuilder.toString();
	}
}
