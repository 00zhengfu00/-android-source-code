package org.incoding.checkdemo;
/**
 * ���ߣ��������
 * ���ͣ�www.incoding.org
 * ʱ��:2012.05.08
 * ����Ⱥ��194802363ԭ��
 * ���ܣ���֤����ƽӿ�
 * */
public interface CheckAction 
{
	
	// ������֤����ʱ��Ϊ�ĸ��������Ժ���Ը�
	public void setCheckNum(int [] chenckNum);
	
	// ��ȡ��֤��
	public int [] getCheckNum();
	
	// ������֤����ʾ
	public void invaliChenkNum();
}
