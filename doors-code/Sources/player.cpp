#include <player.h>
#include <vector>
#include <string>
#include <statedit.h>
#include <enemy.h>
#include <qstring.h>

using namespace std;

Player::Player()
{
    AttackPower = 0;
    SpellPower = 0;
    CURRENT_EXP = 0;
    HP = 0;
    MAX_HP = 0;
    Level = 0;
    this->name = "";
    crit = 10;
    pointsToSpend = 7;
    setStr(1);
    setInt(1);
    setDex(1);
    setLevel(0);
    MAX_EXP = 0;
    setCURRENT_EXP(0);
}

Player::~Player()
{

}

bool Player::checkLevelUp() {
    if (getCURRENT_EXP() >= getMAX_EXP() && getLevel() <= 60)
        return true;
    else return false;
}

void Player::LevelUp() {
    setLevel(getLevel() + 1);
    if (getLevel() > 0 && getLevel() < 60) {
            if (getLevel() % 10 == 0 && getLevel() < 60) {
                pointsToSpend += 10;
                setStr(getStr() + 1);
                setInt(getInt() + 1);
                setDex(getDex() + 1);
            }
            else if (getLevel() != 1 || getLevel() == 60)
                pointsToSpend += 5;
            else {
                pointsToSpend += 7;
            }
            if (getLevel() == 1)
                MAX_EXP = 50;
            else
                MAX_EXP += (int)(((getMAX_EXP() * ((double)getLevel() / 5))
                        + (getMAX_EXP() * (2 / (double)getLevel()))
                        + (getMAX_EXP() / getLevel()) + getMAX_EXP())/ (double)getLevel());
            getMAX_HP();
            setHP(MAX_HP);
            setCURRENT_EXP(0);
    }
}

void Player::setStr(int strength) {
    this->Strength = strength;
}

int Player::getStr() const {
    int mod = 0;
    //for(Item i: inventory)
    //    mod += item->getStrMain();
    return Strength + mod;
}

void Player::setInt(int intelligence) {
    this->Intelligence = intelligence;
}

int Player::getInt() const {
    int mod = 0;
    //for(Item i: inventory)
    //    mod += item->getIntMain();
    return Intelligence + mod;
}

void Player::setDex(int dexterity) {
    this->Dexterity = dexterity;
}

int Player::getDex() const {
    int mod = 0;
    //for(Item i: inventory)
    //    mod += item->getDexMain();
    return Dexterity + mod;
}

int Player::getAttackPower() const {
    int mod = 0;
    //for (Item item : equip)
    //	mod += item.getApOff();
    return AttackPower + mod;
}

void Player::setAttackPower(int attackPower) {
    AttackPower = attackPower;
}

int Player::getSpellPower() const {
    int mod = 0;
    //for (Item item : equip)
    //	mod += item.getSpOff();
    return SpellPower + mod;
}

void Player::setSpellPower(int spellPower) {
    SpellPower = spellPower;
}

double Player::getCrit() const {
    int mod = 0;
    //for (Item item : equip)
    //	mod += item.getCritOff();
    return crit + mod;
}

void Player::setCrit(double crit) {
    this->crit = crit;
}

int Player::getHP() const {
    return HP;
}

void Player::setHP(int hp) {
    if (hp <= 0) {
        // TODO do game over
    } else {
        if (hp > getMAX_HP()) {
            int overheal = hp - getMAX_HP();
            hp = getMAX_HP();
            // TODO Finish overheal state
        }
        HP = hp;
    }
}

int Player::getMAX_HP() {
    return MAX_HP = (10 * getLevel()) + (getDex() * 3) + (getInt() * 2) + getStr();
}

int Player::getCURRENT_EXP() const {
    return CURRENT_EXP;
}

void Player::setCURRENT_EXP(int CURRENT_EXP) {
    this->CURRENT_EXP = CURRENT_EXP;
    checkLevelUp();
}

int Player::getMAX_EXP() const {
    return MAX_EXP;
}

int Player::getLevel() const {
    return Level;
}

void Player::setLevel(int level) {
    if(level > 60) {
        level = 60;
    }
    Level = level;
}

QList<Item> Player::getInventory() const {
    return inventory;
}

int Player::minDamage() {
    return getLevel() * 2;
}

int Player::maxDamage() {
    return getLevel() * 5;
}

QList<Item> Player::getEquip() const {
    return equip;
}

void Player::equipItem(Item item) {
    // TODO Add eqiup item code here
}

Enemy Player::meleeAttack(Enemy actor, bool block) {
    int damage = (abs(((rand() % (maxDamage() + getAttackPower())) - (minDamage() + getAttackPower())) * getStr()) / (getLevel() + 1)) +  minDamage() + getAttackPower();
    if (!actor.block(this, block)) {
        if (rand() % 100 < getCrit()) {
            damage *= 2.5;
            // TODO Print critical hit here
        }
        actor.setHP(actor.getHP() - damage);
        // TODO Print damage here
    } else {
        // TODO Print dodge here
    }
    return actor;
}

Enemy Player::magicAttack(Enemy actor, bool dodge) {
    int damage = (abs(((rand() % (maxDamage() + getSpellPower())) - (minDamage() + getSpellPower())) * getInt()) / (getLevel() + 1)) +  minDamage() + getSpellPower();
    if (!actor.dodge(this, dodge)) {
        if (rand() % 100 < getCrit()) {
            damage *= 2.5;
            // TODO Print critical hit here
        }
        actor.setHP(actor.getHP() - damage);
        // TODO Print damage here
    } else {
        // TODO Print dodge here
    }
    return actor;
}

bool Player::block(bool& ability) {
    int blockChance = 70 - (getDex() / 5);
    if (blockChance < 50)
        blockChance = 50;
    if (ability || rand() % 100 > blockChance) {
        return true;
    }
    return false;
}

bool Player::dodge(bool& ability) {
    int dodgeChance = 70 - (getDex() / 5);
    if (dodgeChance < 50)
        dodgeChance = 50;
    if (ability || rand() % 100 > dodgeChance) {
        return true;
    }
    return false;
}

int Player::getPointsToSpend() const {
    return pointsToSpend;
}

void Player::setPointsToSpend(int pointsToSpend) {
    this->pointsToSpend = pointsToSpend;
}

QString Player::getName() const {
    return name;
}

void Player::setName(QString name) {
    this->name = name;
}
