package org.xiaoguo.game.net;


public interface BaseAction<P, T> {

	public void execute(P p, T message);

}
