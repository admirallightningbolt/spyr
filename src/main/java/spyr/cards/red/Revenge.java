package spyr.cards.red;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import spyr.cards.SpyrCard;

public class Revenge extends SpyrCard {

	public static final String ID = "spyr:revenge";
	public static final String NAME = "Revenge";
	public static final String DESCRIPTION = "Deal damage equal to your missing HP. NL Deals !D! damage.";

	private static final int COST = 3;
	private static final int UPGRADED_COST = 2;

	public Revenge() {
		super(ID, NAME, DESCRIPTION, COST, AbstractCard.CardType.ATTACK,
				AbstractCard.CardColor.RED, AbstractCard.CardRarity.UNCOMMON,
				AbstractCard.CardTarget.ENEMY);
		this.damage = this.baseDamage = 0;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		this.baseDamage = p.maxHealth - p.currentHealth;
		this.calculateCardDamage(m);
		AbstractDungeon.actionManager
				.addToBottom(new DamageAction((AbstractCreature) m,
						new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL),
						AbstractGameAction.AttackEffect.BLUNT_HEAVY));
	}

	@Override
	public void applyPowers() {
		this.baseDamage = AbstractDungeon.player.maxHealth
				- AbstractDungeon.player.currentHealth;
		super.applyPowers();
	}

	@Override
	public void doUpgrade() {
		this.upgradeBaseCost(UPGRADED_COST);
	}

}
