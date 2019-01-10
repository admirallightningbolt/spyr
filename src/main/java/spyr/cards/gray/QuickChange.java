package spyr.cards.gray;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import spyr.cards.SpyrCard;
import spyr.patches.CardEnum;
import spyr.powers.DarkEcoPower;
import spyr.powers.LightEcoPower;

/**
 * Switches between dark & light forms AND draw. Woooho.
 */
public class QuickChange extends SpyrCard {

	public static final String ID = "spyr:quick_change";

	private static final int COST = 1;
	private static final int UPGRADED_COST = 0;
  private static final int CARD_DRAW = 1;

	public QuickChange() {
		super(ID, COST, AbstractCard.CardType.SKILL, CardEnum.FRACTURED_GRAY,
				AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF);
    this.magicNumber = this.baseMagicNumber = CARD_DRAW;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
    AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, this.magicNumber));
		if (p.hasPower(LightEcoPower.POWER_ID)
				&& p.hasPower(DarkEcoPower.POWER_ID)) {
			return;
		}
		if (p.hasPower(LightEcoPower.POWER_ID)) {
			AbstractDungeon.actionManager
					.addToBottom(new ReducePowerAction(p, p, LightEcoPower.POWER_ID, 1));
			AbstractDungeon.actionManager
					.addToBottom(new ApplyPowerAction(p, p, new DarkEcoPower(p, 1), 1));
		} else if (p.hasPower(DarkEcoPower.POWER_ID)) {
			AbstractDungeon.actionManager
					.addToBottom(new ReducePowerAction(p, p, DarkEcoPower.POWER_ID, 1));
			AbstractDungeon.actionManager
					.addToBottom(new ApplyPowerAction(p, p, new LightEcoPower(p, 1), 1));
		}
	}

	@Override
	public void doUpgrade() {
		this.upgradeBaseCost(UPGRADED_COST);
	}

}