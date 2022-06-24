const db = require("../models");

async function getAll() {
    return await db.Location.findAll({
    });
}

async function getById(id) {
    return await db.Account.findOne({
        where: {
            id: id,
        },
    });
}


module.exports = {
    getAll,
    getById,
};
