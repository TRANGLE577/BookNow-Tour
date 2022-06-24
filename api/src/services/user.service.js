const db = require("../models");
const Sequelize = require('sequelize');
const Op = Sequelize.Op;

async function getBooking(id) {
    return await db.Booking.findAll({
        where: {
            accountId: id,
            status: 0
        },
        include: ['account', 'payment', 'tour']
    });
}

async function getHistory(id) {
    return await db.Booking.findAll({
        where: {
            accountId: id,
            status: {
                [Op.or]: [1, -1]
            }
        },
        include: ['account', 'payment', 'tour']
    });
}

module.exports = {
    getBooking,
    getHistory,
};
