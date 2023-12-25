package com.javapai.framework.enums;

/**
 * 收付标志（借贷标记）。<br>
 * 
 * 借和贷本来就是记账符号，是会计都知道，<strong>资金占用</strong>增加、<strong>资金来源</strong>减少用“借”来记载；<strong>资金占用</strong>减少、<strong>资金来源</strong>增加用“贷”来记载。<br>
 * 由于银行与企业是对口的，企业的“借”与“贷”的记录正好与银行相反，同一笔业务（譬如贷款）在企业是：资金来源增加用“贷”来记载，而在银行却是资金占用增加用“借”来记载，这也是外人搞不清银行记账的主要原因。
 * 
 * @author pooja
 *
 */
public enum SfbzEnum {
	/**
	 * 收付标志-贷（Credit）
	 */
	C("C", "转入"),
	/**
	 * 收付标志-借（Debit：表示资金的支出或减少,在账户中是负值,通常用于记载支出、取款等。）
	 */
	D("D", "转出");
	
	private String key;
	private String value;

	private SfbzEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}
}
