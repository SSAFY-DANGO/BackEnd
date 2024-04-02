package com.dango.dango.domain.detectLog.entity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.annotations.Type;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Data
@Table(name = "detect_log")
public class DetectLog {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column(name = "input_time")
	LocalDateTime inputTime;

	@Type(JsonType.class)
	@Column(columnDefinition = "data")
	private Map<String, Integer> data = new HashMap<>();

	@Column(columnDefinition = "input_type")
	private String inputType;

	@Column(columnDefinition = "device")
	private String device;
}
