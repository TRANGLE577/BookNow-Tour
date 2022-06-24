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
        locationId: {
            field: 'location_id',
            foreignKey: true,
            type: DataTypes.INTEGER,
            allowNull: false,
        },
        name: {
            field: 'name',
            type: DataTypes.STRING,
            allowNull: false,
        },
        description: {
            field: 'description',
            type: DataTypes.STRING,
        },
        image: {
            field: 'image',
            type: DataTypes.STRING,
            allowNull: true,
        },
        maximum: {
            field: 'maximum',
            type: DataTypes.INTEGER,
        },
        tourDateDepart: {
            field: 'tour_date_depart',
            type: DataTypes.DATE,
        },
        tourDateReturn: {
            field: 'tour_date_return',
            type: DataTypes.DATE,
        },
        tourAdultCost: {
            field: 'tour_adult_cost',
            type: DataTypes.DOUBLE,
        },
        tourChildrenCost: {
            field: 'tour_children_cost',
            type: DataTypes.DOUBLE,
        },
        createdAt: {
            field: 'created_at',
            type: DataTypes.DATE,
        },
        updatedAt: {
            field: 'updated_at',
            type: DataTypes.DATE,
        },
        status: {
            field: 'status',
            type: DataTypes.BOOLEAN,
        },
        isFinish: {
            field: 'is_finish',
            type: DataTypes.BOOLEAN,
        }
    };

    const options = {
        freezeTableName: true,
    };

    return sequelize.define('tour', attributes, options);
}
