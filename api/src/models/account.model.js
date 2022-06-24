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
        roleId: {
            field: 'role_id',
            foreignKey: true,
            type: DataTypes.INTEGER,
            allowNull: false,
        },
        fullname: {
            field: 'fullname',
            type: DataTypes.STRING,
            allowNull: true,
        },
        image: {
            field: 'image',
            type: DataTypes.STRING,
            allowNull: true,
        },
        email: {
            field: 'email',
            type: DataTypes.STRING,
            allowNull: false,
        },
        username: {
            field: 'username',
            type: DataTypes.STRING,
            allowNull: false,
        },
        password: {
            field: 'password',
            type: DataTypes.STRING,
            allowNull: false,
        },
        phone: {
            field: 'phone',
            type: DataTypes.STRING,
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
    };

    const options = {
        freezeTableName: true,
    };

    return sequelize.define('account', attributes, options);
}
