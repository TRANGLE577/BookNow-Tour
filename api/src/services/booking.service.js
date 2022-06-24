const db = require("../models");
var Sequelize = require('sequelize');
const Op = Sequelize.Op;

async function getAll() {
    return await db.Booking.findAll({
        include: ['account', 'payment', 'tour']
    });
}

async function getById(id) {
    return await db.Booking.findOne({
        where: {
            id: id,
        },
        include: ['account', 'payment', 'tour']
    });
}

async function save(req, res) {
    const params = {
        id: req.body.id,
        tourId: req.body.tourId,
        accountId: req.body.accountId,
        paymentId: req.body.paymentId,
        tourAdultCost: req.body.tourAdultCost,
        tourChildrenCost: req.body.tourChildrenCost,
        quantityAdult: req.body.quantityAdult,
        quantityChildren: req.body.quantityChildren,
        totalCost: req.body.totalCost,
        status: req.body.status
    };

    if (req.body.id == 0) {
        return await db.Booking.create(params);
    } else {
        return await db.Booking.update(params, {
            where: { id: req.body.id }
        }).then((res) => {
            if (res.length > 0 && res[0] !== 0) {
                return "SUCCESS";
            } else {
                return "ERROR";
            }
        });
    }
}

async function getReportBooking(req, res) {
    return await db.Booking.findAll({
        where: {
            createdAt: {
                [Op.between]: [req.body.startDate, req.body.endDate]
            },
        },

        include: ['account', 'payment', 'tour']
    });
}

async function cancle(req, res) {
    const params = {
        status: -1
    };
    return await db.Booking.update(params, {
        where: { id: req.body.id }
    }).then((res) => {
        if (res.length > 0 && res[0] !== 0) {
            return "SUCCESS";
        } else {
            return "ERROR";
        }
    });
}

async function getAllBookingInProgess() {
    return await db.Booking.findAll({
        where: {
            status: 0,
        },
        include: ['account', 'payment', 'tour']
    });
}

async function getAllBookingByTour(req, res) {
    return await db.Booking.findAll({
        where: {
            tour_id: req.body.tourId,
        },
        include: ['account', 'payment', 'tour']
    });
}

module.exports = {
    getAll,
    getById,
    save,
    getReportBooking,
    cancle,
    getAllBookingInProgess,
    getAllBookingByTour,
};
