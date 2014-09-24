package com.zhg.lrc;

import java.util.List;

import com.zhg.client.MyApplication;
import com.zhg.client.SuperActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

public class LyricView extends TextView {
	private Paint NotCurrentPaint; // �ǵ�ǰ��ʻ���
	private Paint CurrentPaint; // ��ǰ��ʻ���

	private int notCurrentPaintColor = Color.DKGRAY;// �ǵ�ǰ��ʻ��� ��ɫ
	private int CurrentPaintColor = Color.BLACK; // ��ǰ��ʻ��� ��ɫ

	private Typeface Texttypeface = Typeface.SERIF;
	private Typeface CurrentTexttypeface = Typeface.DEFAULT_BOLD;
	private float width;
	private static Lyric mLyric;
	private int brackgroundcolor = 0xf000000; // ������ɫ
	private float lrcTextSize = 22; // ��ʴ�С
	private float CurrentTextSize = 24;
	// private Align = Paint.Align.CENTER��
	String nowsen=null;
	public float mTouchHistoryY;

	private int height;
	private long currentDunringTime; // ��ǰ�и�ʳ�����ʱ�䣬�ø�ʱ����sleep
	// private float middleY;// y���м�
	private int TextHeight = 50; // ÿһ�еļ��
	private boolean lrcInitDone = false;// �Ƿ��ʼ�������
	public int index = 0;
	private int lastIndex = 0;
	private List<Sentence> Sentencelist; // ����б�

	private long currentTime;

	private long sentenctTime;

	public Paint getNotCurrentPaint() {
		return NotCurrentPaint;
	}

	public void setNotCurrentPaint(Paint notCurrentPaint) {
		NotCurrentPaint = notCurrentPaint;
	}

	public boolean isLrcInitDone() {
		return lrcInitDone;
	}

	public Typeface getCurrentTexttypeface() {
		return CurrentTexttypeface;
	}

	public void setCurrentTexttypeface(Typeface currrentTexttypeface) {
		CurrentTexttypeface = currrentTexttypeface;
	}

	public void setLrcInitDone(boolean lrcInitDone) {
		this.lrcInitDone = lrcInitDone;
	}

	public float getLrcTextSize() {
		return lrcTextSize;
	}

	public void setLrcTextSize(float lrcTextSize) {
		this.lrcTextSize = lrcTextSize;
	}

	public float getCurrentTextSize() {
		return CurrentTextSize;
	}

	public void setCurrentTextSize(float currentTextSize) {
		CurrentTextSize = currentTextSize;
	}

	public static Lyric getmLyric() {
		return mLyric;
	}

	public void setmLyric(Lyric mLyric) {
		LyricView.mLyric = mLyric;
	}

	public Paint getCurrentPaint() {
		return CurrentPaint;
	}

	public void setCurrentPaint(Paint currentPaint) {
		CurrentPaint = currentPaint;
	}

	public List<Sentence> getSentencelist() {
		return Sentencelist;
	}

	public void setSentencelist(List<Sentence> sentencelist) {
		Sentencelist = sentencelist;
	}

	public int getNotCurrentPaintColor() {
		return notCurrentPaintColor;
	}

	public void setNotCurrentPaintColor(int notCurrentPaintColor) {
		this.notCurrentPaintColor = notCurrentPaintColor;
	}

	public int getCurrentPaintColor() {
		return CurrentPaintColor;
	}

	public void setCurrentPaintColor(int currrentPaintColor) {
		CurrentPaintColor = currrentPaintColor;
	}

	public Typeface getTexttypeface() {
		return Texttypeface;
	}

	public void setTexttypeface(Typeface texttypeface) {
		Texttypeface = texttypeface;
	}

	public int getBrackgroundcolor() {
		return brackgroundcolor;
	}

	public void setBrackgroundcolor(int brackgroundcolor) {
		this.brackgroundcolor = brackgroundcolor;
	}

	public int getTextHeight() {
		return TextHeight;
	}

	public void setTextHeight(int textHeight) {
		TextHeight = textHeight;
	}

	public LyricView(Context context) {
		super(context);
		init();
	}

	public LyricView(Context context, AttributeSet attr) {
		super(context, attr);
		init();
	}

	public LyricView(Context context, AttributeSet attr, int i) {
		super(context, attr, i);
		init();
	}

	private void init() {
		setFocusable(true);
		// PlayListItem pli = new PlayListItem("Because Of You",
		// "/sdcard/MP3/Because Of You.mp3", 0L, true);
		// mLyric = new Lyric(new File("/sdcard/MP3/Because Of You.lrc"), pli);

		// �Ǹ�������
		NotCurrentPaint = new Paint();
		NotCurrentPaint.setAntiAlias(true);

		NotCurrentPaint.setTextAlign(Paint.Align.CENTER);

		// �������� ��ǰ���
		CurrentPaint = new Paint();
		CurrentPaint.setAntiAlias(true);
		// CurrentPaint.setColor(CurrentPaintColor);

		CurrentPaint.setTextAlign(Paint.Align.CENTER);
		// list = mLyric.list;

	}

	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// Log.e("Update", "onDraw");
		canvas.drawColor(brackgroundcolor);
		NotCurrentPaint.setColor(notCurrentPaintColor);
		CurrentPaint.setColor(CurrentPaintColor);

		NotCurrentPaint.setTextSize(lrcTextSize);
		// NotCurrentPaint.setColor(notCurrentPaintColor);
		NotCurrentPaint.setTypeface(Texttypeface);

		CurrentPaint.setTextSize(lrcTextSize);
		CurrentPaint.setTypeface(CurrentTexttypeface);

		// // ���ȿ��ǲ��ǳ�ʼ�������
		// if (!Lyric.initDone) {
		// Sentence temp = new Sentence("Search Lyric...");
		// canvas.drawText(temp.getContent(), width / 2, height / 2,
		// CurrentPaint);
		// return;
		// }

		if (index == -1)
			return;

		float plus = currentDunringTime == 0 ? 30
				: 30
						+ (((float) currentTime - (float) sentenctTime) / (float) currentDunringTime)
						* (float) 30;

		// ���Ϲ��� ����Ǹ��ݸ�ʵ�ʱ�䳤������������������
		canvas.translate(0, -plus);
		// �Ȼ���ǰ�У�֮���ٻ�����ǰ��ͺ��棬�����ͱ��ֵ�ǰ�����м��λ��
		try {
			canvas.drawText(Sentencelist.get(index).getContent(), width / 2,
					height / 2, CurrentPaint);
			// canvas.translate(0, plus);
			float tempY = height / 2;
			// ��������֮ǰ�ľ���
			for (int i = index - 1; i >= 0; i--) {
				// Sentence sen = list.get(i);
				// ��������
				tempY = tempY - TextHeight;
				if (tempY < 0) {
					break;
				}
				canvas.drawText(Sentencelist.get(i).getContent(), width / 2,
						tempY, NotCurrentPaint);
				// canvas.translate(0, TextHeight);
			}

			tempY = height / 2;
			// ��������֮��ľ���
			for (int i = index + 1; i < Sentencelist.size(); i++) {
				// ��������
				tempY = tempY + TextHeight;
				if (tempY > height) {
					break;
				}
				canvas.drawText(Sentencelist.get(i).getContent(), width / 2,
						tempY, NotCurrentPaint);
				// canvas.translate(0, TextHeight);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	protected void onSizeChanged(int w, int h, int ow, int oh) {
		super.onSizeChanged(w, h, ow, oh);
		width = w; // remember the center of the screen
		height = h;
		// middleY = h * 0.5f;
	}

	//
	/**
	 * @param time
	 *            ��ǰ��ʵ�ʱ����
	 * 
	 * @return null
	 */
	public void updateIndex(long time) {

		this.currentTime = time;
		// ������
		index = mLyric.getNowSentenceIndex(time);
		if (index != -1) {
			Sentence sen = Sentencelist.get(index);
			String nowlrcsen=sen.getContent();
			if ("".equals(nowsen)||!nowlrcsen.equals(nowsen)) {
				Intent intent=new Intent(SuperActivity.ACTION_UPDATE_LRC);
				intent.putExtra("lrccurr", sen.getContent());
				MyApplication.context.sendBroadcast(intent);
				nowsen=nowlrcsen;
			}
			sentenctTime = sen.getFromTime();
			currentDunringTime = sen.getDuring();
	
		}
	}

}
