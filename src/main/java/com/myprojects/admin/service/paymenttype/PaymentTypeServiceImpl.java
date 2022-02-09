package com.myprojects.admin.service.paymenttype;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myprojects.admin.aspect.CheckMethod;
import com.myprojects.admin.common.CommonUtil;
import com.myprojects.admin.dao.paymenttype.PaymentTypeDAO;
import com.myprojects.admin.entity.PaymentType;
import com.myprojects.admin.util.paymenttype.PaymentTypeDTO;

@Service
@Transactional
public class PaymentTypeServiceImpl implements PaymentTypeService {
	@Autowired
	private PaymentTypeDAO paymentDAO;

	@Override
	@CheckMethod
	public List<PaymentTypeDTO> getAll() {
		List<PaymentType> ptList = paymentDAO.getAll();
		List<PaymentTypeDTO> dtoList = new ArrayList<>();
		if (CommonUtil.validList(ptList)) {
			ptList.stream().forEach(pt -> {
				dtoList.add(new PaymentTypeDTO(pt));
			});
		}
		return dtoList;
	}

}
