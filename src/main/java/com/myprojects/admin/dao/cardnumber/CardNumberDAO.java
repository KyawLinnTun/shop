package com.myprojects.admin.dao.cardnumber;

import java.util.List;

import com.myprojects.admin.dao.generic.GenericDao;
import com.myprojects.admin.entity.CardNumbers;

public interface CardNumberDAO extends GenericDao<CardNumbers, Long> {

	List<CardNumbers> getCardNumberBySupplierId(Long supplierId);

}
