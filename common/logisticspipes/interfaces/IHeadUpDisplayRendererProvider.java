package logisticspipes.interfaces;

import net.minecraft.src.World;


public interface IHeadUpDisplayRendererProvider {
	public IHeadUpDisplayRenderer getRenderer();
	public int getX();
	public int getY();
	public int getZ();
	public World getWorld();
	public void startWaitching();
	public void stopWaitching();
}
