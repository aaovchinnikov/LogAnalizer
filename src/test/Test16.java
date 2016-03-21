package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import util.StringComparison;

/**
 * Продолжение идей Test6 и Test14. Тут пробуем построить дерево всех вариантов
 * и его обсчитать до первого подходящего шаблона.
 * 
 * @author pih
 *
 */
public class Test16 {

	public static final List<String> TEST_CASE_1 = Arrays.asList(new String[]{
			" {sophos_internal_id}: host gmail-smtp-in.l.google.com[64.233.164.27] said: 450-4.2.1 The user you are trying to contact is receiving mail at a rate that 450-4.2.1 prevents additional messages from being delivered. Please resend your 450-4.2.1 message at a later time. If the user is able to receive mail at that 450-4.2.1 time, your message will be delivered. For more information, please 450-4.2.1 visit 450 4.2.1  https://support.google.com/mail/answer/6592 1si9232024lfi.103 - gsmtp (in reply to RCPT TO command)",
			" {sophos_internal_id}: host gmail-smtp-in.l.google.com[64.233.164.27] said: 450-4.2.1 The user you are trying to contact is receiving mail at a rate that 450-4.2.1 prevents additional messages from being delivered. Please resend your 450-4.2.1 message at a later time. If the user is able to receive mail at that 450-4.2.1 time, your message will be delivered. For more information, please 450-4.2.1 visit 450 4.2.1  https://support.google.com/mail/answer/6592 jn4si9227864lbc.203 - gsmtp (in reply to RCPT TO command)",
//	       	  {sophos_internal_id}: {&}o{&}s{&}t{&}gmail-smtp-in.l.google.com[{&}3.{&}1.{&}2{&}7{&}] said: 450-4.2.1 The user you are trying to contact is receiving mail at a rate that 450-4.2.1 prevents additional messages from being delivered. Please resend your 450-4.2.1 message at a later time. If the user is able to receive mail at that 450-4.2.1 time, your message will be delivered. For more information, please 450-4.2.1 visit 450 4.2.1  https://support.google.com/mail/answer/6592 {&}si{&}.{&} - gsmtp (in reply to RCPT TO command){&}
//			  {sophos_internal_id}: {&}o{&}s{&}t{&}gmail-smtp-in.l.google.com[{&}3.{&}1.{&}2{&}7{&}] said: 450-4.2.1 The user you are trying to contact is receiving mail at a rate that 450-4.2.1 prevents additional messages from being delivered. Please resend your 450-4.2.1 message at a later time. If the user is able to receive mail at that 450-4.2.1 time, your message will be delivered. For more information, please 450-4.2.1 visit 450 4.2.1  https://support.google.com/mail/answer/6592 {&}si{&}.{&} - gsmtp (in reply to RCPT TO command){&}				
			" {sophos_internal_id}: to=<su.p.e.r.v.iso.r.20.1.5.invino@gmail.com>, relay=alt1.gmail-smtp-in.l.google.com[74.125.23.26]:25, delay=264, delays=262/0/1.6/0.94, dsn=4.2.1, status=deferred (host alt1.gmail-smtp-in.l.google.com[74.125.23.26] said: 450-4.2.1 The user you are trying to contact is receiving mail at a rate that 450-4.2.1 prevents additional messages from being delivered. Please resend your 450-4.2.1 message at a later time. If the user is able to receive mail at that 450-4.2.1 time, your message will be delivered. For more information, please 450-4.2.1 visit 450 4.2.1  https://support.google.com/mail/answer/6592 r9si11244175ioi.12 - gsmtp (in reply to RCPT TO command))",
			" {sophos_internal_id}: host gmail-smtp-in.l.google.com[173.194.71.27] said: 450-4.2.1 The user you are trying to contact is receiving mail at a rate that 450-4.2.1 prevents additional messages from being delivered. Please resend your 450-4.2.1 message at a later time. If the user is able to receive mail at that 450-4.2.1 time, your message will be delivered. For more information, please 450-4.2.1 visit 450 4.2.1  https://support.google.com/mail/answer/6592 192si9255066lfh.71 - gsmtp (in reply to RCPT TO command)",
			" {sophos_internal_id}: to=<su.p.e.r.v.iso.r.20.1.5.invino@gmail.com>, relay=alt1.gmail-smtp-in.l.google.com[74.125.23.26]:25, delay=624, delays=622/0.01/1.4/0.94, dsn=4.2.1, status=deferred (host alt1.gmail-smtp-in.l.google.com[74.125.23.26] said: 450-4.2.1 The user you are trying to contact is receiving mail at a rate that 450-4.2.1 prevents additional messages from being delivered. Please resend your 450-4.2.1 message at a later time. If the user is able to receive mail at that 450-4.2.1 time, your message will be delivered. For more information, please 450-4.2.1 visit 450 4.2.1  https://support.google.com/mail/answer/6592 72si20143688iob.61 - gsmtp (in reply to RCPT TO command))",
			" {sophos_internal_id}: host gmail-smtp-in.l.google.com[64.233.163.27] said: 450-4.2.1 The user you are trying to contact is receiving mail at a rate that 450-4.2.1 prevents additional messages from being delivered. Please resend your 450-4.2.1 message at a later time. If the user is able to receive mail at that 450-4.2.1 time, your message will be delivered. For more information, please 450-4.2.1 visit 450 4.2.1  https://support.google.com/mail/answer/6592 dx2si8644490lbc.94 - gsmtp (in reply to RCPT TO command)",
			" {sophos_internal_id}: to=<su.p.e.r.v.iso.r.20.1.5.invino@gmail.com>, relay=alt1.gmail-smtp-in.l.google.com[74.125.203.27]:25, delay=1345, delays=1342/0/1.3/0.94, dsn=4.2.1, status=deferred (host alt1.gmail-smtp-in.l.google.com[74.125.203.27] said: 450-4.2.1 The user you are trying to contact is receiving mail at a rate that 450-4.2.1 prevents additional messages from being delivered. Please resend your 450-4.2.1 message at a later time. If the user is able to receive mail at that 450-4.2.1 time, your message will be delivered. For more information, please 450-4.2.1 visit 450 4.2.1  https://support.google.com/mail/answer/6592 e97si20700057ioi.206 - gsmtp (in reply to RCPT TO command))"
	});
	// template =" {sophos_internal_id}: {&}o{&}s{&}t{&}gmail-smtp-in.l.google.com[{&}3.{&}1.{&}2{&}7{&}] said: 450-4.2.1 The user you are trying to contact is receiving mail at a rate that 450-4.2.1 prevents additional messages from being delivered. Please resend your 450-4.2.1 message at a later time. If the user is able to receive mail at that 450-4.2.1 time, your message will be delivered. For more information, please 450-4.2.1 visit 450 4.2.1  https://support.google.com/mail/answer/6592 {&}si{&}.{&} - gsmtp (in reply to RCPT TO command){&}"
	//             {sophos_internal_id}: {&}o{&}s{&}t{&}gmail-smtp-in.l.google.com[{&}3.{&}1.{&}2{&}7{&}] said: 450-4.2.1 The user you are trying to contact is receiving mail at a rate that 450-4.2.1 prevents additional messages from being delivered. Please resend your 450-4.2.1 message at a later time. If the user is able to receive mail at that 450-4.2.1 time, your message will be delivered. For more information, please 450-4.2.1 visit 450 4.2.1  https://support.google.com/mail/answer/6592 {&}si{&}.{&} - gsmtp (in reply to RCPT TO command){&}


	public static final List<String> TEST_CASE_2 = Arrays.asList(new String[]{
		" {sophos_internal_id}: from=<{email_address}>, size={size}, nrcpt=1 (queue active)",  
		" {sophos_internal_id}: from=<prvs={prvs}={email_address}>, size={size}, nrcpt=1 (queue active)"
	});
	// template = " {sophos_internal_id}: from=<{&}{email_address}>, size={size}, nrcpt=1 (queue active)"

		
	public static final List<String> TEST_CASE_3 = Arrays.asList(new String[]{
		" connect from localhost.localdomain[127.0.0.1]",  
		" disconnect from localhost.localdomain[127.0.0.1]"
	});
	// template = " {&}connect from localhost.localdomain[127.0.0.1]"

	
	public static void main(String[] args) {
		List<String> similarStrings = TEST_CASE_1;
		CaseTreeNode root=new CaseTreeNode(similarStrings);

/*		
		List<List<Integer>> outerList = new ArrayList<List<Integer>>();
		List<Integer> interList = Arrays.asList(new Integer[]{1,2,3});
		outerList.add(interList);
		interList = Arrays.asList(new Integer[]{4,7,8});
		outerList.add(interList);
		interList = Arrays.asList(new Integer[]{5});
		outerList.add(interList);
		
		CaseTreeNode.getPositionsAsList(outerList);
		
		PositionTreeNode ptn = root.computePositionsTree(outerList);
		ptn.printTree();
		ptn.printNode();
		ptn.children.get(1).printNode();
		ptn.children.get(1).children.get(0).printNode();
		ptn.children.get(1).children.get(0).children.get(0).printNode();
		ptn.children.get(1).children.get(0).children.get(1).printNode();
*/
	}
	
	private static List<String> buildLeftSubstrings(List<String> strings, String lcSubstring, List<Integer> indexes){
		System.out.println("-----------buildLeftSubstrings-----------------------------------------");
		for(String s: strings){
			System.out.println(s);
		}
		System.out.println("strings.size(): "+ strings.size());
		System.out.println("indexes.size(): "+ indexes.size());

		if(strings.size()!=indexes.size()){
			throw new IllegalArgumentException("unequal size of lists!");
		}
		
		List<String> leftSubstrings = new ArrayList<String>(strings.size());
		for(int i=0; i< strings.size(); i++){
			leftSubstrings.add(strings.get(i).substring(0, indexes.get(i)));
		}
		
		System.out.println();
		System.out.println("left substrings:");
		for(String s: leftSubstrings){
			System.out.println(s);
		}

		System.out.println("--end-of---buildLeftSubstrings-----------------------------------------");
		System.out.println();
		return leftSubstrings;
	}
	
	private static List<String> buildRightSubstrings(List<String> strings, String lcSubstring, List<Integer> indexes){
		if(strings.size()!=indexes.size()){
			throw new IllegalArgumentException("unequal size of lists!");
		}

		List<String> rightSubstrings = new ArrayList<String>(strings.size());
		for(int i=0; i< strings.size(); i++){
			rightSubstrings.add(strings.get(i).substring(indexes.get(i) + lcSubstring.length(), strings.get(i).length()));
		}

		return rightSubstrings;
	}

	
	/**
	 * Класс описывает один узел в дереве возможных вариантов при построении общего шаблона
	 * @author pih
	 */
	public static final class CaseTreeNode{
		private final List<String> similarStrings;
		private final String lcSequence; //TODO скорее всего это никому не надо
		private final Set<String> lcStrings;
		private final Map<String, List<List<Integer>>> positions;
		private List<ChildCase> children;

		
		public CaseTreeNode(List<String> similarStrings) {
			this.similarStrings = similarStrings;
			this.lcSequence = StringComparison.computeLCSubsequenceForStringGroup(similarStrings);
			this.lcStrings = StringComparison.computeAllLCSubstringsForStringGroup(similarStrings);
			this.positions = computePositions();
			this.children = computeChildren();
		}
		
		private Map<String, List<List<Integer>>> computePositions() {
			System.out.println("------------computePositions--------------");
			for(String s:similarStrings){
				System.out.println("\""+s+"\"");
			}
			final Map<String, List<List<Integer>>> positions= new HashMap<String, List<List<Integer>>>(lcStrings.size());
			
			for(String lcString: this.lcStrings){
				positions.put(lcString, computePositionsOfString(lcString));
				System.out.println("lcString: "+lcString+" positions: "+ positions.get(lcString));
			}
			
			System.out.println("--end-of----computePositions--------------");
			return positions;
		}
		
		/**
		 * Вычисляет позиции переданной подстроки в списке строк {@link #similarStrings}
		 * @param lcString
		 * @return
		 */
		private List<List<Integer>> computePositionsOfString(String string){
			final List<List<Integer>> positionsList = new ArrayList<List<Integer>>(similarStrings.size());
			List<Integer> positions;
			int position;
			
			for(String s: similarStrings){
				positions = new ArrayList<Integer>();
				position = s.indexOf(string);
				while(position != -1){
					positions.add(Integer.valueOf(position));
					position+=string.length();
					position = s.indexOf(string, position);
				}
				positionsList.add(positions);
			}
			
			return positionsList;
		}
		
		/**
		 * Метод строящий перечень вариантов позиций не на основе дерева, а сразу по списку.
		 * Должен заменить выходки с PositionTreeNode прошлых версий
		 * @return
		 */
		public static List<List<Integer>> getPositionsAsList(List<List<Integer>> positions){
			int arraySize=1;
			for(List<Integer> list: positions){
				arraySize *= list.size();
			}
			if(arraySize == 0){
				throw new IllegalStateException("array size is 0");
			}
			int array[][] = new int[arraySize][positions.size()];
			int repeatCount=arraySize;
			int size;
			// Заполняем массив элементами из positions
			for(int j=0; j<positions.size(); j++){ // заполняем по столбикам
				size = positions.get(j).size();
				repeatCount/= size;
				for(int i=0; i< size; i++){
					for(int k=0; k< arraySize; k++){
						array[k][j] = positions.get(j).get((k/repeatCount) % size);
					}
				}
			}

			// построение конечного списка списков
			List<List<Integer>> returnList = new ArrayList<List<Integer>>();
			List<Integer> list;
			for(int i=0; i<arraySize; i++){
				list = new ArrayList<Integer>(positions.size());
				for(int j=0; j<positions.size(); j++){
					list.add(array[i][j]);
				}
				returnList.add(list);
			}
			
			return returnList;
		}
		
		private List<ChildCase> computeChildren() {
			List<ChildCase> children = new ArrayList<ChildCase>(2*lcStrings.size());
			ChildCase childCase;
			List<List<Integer>> positionsList;
			
			for(Entry<String, List<List<Integer>>> entry: positions.entrySet()){
				positionsList = getPositionsAsList(entry.getValue());
				for(List<Integer> pos: positionsList){
					childCase = new ChildCase();
					childCase.lcString = entry.getKey();
					childCase.positions = pos;
					childCase.left = new CaseTreeNode(buildLeftSubstrings(similarStrings, childCase.lcString, childCase.positions));
					childCase.right = new CaseTreeNode(buildRightSubstrings(similarStrings, childCase.lcString, childCase.positions));
					children.add(childCase);
				}
			}
			
			return children;
		}

		
		public List<String> getSimilarStrings() {
			return similarStrings;
		}
		
		public String getLcSequence() {
			return lcSequence;
		}
		
		public Set<String> getLcStrings() {
			return lcStrings;
		}
	}
	
	private static final class ChildCase{
		String lcString;
		List<Integer> positions;
		CaseTreeNode left, right;
	} 
}