package com.vision.erp.service.productionmanagement.codms.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.vision.erp.common.Search;
import com.vision.erp.service.accounting.AccountingDAO;
import com.vision.erp.service.domain.OrderFromBranch;
import com.vision.erp.service.domain.OrderFromBranchProduct;
import com.vision.erp.service.domain.Product;
import com.vision.erp.service.domain.Statement;
import com.vision.erp.service.productionmanagement.codms.ProductionManagementDAOcodms;
import com.vision.erp.service.productionmanagement.codms.ProductionManagementServicecodms;
import com.vision.erp.service.productionmanagement.rudwn.ProductionManagementDAOrudwn;

@Service("productionManagementServiceImplcodms")
public class ProductionManagementServiceImplcodms implements ProductionManagementServicecodms {

	//field
	@Resource(name="productionManagementDAOImplcodms")
	private ProductionManagementDAOcodms productionManagementDAOcodms;
	
	@Resource(name="productionManagementDAOImplrudwn")
	private ProductionManagementDAOrudwn productionManagementDAOrudwn;
	
	//@Resource(name="accountingDAOImpl")
	private AccountingDAO accountingDAO;
	
	//[����] �ֹ������
	@Override
	public void addOrderFromBranch(OrderFromBranch orderFromBranch) throws Exception {
		
		//���� ���� ����ϰ� �ֹ����� �����ϱ�
		List<OrderFromBranchProduct> finalList = new ArrayList<OrderFromBranchProduct>(); 
		int orderFromBranchTotalAmount = 0;
		for(OrderFromBranchProduct op : orderFromBranch.getOrderFromBranchProductList()) {
			OrderFromBranchProduct resultOp = calculateOrderFromBranchProduct(op);
			finalList.add(resultOp);
			orderFromBranchTotalAmount += Integer.parseInt(op.getPrice());
		}
		orderFromBranch.setOrderFromBranchProductList(finalList);
		orderFromBranch.setOrderFromBranchTotalAmount(""+orderFromBranchTotalAmount);
		
		//��ǥ����ϰ� �ֹ����� ��ǥ��ȣ �����ϱ�
		orderFromBranch.setStatementNo(addStatement(orderFromBranch).getStatementNo());
		
		//�ֹ��� ���
		//������ȣ, ��ǥ��ȣ, �Ѱ����� �־����
		productionManagementDAOcodms.insertOrderFromBranch(orderFromBranch);
		
		//�ֹ���ǰ ���
		//�ֹ�����ȣ, ��ǰ��ȣ, ����, ����, �ݾ��� �־����
		for(OrderFromBranchProduct op : orderFromBranch.getOrderFromBranchProductList()) {
			productionManagementDAOcodms.insertOrderFromBranchProduct(op.setOrderFromBranchNo(orderFromBranch.getOrderFromBranchNo()));
		}
		
	}
	
	//[����, ����]�ֹ������º���(�ֹ����01, �ֹ��Ϸ�02, �ֹ�����03, ��ҿ�û04, ���Ȯ��05)
	@Override
	public void modifyOrderFromBranchStatus(OrderFromBranch orderFromBranch) throws Exception {
		//�ֹ������� �����ϱ�
		productionManagementDAOcodms.updateOrderFromBranchStatus(orderFromBranch);
		
		//�ֹ���ҽ� �ֹ���ǰ ����öȸ�ϱ�
		if(orderFromBranch.getOrderFromBranchStatusCodeNo()=="04") {
			for(OrderFromBranchProduct op : orderFromBranch.getOrderFromBranchProductList()) {
				//�ֹ���ǰ����(���ϴ��01, ���ϿϷ�02, ����öȸ03)
				op.setOrderFromBranchProductStatusCodeNo("03");
				productionManagementDAOcodms.updateOrderFromBranchProductStatus(op);
			}
		}
	}

	//[����, ����]�ֹ�������Ʈ ��������(�ֹ���ǰ ä����) ������ȣ searchKeyword�� ä���
	@Override
	public List<OrderFromBranch> getOrderFromBranchList(Search search) throws Exception {
		return productionManagementDAOcodms.selectOrderFromBranchList(search);
	}

	//[����, ����]�ֹ��� �󼼺���
	@Override
	public OrderFromBranch getOrderFromBranchDetail(Search search) throws Exception {
		// TODO Auto-generated method stub
		return productionManagementDAOcodms.selectOrderFromBranchDetail(search);
	}

	//[����] �ֹ���ǰ ���º���(���ϴ��01, ���ϿϷ�02, ����öȸ03)
	//�ֹ�����ȣ, ��ǰ��ȣ �־����
	@Override
	public void modifyOrderFromBranchProductStatus(OrderFromBranchProduct orderFromBranchProduct) throws Exception {
		// TODO Auto-generated method stub
		//��ǰ���º����ϱ�
		productionManagementDAOcodms.updateOrderFromBranchProductStatus(orderFromBranchProduct);
		
		//�ֹ����� ��ǰ�� ���� ���ϿϷ��� �ֹ������� ����
		if(orderFromBranchProduct.getOrderFromBranchProductStatusCodeNo().equals("02")) {
			int notDeliveredYet = productionManagementDAOcodms.selectOrderFromBranchProduct(orderFromBranchProduct.getOrderFromBranchNo());
			if(notDeliveredYet==0) {
				OrderFromBranch ob = new OrderFromBranch();
				ob.setOrderFromBranchStatusCodeNo("02");
				ob.setOrderFromBranchNo(orderFromBranchProduct.getOrderFromBranchNo());
				productionManagementDAOcodms.updateOrderFromBranchStatus(ob);
			}
		}
	}

	//�ֹ���ǰ �ݾ� ����ϱ�
	private OrderFromBranchProduct calculateOrderFromBranchProduct(OrderFromBranchProduct op) throws Exception{
		Product product = productionManagementDAOrudwn.selectDetailProduct(op.getProductNo());
		op.setPrice(product.getSalesPrice());
		op.setOrderFromBranchProductAmount(""+(Integer.parseInt(product.getSalesPrice())*Integer.parseInt(op.getOrderFromBranchProductAmount())));
		return op;
	}
	
	//��ǥ ����ϱ�
	private Statement addStatement(OrderFromBranch orderFromBranch) throws Exception {
		Statement statement = new Statement();
		statement.setTradeDate(orderFromBranch.getOrderDate());
		statement.setTradeTargetName(orderFromBranch.getBranchName());
		statement.setStatementCategoryCodeNo("01");
		statement.setStatementDetail("�ֹ�");
		statement.setAccountNo(orderFromBranch.getAccountNo());
		statement.setTradeAmount(orderFromBranch.getOrderFromBranchTotalAmount());
		accountingDAO.insertStatement(statement);
		
		return statement;
	}
}
