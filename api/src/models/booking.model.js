const { DataTypes } = require('sequelize');

module.exports = model;

function model(sequelize) {
    const attributes = {
        id: {
            field: 'id',
            type: DataTypes.INTEGER,
            primaryKey: true,
            autoIncrement: true,
            allowNull: false,
            unique: true,
        },
        tourId: {
            field: 'tour_id',
            foreignKey: true,
            type: DataTypes.INTEGER,
            allowNull: false,
        },
        accountId: {
            field: 'account_id',
            foreignKey: true,
            type: DataTypes.INTEGER,
            allowNull: false,
        },
        paymentId: {
            field: 'payment_id',
            foreignKey: true,
            type: DataTypes.INTEGER,
            allowNull: false,
        },
        tourAdultCost: {
            field: 'tour_adult_cost',
            type: DataTypes.DOUBLE,
        },
        tourChildrenCost: {
            field: 'tour_children_cost',
            type: DataTypes.DOUBLE,
        },
        quantityAdult: {
            field: 'quantity_adult',
            foreignKey: true,
            type: DataTypes.INTEGER,
            allowNull: false,
        },
        quantityChildren: {
            field: 'quantity_children',
            foreignKey: true,
            type: DataTypes.INTEGER,
            allowNull: false,
        },
        totalCost: {
            field: 'total_cost',
            foreignKey: true,
            type: DataTypes.DOUBLE,
            allowNull: false,
        },
        createdAt: {
            field: 'created_at',
            type: DataTypes.DATE,
        },
        updatedAt: {
            field: 'updated_at',
            type: DataTypes.DATE,
        },
        // 0: in-progress | 1: done | -1 : cancle
        status: {
            field: 'status',
            type: DataTypes.INTEGER,
        }
    };

    const options = {
        freezeTableName: true,
    };

    return sequelize.define('booking', attributes, options);
}
