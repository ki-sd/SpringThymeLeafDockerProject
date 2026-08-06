package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Chef {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int no;
	private String chef;
	private String poster;
	private String mem_cont1,mem_cont2,mem_cont3,mem_cont7;
}
