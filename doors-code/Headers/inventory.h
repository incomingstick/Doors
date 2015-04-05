#ifndef INVENTORY_H
#define INVENTORY_H

#include <QDialog>

namespace Ui {
class Inventory;
}

class Inventory : public QDialog
{
    Q_OBJECT

public:
    explicit Inventory(QWidget *parent = 0);
    ~Inventory();

private slots:
    void on_pushButton_clicked();

private:
    Ui::Inventory *ui;
};

#endif // INVENTORY_H
