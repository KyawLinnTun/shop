package com.myprojects.admin.dao.paymenttype;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.myprojects.admin.dao.generic.GenericDaoImpl;
import com.myprojects.admin.entity.PaymentType;

@Repository
@Transactional
public class PaymentTypeDAOImpl extends GenericDaoImpl<PaymentType, Long> implements PaymentTypeDAO {

}
