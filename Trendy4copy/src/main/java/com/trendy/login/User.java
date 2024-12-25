package com.trendy.login;

// 카카오톡, 네이버에서 정보 가져와서 User에 저장후
// mysql로 전달하기 위한 클래스

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String username;

    private String email;

    private String phoneNumber;

    private String profileImageUrl;
    
    private String receiptType1;
    private String receiptCardNumber1;
    private String bankName1;
    private String accountNumber1;
    private String accountHolder;
    private LocalDateTime createdAt1;
    private LocalDateTime updatedAt1;
    

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('personal_deduction', 'business_deduction', 'none') DEFAULT 'none'")
    private ReceiptType receiptType = ReceiptType.NONE;

    private String receiptCardNumber;

    private String bankName;

    private String accountNumber;

    private String accountHolder1;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    public User() {}

    public User(String username, String email, String profileImageUrl) {
        this.username = username;
        this.email = email;
        this.profileImageUrl = profileImageUrl;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public ReceiptType getReceiptType() {
        return receiptType;
    }

    public String getReceiptCardNumber() {
        return receiptCardNumber1;
    }

    public String getBankName() {
        return bankName1;
    }

    public String getAccountNumber() {
        return accountNumber1;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt1;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt1;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt1 = updatedAt;
    }

    public enum ReceiptType {
        PERSONAL_DEDUCTION, BUSINESS_DEDUCTION, NONE
    }

	public void setUsername(String username2) {
		this.username=username2;
		
	}

	public void setEmail(String email2) {
		this.email=email2;
		
	}

	public void setProfileImageUrl(String profileImageUrl2) {
		this.profileImageUrl=profileImageUrl2;
		
	}
}
