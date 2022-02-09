package com.myprojects.admin.service.supplier;

import java.sql.SQLException;
import java.util.List;

import com.myprojects.admin.util.supplier.SupplierDTO;

public interface SupplierService {

	SupplierDTO getById(Long id);

	SupplierDTO saveOrUpdateSupplier(SupplierDTO supplierDTO);

	List<SupplierDTO> searchSupplier(SupplierDTO dto, String language) throws Exception;

	boolean deleteSupplierById(Long id);

	List<SupplierDTO> getSupplierLabelAndIdByCategoryId(Long categoryId, String language) throws Exception;

}
