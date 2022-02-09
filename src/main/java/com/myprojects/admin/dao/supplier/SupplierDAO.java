package com.myprojects.admin.dao.supplier;

import java.sql.SQLException;
import java.util.List;

import com.myprojects.admin.dao.generic.GenericDao;
import com.myprojects.admin.entity.Supplier;
import com.myprojects.admin.util.supplier.SupplierDTO;

public interface SupplierDAO extends GenericDao<Supplier, Long>{

	List<SupplierDTO> searchSupplier(SupplierDTO dto) throws Exception;

}
