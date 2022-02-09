package com.myprojects.admin.service.cardnumber;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myprojects.admin.common.CommonUtil;
import com.myprojects.admin.dao.cardnumber.CardNumberDAO;
import com.myprojects.admin.entity.CardNumbers;
import com.myprojects.admin.util.cardnumber.CardNumberDTO;

@Service
public class CardNumberServiceImpl implements CardNumberService {

	@Autowired
	private CardNumberDAO dao;

	@Override
	public List<CardNumberDTO> getCardNumberBySupplierId(Long supplierId) {
		List<CardNumberDTO> dtoList = new ArrayList<>();
		List<CardNumbers> entityList = dao.getCardNumberBySupplierId(supplierId);
		if(CommonUtil.validList(entityList)) {
			entityList.stream().forEach(e->{
				dtoList.add(new CardNumberDTO(e));
			});
		}
		return dtoList;
	}

}
