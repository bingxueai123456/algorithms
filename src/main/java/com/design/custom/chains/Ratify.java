package com.design.custom.chains;

/**
 * @Auther: eclair
 * @Date: 2019/9/5 23:05
 * @Description:
 */
public interface Ratify {
	Result deal(Chain chain);

	interface Chain {
	}
}
