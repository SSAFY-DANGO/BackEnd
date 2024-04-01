package com.dango.dango.domain.door.entity;

import java.time.LocalDateTime;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "door")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Door {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "device_nickname")
	private String deviceNickname;

	@Column(name = "is_open")
	private Boolean isOpen;

	@Column(name = "time")
	private LocalDateTime time;


}
