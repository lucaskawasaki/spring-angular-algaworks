package com.example.algamoneyapi.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("algamoney")
public class AlgamoneyApiProperty {
	
	private Seguranca seguranca = new Seguranca();
	private String originPermitida = "http://localhost:8000";
	
	public String getOriginPermitida() {
		return originPermitida;
	}
	
	public void setOriginPermitida(String originPermitida) {
		this.originPermitida = originPermitida;
	}
	
	public Seguranca getSeguranca() {
		return seguranca;
	}
	
	public static class Seguranca{
		private boolean enableHttps;

		public boolean isEnableHttps() {
			return enableHttps;
		}

		public void setEnableHttps(boolean enableHttps) {
			this.enableHttps = enableHttps;
		}
		
		
	}

}
