package net.londonunderground.mod.render;

import org.mtr.mapping.holder.Direction;
import org.mtr.mapping.holder.Identifier;
import org.mtr.mapping.mapper.GraphicsHolder;
import org.mtr.mod.Init;
import org.mtr.mod.block.BlockSignalBase;
import org.mtr.mod.client.IDrawing;
import org.mtr.mod.data.IGui;
import org.mtr.mod.render.MainRenderer;
import org.mtr.mod.render.QueuedRenderLayer;
import org.mtr.mod.render.RenderSignalBase;
import org.mtr.mod.render.StoredMatrixTransformations;

import javax.annotation.Nonnull;

public class RenderTunnelSignalLight<T extends BlockSignalBase.BlockEntityBase> extends RenderSignalBase<T> implements IGui {

	private final boolean redOnTop;
	private final int proceedColor;

	public RenderTunnelSignalLight(Argument dispatcher, boolean redOnTop, int proceedColor) {
		super(dispatcher, 16, 2);
		this.redOnTop = redOnTop;
		this.proceedColor = proceedColor;
	}

	@Override
	protected void render(@Nonnull StoredMatrixTransformations storedMatrixTransformations, @Nonnull T entity, float tickDelta, int occupiedAspect, boolean isBackSide) {
		final float y = occupiedAspect > 0 == redOnTop ? 0.25F : 0.4375F;
		MainRenderer.scheduleRender(new Identifier(Init.MOD_ID, "textures/block/white.png"), false, QueuedRenderLayer.LIGHT, (graphicsHolder, offset) -> {
			storedMatrixTransformations.transform(graphicsHolder, offset);
			IDrawing.drawTexture(graphicsHolder, -0.25F, y, 0.3125F, -0.0625F, y + 0.1875F, 0.3125F, Direction.UP, occupiedAspect > 0 ? 0xFFFF0000 : proceedColor, GraphicsHolder.getDefaultLight());
			graphicsHolder.pop();
		});
	}
}
