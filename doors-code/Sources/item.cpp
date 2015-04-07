#include "item.h"
#include <string>
#include <cstdlib>
#include <cmath>
#include "player.h"

using namespace std;

Item::Item(Player* player, int weight)
{   
    this->player = player;

    // sets the rarity of the item generated
    setRarity(weight);

    // set name of item based on rarity
    setName(weight);

    // sets the prefix based on rarity
    setPrefix();

    // sets the stats of the item
    setStats();

    // sets the item level of this object
    setItemLevel();
}

Item::Item(string name) {
    if(name == "Runetitan, Sword of the One") {
        this->name = name;
        rarity = "Legendary";
        type = "Sword";
    }
}

Item::~Item()
{

}

int Item::getStrMain() {
    if(name == "Runetitan, Sword of the One")
        return player->getLevel() + 10;
    return strMain;
}

int Item::getIntMain() {
    if(name == "Runetitan, Sword of the One")
        return player->getLevel() + 10;
    return intMain;
}

int Item::getDexMain() {
    if(name == "Runetitan, Sword of the One")
        return player->getLevel() + 10;
    return dexMain;
}

int Item::getApOff() {
    if(name == "Runetitan, Sword of the One")
        return player->getLevel() + 5;
    return apOff;
}

int Item::getSpOff() {
    if(name == "Runetitan, Sword of the One")
        return player->getLevel() + 5;
    return spOff;
}

int Item::getCritOff() {
    if(name == "Runetitan, Sword of the One")
        return player->getLevel() + 5;
    return critOff;
}

int Item::getHealing() {
    return healing;
}

int Item::getSellPrice() {
    return sellPrice;
}

QPixmap Item::getPixmap() {
    return item;
}

void Item::setRarity(int weight) {
    int rare = rand() % 101;
    if (rare >= 60 + weight)
        rarity = "Common";
    else if (rare >= 30 + weight)
        rarity = "Uncommon";
    else if (rare >= 10 + weight)
        rarity = "Rare";
    else if (rare >= 1 + weight)
        rarity = "Epic";
    else
        rarity = "Legendary";
}

void Item::setPrefix() {
    if (rarity == "Common")
        prefix = string();
    if (rarity == "Uncommon")
        prefix = "Improved";
    if (rarity == "Rare")
        prefix = "Refined";
    if (rarity == "Epic")
        prefix = "Perfect";
    if (rarity == "Legendary")
        prefix = string();
}

void Item::setName(int weight) {
    if(rarity != "Legendary")
        switch ((rand() % 5) + 1) {
        case 1:
            type = "Helm";
            name = "Greathelm";
            break;
        case 2:
            type = "Chest";
            name = "Chestplate";
            break;
        case 3:
            type = "Staff";
            name = "Battlestaff";
            break;
        case 4:
            type = "Sword";
            name = "Greatsword";
            break;
        case 5:
            type = "Potion";
            name = "Potion";
            break;
        }
        else {
            type = "Quest Item";
            switch (rand() % 3) {
            case 0:
                name = "Rune of Power";
                for (Item leg1 : player->getInventory()) {
                    if (leg1.getName() == "Rune of Power") {
                        if (rand() % 2 == 1) {
                            name = "Rune of Courage";
                            for (Item leg2 : player->getInventory())
                                if (leg2.getName() == "Rune of Courage") {
                                    name = "Rune of Wisdom";
                                    for (Item leg3 : player->getInventory())
                                        if (leg3.getName() == "Rune of Wisdom") {
                                            setRarity(weight);
                                            setName(weight);
                                        }
                                }
                        } else {
                            name = "Rune of Wisdom";
                            for (Item leg2 : player->getInventory())
                                if (leg2.getName() == "Rune of Wisdom") {
                                    name = "Rune of Courage";
                                    for (Item leg3 : player->getInventory())
                                        if (leg3.getName() == "Rune of Courage") {
                                            setRarity(weight);
                                            setName(weight);
                                        }
                                }
                        }
                    }
                }
                break;
            case 1:
                name = "Rune of Courage";
                for (Item leg1 : player->getInventory()) {
                    if (leg1.getName() == "Rune of Courage") {
                        if (rand() % 2 == 1) {
                            name = "Rune of Wisdom";
                            for (Item leg2 : player->getInventory())
                                if (leg2.getName() == "Rune of Wisdom") {
                                    name = "Rune of Power";
                                    for (Item leg3 : player->getInventory())
                                        if (leg3.getName() == "Rune of Power") {
                                            setRarity(weight);
                                            setName(weight);
                                        }
                                }
                        } else {
                            name = "Rune of Power";
                            for (Item leg2 : player->getInventory())
                                if (leg2.getName() == "Rune of Power") {
                                    name = "Rune of Wisdom";
                                        for (Item leg3 : player->getInventory())
                                            if (leg3.getName() == "Rune of Wisdom") {
                                                setRarity(weight);
                                                setName(weight);
                                        }
                                }
                        }
                    }
                }
                break;
            case 2:
                name = "Rune of Wisdom";
                for (Item leg1 : player->getInventory()) {
                    if (leg1.getName() == "Rune of Wisdom") {
                        if (rand() % 2 == 1) {
                            name = "Rune of Courage";
                            for (Item leg2 : player->getInventory())
                                if (leg2.getName() == "Rune of Courage") {
                                    name = "Rune of Power";
                                    for (Item leg3 : player->getInventory())
                                        if (leg3.getName() == "Rune of Power") {
                                            setRarity(weight);
                                            setName(weight);
                                        }
                                }
                        } else {
                            name = "Rune of Power";
                            for (Item leg2 : player->getInventory())
                                if (leg2.getName() == "Rune of Power") {
                                    name = "Rune of Courage";
                                    for (Item leg3 : player->getInventory())
                                        if (leg3.getName() == "Rune of Courage") {
                                            setRarity(weight);
                                            setName(weight);
                                        }
                                }
                        }
                    }
                }
            }
    }
}

void Item::setStats() {
    // decides the main stat
    // 1 = strength
    // 2 = intelligence
    // 3 = dexterity
    int ms1 = (rand() % 3) + 1;
    int ms2 = (rand() % 3) + 1;

    // decides the main stat
    // 1 = attack power
    // 2 = spell power
    // 3 = critical hit chance
    int os1 = (rand() % 3) + 1;
    int os2 = (rand() % 3) + 1;

    if(rarity == "Common") {
        if(type == "Helm" || type == "Chest") {
            if (ms1 == 1) {
                strMain += player->getLevel() + 1;
                suffix = " of Strength";
            } else if (ms1 == 2) {
                intMain += player->getLevel() + 1;
                suffix = " of Intellect";
            } else if (ms1 == 3) {
                dexMain += player->getLevel() + 1;
                suffix = " of Dexterity";
            }
        }
        else if(type == "Staff") {
            if (ms1 == 1) {
                intMain += player->getLevel() + 1;
                suffix = " of Intellect";
            } else if (ms1 == 2) {
                intMain += player->getLevel() + 1;
                suffix = " of Intellect";
            } else if (ms1 == 3) {
                dexMain += player->getLevel() + 1;
                suffix = " of Dexterity";
            }
        }
        else if(type == "Sword") {
            if (ms1 == 1) {
                strMain += player->getLevel() + 1;
                suffix = " of Strength";
            } else if (ms1 == 2) {
                strMain += player->getLevel() + 1;
                suffix = " of Strength";
            } else if (ms1 == 3) {
                dexMain += player->getLevel() + 1;
                suffix = " of Dexterity";
            }
        }
        else if (type == "Potion") {
            healing = (int) (((double) player->getLevel() / (double) player
                    ->getMAX_HP()) * pow(10,
                    ((int)log10(player->getMAX_HP()) + 1)));
        }
    }
    else if(rarity == "Uncommon") {
        if(type == "Helm" || type == "Chest") {
            if (os1 == 1) {
                apOff += player->getLevel();
                suffix = " of Vicious ";
            } else if (os1 == 2) {
                spOff += player->getLevel();
                suffix = " of Magical ";
            } else if (os1 == 3) {
                critOff += 1;
                suffix = " of Accurate ";
            }
            if (ms1 == 1) {
                strMain += player->getLevel() + 2;
                suffix += "Strength";
            } else if (ms1 == 2) {
                intMain += player->getLevel() + 2;
                suffix += "Intellect";
            } else if (ms1 == 3) {
                dexMain += player->getLevel() + 2;
                suffix += "Dexterity";
            }
        }
        else if(type == "Staff") {
            if (os1 == 1) {
                spOff += player->getLevel();
                suffix = " of Magical ";
            } else if (os1 == 2) {
                spOff += player->getLevel();
                suffix = " of Magical ";
            } else if (os1 == 3) {
                critOff += 2;
                suffix = " of Accurate ";
            }
            if (ms1 == 1) {
                intMain += player->getLevel() + 2;
                suffix += "Intellect";
            } else if (ms1 == 2) {
                intMain += player->getLevel() + 2;
                suffix += "Intellect";
            } else if (ms1 == 3) {
                dexMain += player->getLevel() + 2;
                suffix += "Dexterity";
            }
        }
        else if(type == "Sword") {
            if (os1 == 1) {
                apOff += player->getLevel();
                suffix = " of Vicious ";
            } else if (os1 == 2) {
                apOff += player->getLevel();
                suffix = " of Vicious ";
            } else if (os1 == 3) {
                critOff += 2;
                suffix = " of Accurate ";
            }
            if (ms1 == 1) {
                strMain += player->getLevel() + 2;
                suffix += "Strength";
            } else if (ms1 == 2) {
                strMain += player->getLevel() + 2;
                suffix += "Strength";
            } else if (ms1 == 3) {
                dexMain += player->getLevel() + 2;
                suffix += "Dexterity";
            }
        }
        else if(type == "Potion") {
            healing = (int) (((double)(player->getLevel() + 1) / (double) player
                    ->getMAX_HP()) * pow(10,
                    ((int)log10(player->getMAX_HP()) + 1)));;
        }
    }
    else if(rarity == "Rare") {
        if(type == "Helm" || type == "Chest") {
            if (os1 == 1) {
                apOff += player->getLevel() + 1;
                suffix = " of Vicious ";
            } else if (os1 == 2) {
                spOff += player->getLevel() + 1;
                suffix = " of Magical ";
            } else if (os1 == 3) {
                critOff += 2;
                suffix = " of Accurate ";
            }
            if (ms1 == 1) {
                strMain += player->getLevel() + 3;
                if (ms2 == 1) {
                    suffix += "Power";
                    strMain -= player->getLevel();
                } else {
                    suffix += "Strength";
                }
            } else if (ms1 == 2) {
                intMain += player->getLevel() + 3;
                if (ms2 == 2) {
                    suffix += "Wisdom";
                    intMain -= player->getLevel();
                } else {
                    suffix += "Intellect";
                }
            } else if (ms1 == 3) {
                dexMain += player->getLevel() + 3;
                if (ms2 == 3) {
                    dexMain -= player->getLevel();
                    suffix += "Courage";
                } else {
                    suffix += "Dexterity";
                }
            }
            if (ms2 == 1)
                strMain += player->getLevel() + 2;
            else if (ms2 == 2)
                intMain += player->getLevel() + 2;
            else if (ms2 == 3)
                dexMain += player->getLevel() + 2;
        }
        else if(type == "Staff") {
            if (os1 == 1) {
                spOff += player->getLevel() + 1;
                suffix = " of Magical ";
            } else if (os1 == 2) {
                spOff += player->getLevel() + 1;
                suffix = " of Magical ";
            } else if (os1 == 3) {
                critOff += 2;
                suffix = " of Accurate ";
            }
            if (ms1 == 1) {
                intMain += player->getLevel() + 3;
                if (ms2 == 1) {
                    suffix += "Wisdom";
                    intMain -= player->getLevel();
                } else {
                    suffix += "Intellect";
                }
            } else if (ms1 == 2) {
                intMain += player->getLevel() + 3;
                if (ms2 == 2) {
                    suffix += "Wisdom";
                    intMain -= player->getLevel();
                } else {
                    suffix += "Intellect";
                }
            } else if (ms1 == 3) {
                dexMain += player->getLevel() + 3;
                if (ms2 == 3) {
                    dexMain -= player->getLevel();
                    suffix += "Courage";
                } else {
                    suffix += "Dexterity";
                }
            }
            if (ms2 == 1)
                strMain += player->getLevel() + 2;
            else if (ms2 == 2) {
                intMain += player->getLevel() + 2;
            } else if (ms2 == 3) {
                dexMain += player->getLevel() + 2;
            }
        }
        else if(type == "Sword") {
            if (os1 == 1) {
                apOff += player->getLevel() + 1;
                suffix = " of Vicious ";
            } else if (os1 == 2) {
                apOff += player->getLevel() + 1;
                suffix = " of Vicious ";
            } else if (os1 == 3) {
                critOff += 2;
                suffix = " of Accurate ";
            }
            if (ms1 == 1) {
                strMain += player->getLevel() + 3;
                if (ms2 == 1) {
                    suffix += "Power";
                    strMain -= player->getLevel();
                } else {
                    suffix += "Strength";
                }
            } else if (ms1 == 2) {
                strMain += player->getLevel() + 3;
                if (ms2 == 1) {
                    suffix += "Power";
                    strMain -= player->getLevel();
                } else {
                    suffix += "Strength";
                }
            } else if (ms1 == 3) {
                dexMain += player->getLevel() + 3;
                if (ms2 == 3) {
                    suffix += "Courage";
                    dexMain -= player->getLevel();
                } else {
                    suffix += "Dexterity";
                }
            }
            if (ms2 == 1) {
                strMain += player->getLevel() + 2;
            } else if (ms2 == 2)
                intMain += player->getLevel() + 2;
            else if (ms2 == 3)
                dexMain += player->getLevel() + 2;
        }
        else if(type == "Potion") {
            healing = (int) (((double) (player->getLevel() + 2) / (double) player
                    ->getMAX_HP()) * pow(10,
                    ((int)log10(player->getMAX_HP()) + 1)));
        }
    }
    else if(rarity == "Epic") {
        if(type == "Helm" || type == "Chest") {
            if (os1 == 1) {
                apOff += player->getLevel() + 3;
                if (os2 == 1) {
                    suffix = " of Brutal ";
                    apOff -= player->getLevel();
                } else {
                    suffix = " of Vicious ";
                }
            } else if (os1 == 2) {
                spOff += player->getLevel() + 3;
                if (os2 == 2) {
                    suffix = " of Mystical ";
                    spOff -= player->getLevel();
                } else {
                    suffix = " of Magical ";
                }
            } else if (os1 == 3) {
                critOff += 5;
                if (os2 == 3) {
                    suffix = " of True ";
                    critOff -= player->getLevel();
                } else {
                    suffix = " of Accurate ";
                }
            }
            if (os2 == 1) {
                apOff += player->getLevel() + 1;
            } else if (os2 == 2) {
                spOff += player->getLevel() + 1;
            } else if (os2 == 3) {
                critOff += 2;
            }
            if (ms1 == 1) {
                strMain += player->getLevel() + 6;
                if (ms2 == 1) {
                    suffix += "Power";
                    strMain -= player->getLevel();
                } else {
                    suffix += "Strength";
                }
            } else if (ms1 == 2) {
                intMain += player->getLevel() + 6;
                if (ms2 == 2) {
                    suffix += "Wisdom";
                    intMain -= player->getLevel();
                } else {
                    suffix += "Intellect";
                }
            } else if (ms1 == 3) {
                dexMain += player->getLevel() + 6;
                if (ms2 == 1) {
                    suffix += "Courage";
                    dexMain -= player->getLevel();
                } else {
                    suffix += "Dexterity";
                }
            }
            if (ms2 == 1) {
                strMain += player->getLevel() + 4;
            } else if (ms2 == 2) {
                intMain += player->getLevel() + 4;
            } else if (ms2 == 3) {
                dexMain += player->getLevel() + 4;
            }
        }
        else if(type == "Staff") {
            if (os1 == 1) {
                spOff += player->getLevel() + 3;
                if (os2 == 1) {
                    suffix = " of Mystical ";
                    spOff -= player->getLevel();
                } else {
                    suffix = " of Magical ";
                }
            } else if (os1 == 2) {
                spOff += player->getLevel() + 3;
                if (os2 == 2) {
                    suffix = " of Mystical ";
                    spOff -= player->getLevel();
                } else {
                    suffix = " of Magical ";
                }
            } else if (os1 == 3) {
                critOff += 5;
                if (os2 == 3) {
                    suffix = " of True ";
                    critOff -= player->getLevel();
                } else {
                    suffix = " of Accurate ";
                }
            }
            if (os2 == 1) {
                spOff += player->getLevel() + 1;
            } else if (os2 == 2) {
                spOff += player->getLevel() + 1;
            } else if (os2 == 3) {
                critOff += 2;
            }
            if (ms1 == 1) {
                intMain += player->getLevel() + 6;
                if (ms2 == 1) {
                    suffix += "Wisdom";
                    strMain -= player->getLevel();
                } else {
                    suffix += "Intellect";
                }
            } else if (ms1 == 2) {
                intMain += player->getLevel() + 6;
                if (ms2 == 2) {
                    suffix += "Wisdom";
                    strMain -= player->getLevel();
                } else {
                    suffix += "Intellect";
                }
            } else if (ms1 == 3) {
                dexMain = player->getLevel() + 6;
                if (ms2 == 3) {
                    suffix += "Courage";
                    strMain -= player->getLevel();
                } else {
                    suffix += "Dexterity";
                }
            }
            if (ms2 == 1) {
                intMain += player->getLevel() + 3;
            } else if (ms2 == 2) {
                intMain += player->getLevel() + 3;
            } else if (ms2 == 3) {
                dexMain += player->getLevel() + 3;
            }
        }
        else if(type == "Sword") {
            if (os1 == 1) {
                apOff += player->getLevel() + 3;
                if (os2 == 1) {
                    suffix = " of Brutal ";
                    apOff -= player->getLevel();
                } else {
                    suffix = " of Vicious ";
                }
            } else if (os1 == 2) {
                apOff += player->getLevel() + 3;
                if (os2 == 1) {
                    suffix = " of Brutal ";
                    apOff -= player->getLevel();
                } else {
                    suffix = " of Vicious ";
                }
            } else if (os1 == 3) {
                critOff = 5;
                if (os2 == 3) {
                    suffix = " of True ";
                } else {
                    suffix = " of Accurate ";
                }
            }
            if (os2 == 1) {
                apOff += player->getLevel() + 1;
            } else if (os2 == 2) {
                apOff += player->getLevel() + 1;
            } else if (os2 == 3) {
                critOff += 2;
            }
            if (ms1 == 1) {
                strMain += player->getLevel() + 6;
                if (ms2 == 1) {
                    suffix += "Power";
                    strMain -= player->getLevel();
                } else {
                    suffix += "Strength";
                }
            } else if (ms1 == 2) {
                strMain += player->getLevel() + 6;
                if (ms2 == 1) {
                    suffix += "Power";
                    strMain -= player->getLevel();
                } else {
                    suffix += "Strength";
                }
            } else if (ms1 == 3) {
                dexMain += player->getLevel() + 6;
                if (ms2 == 3) {
                    suffix += "Courage";
                    strMain -= player->getLevel();
                }
                else {
                    suffix += "Dexterity";
                }
            }
            if (ms2 == 1) {
                strMain += player->getLevel() + 3;
            }
            else if (ms2 == 2) {
                strMain += player->getLevel() + 3;
            } else if (ms2 == 3) {
                dexMain += player->getLevel() + 3;
            }
        }
        else if(type == "Potion") {
            healing = (int) (((double) (player->getLevel() + 5) / (double) player
                    ->getMAX_HP()) * pow(10,
                    ((int)log10(player->getMAX_HP()) + 1)));
        }
    }
    if(rarity == "Legendary") {
        // TODO add legendary potions
    }
}

void Item::setItemLevel() {
    int ilvl = 0;
    if (rarity == "Legendary")
        ilvl += 10;
    if (rarity == "Epic")
        ilvl += 5;
    if (rarity == "Rare")
        ilvl += 3;
    if (rarity == "Uncommon")
        ilvl += 2;
    itemLevel = (player->getLevel() * 10) + ilvl;
}

int Item::getItemLevel() {
    if(name == "Runetitan, Sword of the One")
        return (player->getLevel() * 15) + 10;
    return itemLevel;
}

string Item::getName() {
    string toString = "";
    if (prefix != string())
        toString += prefix + " ";
    toString += name;
    if (suffix != string())
        toString += suffix;
    return toString;
}

bool Item::equals(Item obj) {
    Item item = obj;
    if (getName() == item.getName())
        if (getStrMain() == item.getStrMain())
            if (getIntMain() == item.getIntMain())
                if (getDexMain() == item.getDexMain())
                    if (getApOff() == item.getApOff())
                        if (getSpOff() == item.getSpOff())
                            if (getCritOff() == item.getCritOff())
                                if (getHealing() == item.getHealing())
                                    if (getItemLevel() == item.getItemLevel())
                                        return true;
    return false;
}

string Item::toString() {
    string toString = "";
    toString += getName();
    toString += "\n";
    toString += rarity + " " + type + "\n";
    if (strMain > 0) {
        toString += "+";
        toString += getStrMain();
        toString += " Strength\n";
    }
    if (intMain > 0) {
        toString += "+";
        toString += getIntMain();
        toString += " Intelligence\n";
    }
    if (dexMain > 0) {
        toString += "+";
        toString += getDexMain();
        toString += " Dexterity\n";
    }
    if (apOff > 0) {
        toString += "+";
        toString += getApOff();
        toString += " Attack Power\n";
    }
    if (spOff > 0) {
        toString += "+";
        toString += getSpOff();
        toString += " Spell Power\n";
    }
    if (critOff > 0) {
        toString += "+";
        toString += getCritOff();
        toString += "% Crit Chance\n";
    }
    if(healing > 0) {
        toString += "Heals you for ";
        toString += healing;
        toString += " HP\n";
    }
    toString += "Item Level ";
    toString += itemLevel;
    return toString;
}
