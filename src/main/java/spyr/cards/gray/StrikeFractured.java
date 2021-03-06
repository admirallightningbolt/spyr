package spyr.cards.gray;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.helpers.BaseModCardTags;
import spyr.cards.SpyrCard;
import spyr.patches.CardEnum;

/**
 * Basic strike card.
 */
public class StrikeFractured extends SpyrCard {

	public static final String ID = "spyr:strike_fractured";
	public static final String NAME = "Strike";
	public static final String DESCRIPTION = "Deal !D! damage.";

	private static final int COST = 1;
	private static final int POWER = 6;
	private static final int UPGRADE_BONUS = 3;

	public StrikeFractured() {
		super(ID, NAME, DESCRIPTION, COST, AbstractCard.CardType.ATTACK,
				CardEnum.FRACTURED_GRAY, AbstractCard.CardRarity.BASIC,
				AbstractCard.CardTarget.ENEMY);
		this.baseDamage = POWER;
		this.tags.add(BaseModCardTags.BASIC_STRIKE);
		this.tags.add(CardTags.STRIKE);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(
				new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn),
						AbstractGameAction.AttackEffect.SLASH_DIAGONAL));

	}

	@Override
	public void doUpgrade() {
		upgradeDamage(UPGRADE_BONUS);
	}

}
