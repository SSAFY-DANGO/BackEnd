package com.dango.dango.domain.user.entity;

import java.time.LocalDateTime;
import java.util.Collection;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "users")
@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

	@Id
	@NotNull
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(name = "refrigerator_id")
	private Long refrigeratorId;

	@NotNull
	@Column(name = "email")
	private String username;

	@NotNull
	@Column(name = "password")
	private String password;

	@NotNull
	@Column(name = "nickname",unique = true)
	private String nickname;

	@UpdateTimestamp
	@NotNull
	@Column(name = "update_time")
	private LocalDateTime updateTime;

	@CreationTimestamp
	@NotNull
	@Column(name = "create_time")
	private LocalDateTime createTime;

	@NotNull
	@Column(name = "delete_time")
	private LocalDateTime deleteTime;

	@ColumnDefault("false")
	@NotNull
	@Column(name = "deleted",columnDefinition = "TINYINT(1)")
	private boolean deleted;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}
}
