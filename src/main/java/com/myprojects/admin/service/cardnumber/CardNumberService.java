package com.myprojects.admin.service.cardnumber;

import java.util.List;

import com.myprojects.admin.util.cardnumber.CardNumberDTO;

public interface CardNumberService {

	List<CardNumberDTO> getCardNumberBySupplierId(Long supplierId);

}
