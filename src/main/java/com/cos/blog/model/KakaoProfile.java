package com.cos.blog.model;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Generated("jsonschema2pojo")
@Data
public class KakaoProfile {

	public Long id;
	public String connected_at;
	public Properties properties;
	public KakaoAccount kakaoAccount;
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	@Data
	public class Properties {

		public String nickname;
		public String profile_image;
		public String thumbnail_image;
		private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

		public Map<String, Object> getAdditionalProperties() {
			return this.additionalProperties;
		}

		public void setAdditionalProperty(String name, Object value) {
			this.additionalProperties.put(name, value);
		}

	}

	@Data
	public class KakaoAccount {

		public Boolean profileNicknameNeedsAgreement;
		public Boolean profileImageNeedsAgreement;
		public Profile profile;
		private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

		public Map<String, Object> getAdditionalProperties() {
			return this.additionalProperties;
		}

		public void setAdditionalProperty(String name, Object value) {
			this.additionalProperties.put(name, value);
		}

	}

	@Data
	public class Profile {

		public String nickname;
		public String thumbnailImageUrl;
		public String profileImageUrl;
		public Boolean isDefaultImage;
		private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

		public Map<String, Object> getAdditionalProperties() {
			return this.additionalProperties;
		}

		public void setAdditionalProperty(String name, Object value) {
			this.additionalProperties.put(name, value);
		}

	}

}
