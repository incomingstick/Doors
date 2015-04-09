#include "inventory.h"
#include "ui_inventory.h"
#include "qdesktopwidget.h"


Inventory::Inventory(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::Inventory)
{
    ui->setupUi(this);

    this->move(QApplication::desktop()->availableGeometry().center() - this->rect().center());
    this->setWindowTitle("Inventory");
    for(int i = 0; i < 5; i++) {
        for(int j = 0; j < 5; j++) {
            inv << new QLabel(this);
            inv.at(j+i*5)->setGeometry(350 - (j * 40), 50 + (i * 40), 30, 30);
            inv.at(j+i*5)->setScaledContents(true);
            inv.at(j+i*5)->setStyleSheet("border: 1px solid black");
        }
    }
    ui->head->setStyleSheet("border: 1px solid black");
    ui->armor->setStyleSheet("border: 1px solid black");
    ui->weapon->setStyleSheet("border: 1px solid black");
}

Inventory::~Inventory()
{
    delete ui;
}

void Inventory::on_pushButton_clicked()
{
    this->close();
}

void Inventory::setPlayer(Player player) {
    this->player = player;
    for(int i = 0; i < 5; i++) {
        for(int j = 0; j < 5; j++) {
            inv.at(j+i*5)->setPixmap(player.getInventory().at(j+i*5).getPixmap());
            inv.at(j+i*5)->setToolTip(player.getInventory().at(j+i*5).toString());
        }
    }
}
