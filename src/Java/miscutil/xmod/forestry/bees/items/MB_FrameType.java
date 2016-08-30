package miscutil.xmod.forestry.bees.items;

import forestry.api.apiculture.IBeeGenome;
import forestry.api.apiculture.IBeeModifier;

public enum MB_FrameType implements IBeeModifier
{
	//ExtraBees Clone Frames
	//Name, FrameHP, territory (1f), Mutation rate, lifespan rate, production rate, genetic decay (1f)	
	COCOA("Chocolate", 240, 1.0f, 1.0f, 0.50f, 1.50f, 1f),
	CAGE("Restraint",  240, 0.5f, 1.0f, 0.75f, 0.75f, 1f),
	SOUL("Soul", 	   80,  1.0f, 1.5f, 0.75f, 0.25f, 1f),
	CLAY("Healing",    240, 1.0f, 0.5f, 1.50f, 0.75f, 1f),
	NOVA("Nova",       240, 1.0f, 100.0f, 0.0001f, 1.00f, 1f),
	
	
	//Name, FrameHP, territory (1f), Mutation rate, lifespan rate, production rate, genetic decay (1f)	
	ACCELERATED("Accelerated", 175, 1f, 2.5f, 0.9f, 1.8f, 1f),
	VOID("Void", 20, 1f, 1f, 0.0001f, 10f, 1f),
	MUTAGENIC("Mutagenic", 3, 1f, 10f, 0.0001f, 10f, 1f),
	BUSY("Busy", 2000, 1f, 0f, 3f, 4f, 1f);

	private final String frameName;
	public final int maxDamage;

	private final float territoryMod;
	private final float mutationMod;
	private final float lifespanMod;
	private final float productionMod;
	private final float floweringMod;
	private final float geneticDecayMod;
	private final boolean isSealed;
	private final boolean isLit;
	private final boolean isSunlit;
	private final boolean isHellish;
	
	MB_FrameType(String name, int damage, float territory, float mutation, float lifespan, float production, float geneticDecay) {
		this(name, damage, territory, mutation, lifespan, production, 1f, geneticDecay, false, false, false, false);
	}
	
	MB_FrameType(String name, int damage,
						  float territory, float mutation, float lifespan, float production, float flowering, float geneticDecay,
						  boolean sealed, boolean lit, boolean sunlit, boolean hellish)
	{
		this.frameName = name;
		this.maxDamage = damage;
		
		this.territoryMod = territory;
		this.mutationMod = mutation;
		this.lifespanMod = lifespan;
		this.productionMod = production;
		this.floweringMod = flowering;
		this.geneticDecayMod = geneticDecay;
		this.isSealed = sealed;
		this.isLit = lit;
		this.isSunlit = sunlit;
		this.isHellish = hellish;
	}

	public String getName()
	{
		return this.frameName;
	}
	
	public String getLocalizedName()
	{
		return FR_StringUtil.getLocalizedString("frame." + this.frameName);
	}

	@Override
	public float getTerritoryModifier(IBeeGenome genome, float currentModifier) {
		return territoryMod;
	}

	@Override
	public float getMutationModifier(IBeeGenome genome, IBeeGenome mate, float currentModifier) {
		return mutationMod;
	}

	@Override
	public float getLifespanModifier(IBeeGenome genome, IBeeGenome mate, float currentModifier) {
		return lifespanMod;
	}

	@Override
	public float getProductionModifier(IBeeGenome genome, float currentModifier) {
		return productionMod;
	}

	@Override
	public float getFloweringModifier(IBeeGenome genome, float currentModifier) {
		return floweringMod;
	}

	@Override
	public float getGeneticDecay(IBeeGenome genome, float currentModifier) {
		return geneticDecayMod;
	}

	@Override
	public boolean isSealed() {
		return isSealed;
	}

	@Override
	public boolean isSelfLighted() {
		return isLit;
	}

	@Override
	public boolean isSunlightSimulated() {
		return isSunlit;
	}

	@Override
	public boolean isHellish() {
		return isHellish;
	}
}