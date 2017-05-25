package com.tiexue.mcp.task.plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sun.xml.bind.v2.schemagen.xmlschema.List;
import com.tiexue.mcp.task.entity.McpTaskConstants;

public class PageFeature {

	private HashMap<Integer, ArrayList<String>> urlCharachers;

	/**
	 * 给url添加规则
	 * 
	 * @param pageType
	 * @param rules
	 */
	public void injectUrlRule(Integer pageType, String... rules) {
		if (urlCharachers == null) {
			urlCharachers = new HashMap<Integer, ArrayList<String>>();
		}
		ArrayList<String> ruleList = new ArrayList<String>();
		for (String rule : rules) {
			ruleList.add(rule);
		}
		urlCharachers.put(pageType, ruleList);
	}

	/**
	 * 匹配url
	 * 
	 * @param UrlString
	 * @return
	 */
	public int matchUrl(String UrlString) {
		int pagetype = McpTaskConstants.PageType_None;
		float value = 0;
		Iterator iter = urlCharachers.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<Integer, ArrayList<String>> entry = (Entry<Integer, ArrayList<String>>) iter.next();
			float temp = GetMatchingValueByCharacters(UrlString, entry.getValue());
			if (temp > value) {
				value = temp;
				pagetype = entry.getKey();
			}
		}
		return pagetype;
	}

	/**
	 * 得到输入字符的匹配度
	 * 
	 * @param input
	 * @param characters
	 * @return
	 */
	private static float GetMatchingValueByCharacters(String input, ArrayList<String> characters) {
		if (characters == null || characters.isEmpty())
			return 1;

		float matchCount = 0;

		for (String str : characters) {
			if (str.indexOf("REG:") == 0) {
				String reg = str.substring(4);
				Pattern pattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
				Matcher macth = pattern.matcher(input);
				if (macth.find()) {
					matchCount += 1;
				}
			} else if (str.indexOf("NOTREG:") == 0) {
				String reg = str.substring(7);
				Pattern pattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
				Matcher match = pattern.matcher(input);
				if (!match.find()) {
					matchCount += 1;
				}
			} else if (str.indexOf("NOT:") == 0) {
				String s = str.substring(4);
				if (input.indexOf(s) == -1) {
					matchCount += 1;
				}
			} else {
				if (input.indexOf(str) > -1) {
					matchCount += 1;
				}
			}
		}

		return matchCount / characters.size();
	}

}
