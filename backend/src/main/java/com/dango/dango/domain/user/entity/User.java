package com.dango.dango.domain.user.entity;

import java.time.LocalDateTime;
import java.util.Collection;

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
	@Column(name = "id",nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "refrigerator_id",nullable = false)
	private Long refrigeratorId;

	@Column(name = "email",nullable = false)
	private String username;

	@Column(name = "password",nullable = false)
	private String password;

	@Column(name = "nickname",nullable = false,unique = true)
	private String nickname;

	@UpdateTimestamp
	@Column(name = "update_time",nullable = false)
	private LocalDateTime updateTime;

	@CreationTimestamp
	@Column(name = "create_time",nullable = false)
	private LocalDateTime createTime;

	@Column(name = "delete_time")
	private LocalDateTime deleteTime;

	@Column(name = "deleted",nullable = false)
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
