package com.lzc.shop.order.action;

import java.io.IOException;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.lzc.shop.cart.vo.Cart;
import com.lzc.shop.cart.vo.CartItem;
import com.lzc.shop.order.service.OrderService;
import com.lzc.shop.order.vo.Order;
import com.lzc.shop.order.vo.OrderItem;
import com.lzc.shop.user.vo.User;
import com.lzc.shop.utils.PageBean;
import com.lzc.shop.utils.PaymentUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class OrderAction extends ActionSupport implements ModelDriven<Order> {
	private Order order = new Order();

	@Override
	public Order getModel() {
		return order;
	}

	// 接收支付通道编码:
	private String pd_FrpId;

	public void setPd_FrpId(String pd_FrpId) {
		this.pd_FrpId = pd_FrpId;
	}

	// 接收付款成功后的参数:
	private String r3_Amt;
	private String r6_Order;

	public void setR3_Amt(String r3_Amt) {
		this.r3_Amt = r3_Amt;
	}

	public void setR6_Order(String r6_Order) {
		this.r6_Order = r6_Order;
	}

	private int currentPage;

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	// 注入OrderService对象
	private OrderService orderService;

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	/**
	 * 提交订单执行的方法
	 * 
	 * @return
	 */
	public String submitorder() {
		// 保存到数据库
		// 补全order的信息:
		// 订单时间
		order.setOrdertime(new Date());
		// 订单状态
		order.setState(1); // 1 未付款 2 已付款未发货 3 已发货为确认订单 4 交易完成

		Cart cart = (Cart) ServletActionContext.getRequest().getSession()
				.getAttribute("cart");
		// 判断购物车是否为空
		if (cart == null) {
			this.addActionError("亲~您的购物车为空，请前往购物！");
			return "msg";
		}
		// 订单总计
		order.setTotal(cart.getTotal());
		// 从购物项中获取订单项信息
		for (CartItem cartItem : cart.getCartItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOrder(order);
			// 向订单里添加每一个订单项
			order.getOrderItems().add(orderItem);
		}

		// 用户信息
		User existUser = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("existUser");
		if (existUser == null) {
			this.addActionError("亲~您还没登录，请前往登录！");
			return "login";
		}
		order.setUser(existUser);

		// 调用orderService的save()方法
		orderService.save(order);

		// 把order对象保存到值栈，因为order刚好是模型驱动的对象，所以order对象本来就在栈顶位置，直接使用model就行

		return "submitorder";
	}

	/**
	 * 点击我的订单执行的方法
	 * 
	 * @return
	 */
	public String findByUid() {
		User existUser = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("existUser");
		PageBean<Order> pageBean = orderService.findByUid(existUser.getUid(),
				currentPage);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByUid";
	}

	/**
	 * 点击付款执行的方法
	 * 
	 * @return
	 */
	public String findByOid() {
		order = orderService.findByOid(order.getOid());
		return "findByOid";
	}

	// 为订单付款:
	public String payOrder() throws IOException {
		// 1.修改数据:
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setAddr(order.getAddr());
		currOrder.setName(order.getName());
		currOrder.setPhone(order.getPhone());
		// 修改订单
		orderService.update(currOrder);
		// 2.完成付款:
		// 付款需要的参数:
		String p0_Cmd = "Buy"; // 业务类型:
		String p1_MerId = "10001126856";// 商户编号:
		String p2_Order = order.getOid().toString();// 订单编号:
		String p3_Amt = "0.01"; // 付款金额:
		String p4_Cur = "CNY"; // 交易币种:
		String p5_Pid = ""; // 商品名称:
		String p6_Pcat = ""; // 商品种类:
		String p7_Pdesc = ""; // 商品描述:
		String p8_Url = "http://10.31.29.18:8080/shop/order_callBack.action"; // 商户接收支付成功数据的地址:
		String p9_SAF = ""; // 送货地址:
		String pa_MP = ""; // 商户扩展信息:
		String pd_FrpId = this.pd_FrpId;// 支付通道编码:
		String pr_NeedResponse = "1"; // 应答机制:
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl"; // 秘钥
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,
				p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
				pd_FrpId, pr_NeedResponse, keyValue); // hmac
		// 向易宝发送请求:
		StringBuffer sb = new StringBuffer(
				"https://www.yeepay.com/app-merchant-proxy/node?");
		sb.append("p0_Cmd=").append(p0_Cmd).append("&");
		sb.append("p1_MerId=").append(p1_MerId).append("&");
		sb.append("p2_Order=").append(p2_Order).append("&");
		sb.append("p3_Amt=").append(p3_Amt).append("&");
		sb.append("p4_Cur=").append(p4_Cur).append("&");
		sb.append("p5_Pid=").append(p5_Pid).append("&");
		sb.append("p6_Pcat=").append(p6_Pcat).append("&");
		sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		sb.append("p8_Url=").append(p8_Url).append("&");
		sb.append("p9_SAF=").append(p9_SAF).append("&");
		sb.append("pa_MP=").append(pa_MP).append("&");
		sb.append("pd_FrpId=").append(pd_FrpId).append("&");
		sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		sb.append("hmac=").append(hmac);

		// 重定向:向易宝出发:
		ServletActionContext.getResponse().sendRedirect(sb.toString());
		return NONE;
	}

	// 付款成功后跳转回来的路径:
	public String callBack() {
		// 修改订单的状态:
		Order currOrder = orderService.findByOid(Integer.parseInt(r6_Order));
		// 修改订单状态为2:已经付款:
		currOrder.setState(2);
		orderService.update(currOrder);
		this.addActionMessage("支付成功!订单编号为: " + r6_Order + " 付款金额为: " + r3_Amt);
		return "msg";
	}

	/**
	 * 确认收获
	 * 
	 * @return
	 */
	public String updateState() {
		order = orderService.findByOid(order.getOid());
		order.setState(order.getState() + 1);
		orderService.update(order);
		this.addActionMessage("确认收货成功！");
		return "updateState";
	}
}
