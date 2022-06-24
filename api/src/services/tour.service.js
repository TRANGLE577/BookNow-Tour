const db = require("../models");
const Sequelize = require("sequelize");
const Op = Sequelize.Op;

async function getAll() {
    return await db.Tour.findAll({
        order: [['updatedAt', 'DESC']],
        include: ['location']
    });
}

async function getByRandom() {
    return await db.Tour.findAll({
        order: [
            [Sequelize.literal('RAND()')]
        ],
        limit: 5,
        include: ['location']
    });
}

async function getBySearch(keyword) {
    const s = '%' + keyword +'%'
    return await db.Tour.findAll({
        where: {
            name: {
              [Op.like]: s
            }
        },
        order: [['updatedAt', 'DESC']],
        include: ['location']
    });
}

async function getById(id) {
    return await db.Tour.findOne({
        where: {
            id: id,
        },
        include: ['location']
    });
}

async function save(req, res) {
    let params = {
        id: req.body.id,
        name: req.body.name,
        locationId: req.body.locationId,
        description: req.body.description,
        tourDateDepart: req.body.tourDateDepart,
        tourDateReturn: req.body.tourDateReturn,
        tourAdultCost: req.body.tourAdultCost,
        tourChildrenCost: req.body.tourChildrenCost,
        isFinish: req.body.isFinish,
        status: req.body.status
    };

    if (req.file && req.file.path) {
        params = {
            ...params,
            image: req.file.path,
        }
    }

    if (req.body.id == 0 || req.body.id == '0') {
        return await db.Tour.create(params);
    } else {
        return await db.Tour.update(params, {
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

async function getReportTour(req, res) {
    return await db.Tour.findAll({
        where: {
            createdAt: {
                [Op.between]: [req.body.startDate, req.body.endDate]
            },
        },

        include: ['location']
    });
}


async function deleteTour(req, res) {
    let params = {
        id: req.body.id,
        status: req.body.status
    };
    
    if (req.body.id == 0 || req.body.id == '0') {
        return await db.Tour.create(params);
    } else {
        return await db.Tour.update(params, {
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

module.exports = {
    getAll,
    getByRandom,
    getBySearch,
    getById,
    save,
    getReportTour,
    deleteTour
};
