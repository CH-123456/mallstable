package com.example.mallstable.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class ActionOrderVo implements Serializable {
	private Long orderNo;//订单编号
	private BigDecimal amount;//订单价格
	private Integer type;//支付类型
	private String typeDesc;//订单状态描述
	private Integer freight;//邮费支付类型
	private Integer status;//订单状态
	private String statusDesc;//订单状态描述
	private String paymentTime;//支付时间
	private String deliveryTime;//邮寄时间
	private String finishTime;//交易完成时间
	private String closeTime;//交易关闭时间
	private String created;//交易创建时间
	
	private List<ActionorderItemVo> orderItems;//订单详情
	private Integer addrId;//收货地址id
	private String deliverName;//收货人姓名
	private Address address;//收货人详情
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getFreight() {
		return freight;
	}
	public void setFreight(Integer freight) {
		this.freight = freight;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public String getPaymentTime() {
		return paymentTime;
	}
	public void setPaymentTime(String paymentTime) {
		this.paymentTime = paymentTime;
	}
	public String getDeliveryTime() {
		return deliveryTime;
	}
	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	public String getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}
	public String getCloseTime() {
		return closeTime;
	}
	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public List<ActionorderItemVo> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<ActionorderItemVo> orderItems) {
		this.orderItems = orderItems;
	}
	public Integer getAddrId() {
		return addrId;
	}
	public void setAddrId(Integer addrId) {
		this.addrId = addrId;
	}
	public String getDeliverName() {
		return deliverName;
	}
	public void setDeliverName(String deliverName) {
		this.deliverName = deliverName;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getTypeDesc() {
		return typeDesc;
	}
	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}
	
	
}
