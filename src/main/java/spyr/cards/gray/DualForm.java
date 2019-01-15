package spyr.cards.gray;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import spyr.cards.SpyrCard;
import spyr.patches.CardEnum;
import spyr.powers.DarkEcoPower;
import spyr.powers.LightEcoPower;

/**
 * Adds both DarkEco & LightEco. This will trigger both effects on cards that
 * gain bonuses from being in one form.
 */
public class DualForm extends SpyrCard {

	public static final String ID = "spyr:dual_form";

	private static final int COST = 3;

	public DualForm() {
		super(ID, COST, AbstractCard.CardType.POWER, CardEnum.FRACTURED_GRAY,
				AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF);
		this.isEthereal = true;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		if (!p.hasPower(DarkEcoPower.POWER_ID)) {
			AbstractDungeon.actionManager
					.addToBottom(new ApplyPowerAction(p, p, new DarkEcoPower(p, 1), 1));
		}
		if (!p.hasPower(LightEcoPower.POWER_ID)) {
			AbstractDungeon.actionManager
					.addToBottom(new ApplyPowerAction(p, p, new LightEcoPower(p, 1), 1));
		}
	}

	@Override
	public void doUpgrade() {
		this.isEthereal = false;
		this.rawDescription = this.cardStrings.UPGRADE_DESCRIPTION;
		this.initializeDescription();
	}

}
