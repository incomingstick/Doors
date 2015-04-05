#include "inventory.h"
#include "ui_inventory.h"
#include "qdesktopwidget.h"

Inventory::Inventory(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::Inventory)
{
    ui->setupUi(this);

    this->move(QApplication::desktop()->availableGeometry().center() - this->rect().center());
}

Inventory::~Inventory()
{
    delete ui;
}

void Inventory::on_pushButton_clicked()
{
    this->close();
}
