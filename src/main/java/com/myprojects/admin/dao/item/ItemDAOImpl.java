package com.myprojects.admin.dao.item;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.myprojects.admin.aspect.CheckMethod;
import com.myprojects.admin.common.CommonUtil;
import com.myprojects.admin.common.enumeration.CommonStatus;
import com.myprojects.admin.dao.generic.GenericDaoImpl;
import com.myprojects.admin.entity.Item;
import com.myprojects.admin.util.item.ItemDTO;

@Repository
@Transactional
public class ItemDAOImpl extends GenericDaoImpl<Item, Long> implements ItemDAO {

	@Override
	@CheckMethod
	public Item getItemByCriterias(String itemName, String size, String color, String manufactureCountry, String brand,
			Long categoryId) {
		Criteria c = getCurrentSession().createCriteria(Item.class);
		if (CommonUtil.validString(itemName)) {
			c.add(Restrictions.eq("name", itemName));
		}
		if (CommonUtil.validString(size)) {
			c.add(Restrictions.eq("size", size));
		}
		if (CommonUtil.validString(color)) {
			c.add(Restrictions.eq("color", color));
		}
		if (CommonUtil.validString(manufactureCountry)) {
			c.add(Restrictions.eq("manufactureCountry", manufactureCountry));
		}
		if (CommonUtil.validString(brand)) {
			c.add(Restrictions.eq("brand", brand));
		}
		if (CommonUtil.validLong(categoryId)) {
			c.createAlias("category", "c");
			c.add(Restrictions.eq("c.id", categoryId));
		}
		return CommonUtil.validList(c.list()) ? (Item) c.list().get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	@CheckMethod
	public List<Item> searchItem(ItemDTO itemDTO) {
		Criteria c = getCurrentSession().createCriteria(Item.class);
		if (itemDTO != null) {
			if (CommonUtil.validString(itemDTO.getName())) {
				c.add(Restrictions.like("name", itemDTO.getName(), MatchMode.ANYWHERE));
			}
			if (itemDTO.getCategoryDTO() != null && CommonUtil.validLong(itemDTO.getCategoryDTO().getId())) {
				c.createAlias("category", "c");
				c.add(Restrictions.eq("c.id", itemDTO.getCategoryDTO().getId()));
			}
			if (CommonUtil.validInteger(itemDTO.getStatus())) {
				if (CommonStatus.ACTIVE.getCode() == itemDTO.getStatus()) {
					c.add(Restrictions.eq("status", itemDTO.getStatus()));
				} else {
					c.add(Restrictions.or(Restrictions.eq("status", itemDTO.getStatus()),
							Restrictions.isNull("status")));
				}
			}
		}
		return c.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ItemDTO> selectDuplicateItems(Long itemId, String itemName, String language) throws Exception {
		StringBuilder query = new StringBuilder();
		query.append(
				"Select i.Id as id,i.Name as name,i.size as size,i.manufactureCountry as manufactureCountry,i.color as color,i.brand as brand,i.status as status");
		if (CommonUtil.validString(language)) {
			query.append(",c.mmFontName as categoryName");
		} else {
			query.append(",c.name as categoryName");
		}
		query.append(" from item i join category c on i.Category_Id=c.id");
		query.append(" where i.name like :itemName");
		if (CommonUtil.validLong(itemId)) {
			query.append(" And i.id!= :itemId");
		}
		SQLQuery q = getCurrentSession().createSQLQuery(query.toString()).addScalar("id", StandardBasicTypes.LONG)
				.addScalar("name", StandardBasicTypes.STRING).addScalar("size", StandardBasicTypes.STRING)
				.addScalar("manufactureCountry", StandardBasicTypes.STRING)
				.addScalar("color", StandardBasicTypes.STRING).addScalar("brand", StandardBasicTypes.STRING)
				.addScalar("categoryName", StandardBasicTypes.STRING).addScalar("status", StandardBasicTypes.INTEGER);
		q.setResultTransformer(Transformers.aliasToBean(ItemDTO.class));
		q.setParameter("itemName", "%" + itemName + "%");
		if (CommonUtil.validLong(itemId)) {
			q.setParameter("itemId", itemId);
		}
		return q.list();
	}

}
