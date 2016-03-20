package util;

import java.util.List;
import java.util.regex.Pattern;

public class Templates {
	public static String getUnitedTemplate(List<String> similarStrings){
		String unitedTemplate;
		String candidate;
		
		// Нахождение LCS для списка похожих строк
		String lcs = similarStrings.get(0);
		for (String s : similarStrings) {
			lcs = StringComparison.computeLCSubsequence(lcs, s);
		}
		System.out.println("before insertion: "+lcs);

		unitedTemplate = insertUnnamedPlaceholders(lcs);
		System.out.println("after insertion: "+unitedTemplate);
		
		// TODO имеет смысл делать сразу нормальный regexp а не через промежуточный с {&}
		for(int i=0; i<=lcs.length(); i++){
			candidate = unitedTemplate.replaceFirst("\\{"+ i +"\\}", "");
			String regexp = ParseMessage.escapeSpecialRegexChars(candidate);
			regexp = regexp.replaceAll("\\\\\\{\\d*\\\\\\}", "(.*)"); // FUCK! Hate regexp!
			System.out.println("regexp: "+ regexp);
			Pattern pattern = Pattern.compile(regexp);
			System.out.println("regexp compiled");
			if(matchesAll(pattern, similarStrings)) {
				System.out.println("matches all");
				unitedTemplate = candidate;
			}
			System.out.println("after replacement: "+unitedTemplate);
		}
		
		unitedTemplate = rebuildPlaceholderNumeration(unitedTemplate);
		
		return unitedTemplate;
	}

	private static String insertUnnamedPlaceholders(String lcs){
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i< lcs.length(); i++){
			sb.append('{');
			sb.append(i);
			sb.append('}');
			sb.append(lcs.charAt(i));
		}
		sb.append('{');
		sb.append(lcs.length());
		sb.append('}');
		
		return sb.toString();
	}
	
	private static boolean matchesAll(Pattern pattern, List<String> similarStrings){
		for(String s: similarStrings){
			System.out.println("test against: "+s);
			if(!pattern.matcher(s).matches()){
				return false;
			}
		}
		
		return true;
	}
	
	private static String rebuildPlaceholderNumeration(String template){
		String unitedTemplate =template.replaceAll("\\{\\d*\\}", "{&}");
		String tmp;
		int placeholdersCount=0;
		
		// now replace placehoders with numbers in united template
		do {
			tmp = unitedTemplate;
			unitedTemplate = tmp.replaceFirst("\\{&\\}", "{" + placeholdersCount + "}");
			placeholdersCount++;
		} while (!tmp.equals(unitedTemplate));

		return unitedTemplate;
	}

}
