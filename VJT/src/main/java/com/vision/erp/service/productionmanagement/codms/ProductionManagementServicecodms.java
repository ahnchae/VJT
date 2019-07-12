package com.vision.erp.service.productionmanagement.codms;

import java.util.List;

import com.vision.erp.common.Search;
import com.vision.erp.service.domain.OrderFromBranch;
import com.vision.erp.service.domain.OrderFromBranchProduct;

public interface ProductionManagementServicecodms {
	
	//[����] �ֹ������
	public void addOrderFromBranch(OrderFromBranch orderFromBranch) throws Exception;
	
	//[����, ����]�ֹ������º���(�ֹ���ҿ�û, �ֹ����Ȯ��, �ֹ��Ϸ�), �ֹ����Ȯ���� �ֹ���ǰ���� ������ҷ� ����
	public void modifyOrderFromBranchStatus(OrderFromBranch orderFromBranch) throws Exception;
	
	//[����, ����]�ֹ�������Ʈ ��������(�ֹ���ǰ ä����) ������ȣ searchKeyword�� ä���
	public List<OrderFromBranch> getOrderFromBranchList(Search search) throws Exception;
	
	//[����, ����]�ֹ��� �󼼺���
	public OrderFromBranch getOrderFromBranchDetail(Search search) throws Exception;
	
	//[����] �ֹ���ǰ ���º���(���ϿϷ�, ���ϴ��, �������)
	public void modifyOrderFromBranchProductStatus(OrderFromBranchProduct orderFromBranchProduct) throws Exception;
	
}
