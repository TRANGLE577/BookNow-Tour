const db = require("../models");

async function getAll() {
    return await db.Account.findAll({
        include: ['role']
    });
}

async function getById(id) {
    return await db.Account.findOne({
        where: {
            id: id,
        },
        include: ['role']
    });
}

async function deleteAccount(req, res) {
    let params = {
        id: req.body.id,
        status: req.body.status
    };

    if (req.body.id == 0 || req.body.id == '0') {
        return await db.Account.create(params);
    } else {
        return await db.Account.update(params, {
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
    getById,
    deleteAccount,
};
