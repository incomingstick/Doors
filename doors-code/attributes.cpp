#include "attributes.h"
#include "ui_attributes.h"

Attributes::Attributes(QWidget *parent) :
    QWidget(parent),
    ui(new Ui::Attributes)
{
    ui->setupUi(this);
}

Attributes::~Attributes()
{
    delete ui;
}

void Attributes::setPlayer(Player player) {
    this->player = player;
    ui->nameLabel->setText(this->player.getName());
}
