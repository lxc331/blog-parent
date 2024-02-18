package com.example.blog.chat.po;

import lombok.Data;

@Data
public class Staff {
	private byte staff_id;
	private String first_name;
	private String last_name;
	private short address_id;
	private String email;
	private String username;
	private String password;
	private String last_update;
}