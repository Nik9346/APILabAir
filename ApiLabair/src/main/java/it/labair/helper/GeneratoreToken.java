package it.labair.helper;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Base64;

import org.springframework.stereotype.Component;

@Component
public class GeneratoreToken {

	public String generazioneToken(String userName) {
		LocalDateTime now = LocalDateTime.now();
		Instant instant = now.toInstant(OffsetDateTime.now().getOffset());
		long timestamp = instant.getEpochSecond()*1000;
		String usernameCodificato = Base64.getEncoder().encodeToString(userName.getBytes());
		return usernameCodificato + "_" + timestamp;
	}
}
