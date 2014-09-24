package com.zhg.utils;

public class StrTime {

	public static String gettim(int durctions){
		int mintue=durctions/1000/60;
		int second=(durctions-mintue*60000)/1000;
		if (second<10) {
			return "0"+mintue+":0"+second;
		}else {
			return "0"+mintue+":"+second;
		}
	}
	public static String getTime(String durction){
		try {
			int durctions=Integer.parseInt(durction);
			int mintue=durctions/1000/60;
			int second=(durctions-mintue*60000)/1000;
			if (second<10) {
				return "0"+mintue+":0"+second;
			}else {
			return "0"+mintue+":"+second;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return "00:00";
		}
	}
	/**
	 * �жϸ����ַ����Ƿ�հ״���<br>
	 * �հ״���ָ�ɿո��Ʊ�����س��������з���ɵ��ַ���<br>
	 * �������ַ���Ϊnull����ַ���������true
	 * @param input
	 * @return boolean
	 */
	public static boolean isBlank( String input ) 
	{
		if ( input == null || "".equals( input ) )
			return true;
		
		for ( int i = 0; i < input.length(); i++ ) 
		{
			char c = input.charAt( i );
			if ( c != ' ' && c != '\t' && c != '\r' && c != '\n' )
			{
				return false;
			}
		}
		return true;
	}
}
