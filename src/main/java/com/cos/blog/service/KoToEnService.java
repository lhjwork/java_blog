package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.cos.blog.model.KoToEn;

public class KoToEnService {
	
	@Autowired
	private KoToEn koToEn;
	
	public String KoToEnTransForm(String word) {
		// 분리할 단어
		String result		= "";									// 결과 저장할 변수
		String resultEng	= "";									// 알파벳으로
		
		for (int i = 0; i < word.length(); i++) {
			
			/*  한글자씩 읽어들인다. */
			char chars = (char) (word.charAt(i) - 0xAC00);

			if (chars >= 0 && chars <= 11172) {
				/* A. 자음과 모음이 합쳐진 글자인경우 */

				/* A-1. 초/중/종성 분리 */
				int chosung 	= chars / (21 * 28);
				int jungsung 	= chars % (21 * 28) / 28;
				int jongsung 	= chars % (21 * 28) % 28;

				
				/* A-2. result에 담기 */
				result = result + koToEn.arrChoSung[chosung] + koToEn.arrJungSung[jungsung];

				
				/* 자음분리 */
				if (jongsung != 0x0000) {
					/* A-3. 종성이 존재할경우 result에 담는다 */
					result =  result + koToEn.arrJongSung[jongsung];
				}

				/* 알파벳으로 */
				resultEng = resultEng + koToEn.arrChoSungEng[chosung] + koToEn.arrJungSungEng[jungsung];
				if (jongsung != 0x0000) {
					/* A-3. 종성이 존재할경우 result에 담는다 */
					resultEng =  resultEng + koToEn.arrJongSungEng[jongsung];
				}

			} else {
				/* B. 한글이 아니거나 자음만 있을경우 */
				
				/* 자음분리 */
				result = result + ((char)(chars + 0xAC00));
				
				/* 알파벳으로 */
				if( chars>=34097 && chars<=34126){
					/* 단일자음인 경우 */
					int jaum 	= (chars-34097);
					resultEng = resultEng + koToEn.arrSingleJaumEng[jaum];
				} else if( chars>=34127 && chars<=34147) {
					/* 단일모음인 경우 */
					int moum 	= (chars-34127);
					resultEng = resultEng + koToEn.arrJungSungEng[moum];
				} else {
					/* 알파벳인 경우 */
					resultEng = resultEng + ((char)(chars + 0xAC00));
				}
			}//if
			
		}//for
		return resultEng;
	}
	
	
}
