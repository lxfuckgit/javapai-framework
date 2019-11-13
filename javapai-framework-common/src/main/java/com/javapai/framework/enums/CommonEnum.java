//package com.javapai.framework.enums;
//
///**
// * 
// * @author pooja
// *
// */
//public class CommonEnum {
//	
//	/**
//	 * 提货计划已取消确认
//	 */
//	BUY_ORDER_CANCELCONFIRM,
//	/**
//	 * 提货计划已全部下发
//	 */
//	BUY_ORDER_ACTIONED,
//
//	/**
//	 * 已收货完成关闭
//	 */
//	BUY_ORDER_ALLCLOSED,
//	/**
//	 * 有余量未收货关闭
//	 */
//	BUY_ORDER_PARTCLOSED,
//
//	/**
//	 * 提货计划已审核
//	 */
//	BUY_ORDER_CHECKED,
//	/**
//	 * 提货计划待重审
//	 */
//	BUY_ORDER_WAITCHECK,
//	/**
//	 * 提货计划已生成派车单
//	 */
//	BUY_ORDER_SENTCARORDERED,
//	/**
//	 * 提货计划提货派车
//	 */
//	BUY_ORDER_SENTCAR,
//	/**
//	 * 提货计划已全部生成派车单
//	 */
//	BUY_ORDER_ALLSENTORDER,
//	/**
//	 * 提货计划部分派车
//	 */
//	BUY_ORDER_PARTSENTORDER,
//	/**
//	 * 已生成派车单
//	 */
//	BUY_ORDER_SENDTRUCKORDERED,
//	/**
//	 * 部分提货派车
//	 */
//	BUY_ORDER_PARTSENDTRUCKED,
//	/**
//	 * 全部提货派车
//	 */
//	BUY_ORDER_ALLSENDTRUCKED,
//	/**
//	 * 提货计划提货途中
//	 */
//	BUY_ORDER_ONTHEWAY, SENTCAR_ORDER_RECHECKED,
//	/**
//	 * 提货计划部分提货途中
//	 */
//	BUY_ORDER_PARTONTHEWAY,
//	/**
//	 * 提货计划全部提货途中
//	 */
//	BUY_ORDER_ALLONTHEWAY,
//	/**
//	 * 提货计划提货部分完毕
//	 */
//	BUY_ORDER_PARTBUYORDERFINISH,
//
//	/**
//	 * 提货计划全部提货完毕
//	 */
//	BUY_ORDER_ALLBUYORDERFINISH,
//
//	/**
//	 * 提货计划部分核收
//	 */
//	BUY_ORDER_PARTCUSTOMERCHECKED,
//	/**
//	 * 提货计划全部核收
//	 */
//	BUY_ORDER_ALLCUSTOMERCHECKED,
//
//	/**
//	 * 提货计划已解除核收
//	 */
//	BUY_ORDER_UNCHECKEDBUYORDER,
//	/**
//	 * 提货计划提货完毕
//	 */
//	BUY_ORDER_BUYORDERFINISH,
//
//	/**
//	 * 提货计划解除核收
//	 */
//	BUY_ORDER_CANCEL_ACCEPTED,
//
//	/**
//	 * 提货计划部分入库
//	 */
//	BUY_ORDER_PARTINORDER,
//	/**
//	 * 提货计划全部入库
//	 */
//	BUY_ORDER_FULLINORDER,
//	/**
//	 * 提货计划已挂起
//	 */
//	BUY_ORDER_HANG_UP,
//	/**
//	 * 提货计划物流中心入库
//	 */
//	BUY_ORDER_LOGISTICSCENTERINORDER,
//	/**
//	 * 到货计划创建
//	 */
//	ARRIVE_ORDER_CREATED,
//	/**
//	 * 到货计划已审核
//	 */
//	ARRIVE_ORDER_CHECKED,
//	/**
//	 * 到货计划取消
//	 */
//	ARRIVE_ORDER_CANCELED,
//
//	/**
//	 * 关闭到货计划
//	 */
//	ARRIVE_ORDER_CLOSED,
//	/**
//	 * 到货计划待重审
//	 */
//	ARRIVE_ORDER_RECHECKED,
//	/**
//	 * 到货计划修改
//	 */
//	ARRIVE_ORDER_UPDATED,
//	/**
//	 * 供货计划创建
//	 */
//	GET_ORDER_CREATED,
//	/**
//	 * 送货计划创建
//	 */
//	DELIVERY_ORDER_CREATED,
//
//	/**
//	 * 送货计划已修改
//	 */
//	DELIVERY_ORDER_UPDATED,
//
//	/**
//	 * 送货计划已关联
//	 */
//	DELIVERY_ORDER_LINKED,
//
//	/**
//	 * 送货单核收生成
//	 */
//	DELIVERY_ORDER_SIGN_CREATED,
//	/**
//	 * 送货计划已取消
//	 */
//	DELIVERY_ORDER_CANCELED,
//	/**
//	 * 供货计划修改
//	 */
//	GET_ORDER_UPDATE,
//	/**
//	 * 已审核供货计划
//	 */
//	GET_ORDER_CHECKED,
//	/**
//	 * 已部分生成派车单
//	 */
//	GET_ORDER_PARTSENTORDER,
//	/**
//	 * 已全部生成派车单
//	 */
//	GET_ORDER_ALLSENTORDER,
//	/**
//	 * 物流中心出库
//	 */
//	GET_ORDER_OUTORDERED,
//	/**
//	 * 供货派车
//	 */
//	GET_ORDER_SENDTRUCKED,
//	/**
//	 * 供货途中
//	 */
//	GET_ORDER_ONTHEWAY,
//	/**
//	 * 供货完毕
//	 */
//	GET_ORDER_GETORDERFINISH,
//	/**
//	 * 物流中心已签收
//	 */
//	GET_ORDER_LOGISTICSCENTERSIGNOFF,
//	/**
//	 * 客户已签收
//	 */
//	GET_ORDER_CUSTOMERSIGNOFFED,
//	/**
//	 * 客户已部分签收
//	 */
//	GET_ORDER_PARTCUSTOMERSIGNOFFED,
//	/**
//	 * 供货计划已关闭
//	 */
//	GET_ORDER_CLOSED,
//	/**
//	 * 供货计划已取消
//	 */
//	GET_ORDER_CANCELED,
//	/**
//	 * 待重审供货计划
//	 */
//	GET_ORDER_RECHECK,
//	/**
//	 * 已挂起供货计划
//	 */
//	GET_ORDER_HANGUP,
//	/**
//	 * 供货计划已解除签收
//	 */
//	GET_ORDER_UNSIGNFOR,
//	/**
//	 * 已创建派车单
//	 */
//	SENTCAR_ORDER_CREATE,
//	/**
//	 * 派车单已审核
//	 */
//	SENTCAR_ORDER_CONFIRMED,
//	/**
//	 * 运输途中
//	 */
//	SENTCAR_ORDER_TRANSING,
//	/**
//	 * 运输完成
//	 */
//	SENTCAR_ORDER_TRANSED,
//	/**
//	 * 已关闭派车单
//	 */
//	SENTCAR_ORDER_CLOSED,
//	/**
//	 * 已取消派车单
//	 */
//	SENTCAR_ORDER_CANCELED,
//
//	/**
//	 * 退货单已创建
//	 */
//	REFUND_ORDER_CREATED,
//	/**
//	 * 退货单已作废
//	 */
//	REFUND_ORDER_CANCELED,
//	/**
//	 * 退货单已修改
//	 */
//	REFUND_ORDER_UPDATED,
//
//	/**
//	 * 退货单已关闭
//	 */
//	REFUND_ORDER_CLOSED,
//
//	/**
//	 * 退货单已审核
//	 */
//	REFUND_ORDER_CHECKED,
//	/**
//	 * 退货单待重审
//	 */
//	REFUND_ORDER_RECHECKED,
//	/**
//	 * 全部派车
//	 */
//	DISPATCHIN_STATUS_ALL,
//	/**
//	 * 部分派车
//	 */
//	DISPATCHIN_STATUS_PART,
//	/**
//	 * 未派车
//	 */
//	DISPATCHIN_STATUS_NONE,
//	/**
//	 * 入库修改状态
//	 */
//	IN_ORDER_UPDATED,
//	/**
//	 * 出库修改状态
//	 */
//	OUT_ORDER_UPDATED
//
//	
//	
//	/**
//	 * 成品质检_物料位置_成品仓库
//	 */
//	private static final String TYPE_INVENTORY = "成品仓库";
//
//	/**
//	 * 配料质检_物料位置_工位
//	 */
//	private static final String TYPE_STATION = "工位";
//
//	/**
//	 * 配料质检_物料位置_仓库
//	 */
//	private static final String TYPE_WAREHOUSE = "仓库";
//
//	// ///////////////////////////////////////////////
//	// ///////////////////////////////////////////////
//
//	// ///////////////////////////////////////////////
//	// ///////////////////////////////////////////////
//	/**
//	 * 仓库类别[烟台配料仓库]
//	 */
//	private static final String WAREHOUSE_PL = "4028811f2c6248e8012c624d2ca7000d";
//
//	/**
//	 * 仓库类别[烟台成品仓库]
//	 */
//	private static final String WAREHOUSE_CP = "4028811f2c6248e8012c624d93ce0011";
//
//	/**
//	 * 仓库类别[烟台蒲湾仓库]
//	 */
//	private static final String WAREHOUSE_PW = "ORGYTPWCK";
//
//	// ///////////////////////////////////////////////
//	// ///////////////////////////////////////////////
//
//	// ///////////////////////////////////////////////
//	// ///////////////////////////////////////////////
//	/**
//	 * 创建进货状态
//	 */
//	public static final String GET_INORDER_CREATED = "IN_ORDER_CREATED";
//
//	/**
//	 * 完成进货状态
//	 */
//	public static final String GET_INORDER_FINISHED = "IN_ORDER_FINISHED";
//
//	/**
//	 * 创建出货状态
//	 */
//	public static final String GET_OUTORDER_CREATED = "OUT_ORDER_CREATED";
//
//	/**
//	 * 进货状态
//	 */
//	public static final String IN_ORDER_TYPE = "IN_ORDER";
//
//	/**
//	 * 出货状态
//	 */
//	public static final String OUT_ORDER_TYPE = "OUT_ORDER";
//
//	/**
//	 * 进货类型
//	 */
//	public static final String IN_ORDER_STYLE = "IN_ORDER_STYLE";
//
//	public enum YesNo implements Enums<String> {
//		Yes("YES", "是"), No("NO", "否");
//
//		private String key;
//		private String name;
//
//		private YesNo(String key, String name) {
//			this.key = key;
//			this.name = name;
//		}
//
//		@Override
//		public String getKey() {
//			// TODO Auto-generated method stub
//			return this.key;
//		}
//
//		@Override
//		public String getName() {
//			// TODO Auto-generated method stub
//			return this.name;
//		}
//
//		public static YesNo bool2YesNo(boolean flag) {
//			if (flag) {
//				return YesNo.Yes;
//			}
//			return YesNo.No;
//		}
//
//		public boolean boolValue() {
//			if (this.equals(YesNo.Yes)) {
//				return true;
//			}
//			return false;
//		}
//
//		@Override
//		public String getCode() {
//			// TODO Auto-generated method stub
//			return null;
//		}
//	}
//	
//	/**
//	 * 盘点状态
//	 */
//	public enum Pandian {
//		/* 新建立状态 */
//		SHIPMENT_ORDER_CREATED
//	}
//	
//	/**
//	 * 采购状态
//	 */
//	public enum Caigou {
//		// 在表STATUS_ITEM中,STATUS_TYPE为'BUY_ORDER'
//		/**
//		 * 备料单
//		 */
//		STOCK_ORDER_CREATED,
//		/**
//		 * 备料单已确认
//		 */
//		STOCK_ORDER_CONFIRM,
//		/**
//		 * 备料单已关闭
//		 */
//		STOCK_ORDER_CLOSED,
//		/**
//		 * 备料单已取消
//		 */
//		STOCK_ORDER_CANCELED
//	}
//
//}
